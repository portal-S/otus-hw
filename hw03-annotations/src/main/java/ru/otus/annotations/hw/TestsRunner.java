package ru.otus.annotations.hw;

import ru.otus.annotations.hw.annotation.After;
import ru.otus.annotations.hw.annotation.Before;
import ru.otus.annotations.hw.annotation.Test;
import ru.otus.annotations.hw.exception.TestAnnotationConflictException;
import ru.otus.annotations.hw.result.ExecutionResultBuilder;
import ru.otus.annotations.hw.result.TestExecutionResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestsRunner {

    public void runTestClass(Class<?> clazz) {

        var methods = List.of(clazz.getMethods());

        // as a rule, there are not too many methods,
        // so it's not a problem to traverse the methods a few times
        var beforeMethods = obtainBeforeMethods(methods);
        var afterMethods = obtainAfterMethods(methods);
        var testMethods = obtainTestMethods(methods);

        validateAnnotationsCompatibility(beforeMethods, afterMethods, testMethods);

        var result = runTests(clazz, testMethods, beforeMethods, afterMethods);

        System.out.println(result);
    }

    private TestExecutionResult runTests(
        Class<?> clazz,
        List<Method> testMethods,
        List<Method> beforeMethods,
        List<Method> afterMethods
    ) {

        var resultBuilder = new ExecutionResultBuilder();

        for (var testMethod : testMethods) {
            try {
                var instance = clazz.getDeclaredConstructor().newInstance();

                // тут я рассматриваю падение before и after методов как падение всего теста
                runMethodsOnObject(beforeMethods, instance);
                testMethod.invoke(instance);
                runMethodsOnObject(afterMethods, instance);

                resultBuilder.registerSucceedTest(testMethod.getName());
            } catch (Exception e) {
                resultBuilder.registerFailedTest(testMethod.getName(), e.getMessage());
            }
        }
        return resultBuilder.build();
    }

    private void runMethodsOnObject(List<Method> methods, Object instance) throws InvocationTargetException, IllegalAccessException {
        for (var method : methods) {
            method.invoke(instance);
        }
    }

    private List<Method> obtainBeforeMethods(List<Method> methods) {
        return methods.stream().filter(m -> m.isAnnotationPresent(Before.class)).toList();
    }

    private List<Method> obtainAfterMethods(List<Method> methods) {
        return methods.stream().filter(m -> m.isAnnotationPresent(After.class)).toList();
    }

    private List<Method> obtainTestMethods(List<Method> methods) {
        return methods.stream().filter(m -> m.isAnnotationPresent(Test.class)).toList();
    }

    private void validateAnnotationsCompatibility(List<Method>... methodsGroupedByAnnotation) {
        Set<Method> uniqueMethods = new HashSet<>();
        var conflicted = Arrays.stream(methodsGroupedByAnnotation)
            .flatMap(list -> list.stream())
            .filter(method -> !uniqueMethods.add(method))
            .findAny();

        conflicted.ifPresent(conflictedMethod -> {
            throw new TestAnnotationConflictException(conflictedMethod.getName());
        });
    }

}

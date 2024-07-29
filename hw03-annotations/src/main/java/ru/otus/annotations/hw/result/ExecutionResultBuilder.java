package ru.otus.annotations.hw.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionResultBuilder {
    private final Map<String, String> failedTests;
    private final List<String> succeedTests;

    public ExecutionResultBuilder() {
        this.failedTests = new HashMap<>();
        this.succeedTests = new ArrayList<>();
    }

    public void registerFailedTest(String testMethodName, String failCause) {
        failedTests.put(testMethodName, failCause);
    }

    public void registerSucceedTest(String testMethodName) {
        succeedTests.add(testMethodName);
    }

    public TestExecutionResult build() {
        return new TestExecutionResult(failedTests, succeedTests);
    }
}
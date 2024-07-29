package ru.otus.annotations.hw;

public class Main {
    public static void main(String[] args) {
        TestsRunner runner = new TestsRunner();
        runner.runTestClass(TestClass.class);
    }
}

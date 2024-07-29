package ru.otus.annotations.hw.result;

import java.util.List;
import java.util.Map;

public class TestExecutionResult {
    private final Map<String, String> failedTests;
    private final List<String> succeedTests;

    TestExecutionResult(Map<String, String> failedTests, List<String> succeedTests) {
        this.failedTests = failedTests;
        this.succeedTests = succeedTests;
    }

    @Override
    public String toString() {
        StringBuilder builder  = new StringBuilder("Execution result: \n");
        builder.append("Succeeded: \n");
        succeedTests.forEach(succeedTest -> {
            builder.append(succeedTest).append("\n");
        });

        builder.append("Failed: \n");
        failedTests.forEach((failedTestName, failCause) -> {
            builder
                .append(failedTestName)
                .append("\n")
                .append("Cause: ")
                .append(failCause)
                .append("\n");
        });

        return builder.toString();
    }
}

package com.example.demo.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Simple TestNG listener for logging test results.
 * 
 * It doesn’t affect functionality but supports your test class
 * via the @Listeners(TestResultListener.class) annotation.
 */
public class TestResultListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶️ Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test failed: " + result.getName());
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n=== Starting Test Suite: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Finished Test Suite: " + context.getName() + " ===\n");
    }
}

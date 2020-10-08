package com.example.singletonobserverexample;

public class TestSingleton2 {
    private static final TestSingleton2 INSTANCE = new TestSingleton2();

    public static TestSingleton2 getInstance() {
        return INSTANCE;
    }

    private String data;

    private TestSingleton2() {
    }

    public String getData() {
        return data;
    }
}

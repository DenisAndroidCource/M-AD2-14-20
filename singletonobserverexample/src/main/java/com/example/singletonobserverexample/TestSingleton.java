package com.example.singletonobserverexample;

public class TestSingleton {
    private static TestSingleton INSTANCE;

    private TestSingleton() {
    }

    public static  TestSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestSingleton();
        }
        return INSTANCE;
    }
}

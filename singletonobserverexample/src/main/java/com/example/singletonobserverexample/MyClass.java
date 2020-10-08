package com.example.singletonobserverexample;

public class MyClass {

    public static void main(String[] args) {
        TestSingleton2 testSingleton2 = TestSingleton2.getInstance();

        TestObserver.getInstance().subscribe(new TestObserver.TestObserverActionListener() {
            @Override
            public void notifyDataChanged(String data) {

            }
        });
    }
}

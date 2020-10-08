package com.example.singletonobserverexample;

import java.util.ArrayList;
import java.util.List;

public class TestObserver {

    interface TestObserverActionListener {
        void notifyDataChanged(String data);
    }

    private static TestObserver INSTANCE;

    public static TestObserver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestObserver();
        }
        return INSTANCE;
    }

    private final List<TestObserverActionListener> listeners = new ArrayList<>();

    private TestObserver(){}

    public void subscribe(TestObserverActionListener testObserverActionListener) {
        if (!listeners.contains(testObserverActionListener)) {
            listeners.add(testObserverActionListener);
        }
    }

    public void unsubscribe(TestObserverActionListener testObserverActionListener) {
        listeners.remove(testObserverActionListener);
    }

    public void notify(String data) {
        for (TestObserverActionListener observer: listeners) {
            observer.notifyDataChanged(data);
        }
    }
}

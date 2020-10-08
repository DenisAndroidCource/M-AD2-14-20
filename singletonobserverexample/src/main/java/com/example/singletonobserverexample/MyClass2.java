package com.example.singletonobserverexample;

public class MyClass2 {

    public static void main(String[] args) {
        TestObserver testObserver = TestObserver.getInstance();
        testObserver.notify("Data");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

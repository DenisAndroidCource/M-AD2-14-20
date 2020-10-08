package com.example.adapterexample;

public class Computer {
    private CardReader cardReader;

    public void setCardReader(CardReader cardReader) {
        this.cardReader = cardReader;
    }

    public void readData() {
        cardReader.connect();
    }
}

package com.example.adapterexample;

public class CardReader implements Usb {

    private MemoryCard memoryCard;

    public CardReader(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public void connect() {
        memoryCard.connect();
    }
}

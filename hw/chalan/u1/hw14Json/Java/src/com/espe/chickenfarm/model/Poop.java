package com.espe.chickenfarm.model;

public class Poop {
    private int amount;

    public Poop(int amount) {
        this.amount = amount;
    }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    @Override
    public String toString() {
        return String.format("Poop{amount=%d}", amount);
    }
}
package com.espe.chickenfarm.model;

public class Egg {
    private String size;

    public Egg(String size) {
        this.size = size;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    @Override
    public String toString() {
        return String.format("Egg{size=%s}", size);
    }
}
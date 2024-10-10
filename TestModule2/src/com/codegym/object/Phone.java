package com.codegym.object;

import java.util.concurrent.atomic.AtomicInteger;

public class Phone {
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String name;
    private String branch;
    private int price;
    private int quantity;

    public Phone(int id, String name, int price, String branch, int quantity) {
        this.id = idCounter.incrementAndGet();
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.quantity = quantity;
    }

    public static AtomicInteger getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int maxProductId) {
        idCounter.set(maxProductId);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "DienThoai{" +
                "id= '%d' |" +
                ", name= '%s' |" +
                ", branch= '%s'  |" +
                ", price= '%s'|"  +
                ", quantity= %d |" +
                '}';
    }

    public int getProductId() {
        return id;
    }
}


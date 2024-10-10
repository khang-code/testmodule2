package com.codegym.object;

import java.util.Date;

public class GenuinePhone extends Phone {
    private Date warrantyPeriod;
    private String warrantyRange;
    public GenuinePhone(int id, String name, int price, String branch, int quantity, Date warrantyPeriod, String warrantyRange) {
        super(id, name, price, branch, quantity);
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyRange = warrantyRange;
    }

    public Date getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Date warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyRange() {
        return warrantyRange;
    }

    public void setWarrantyRange(String warrantyRange) {
        this.warrantyRange = warrantyRange;
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public void setBranch(String branch) {
        super.setBranch(branch);
    }

    @Override
    public String getBranch() {
        return super.getBranch();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

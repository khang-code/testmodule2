package com.codegym.object;

public class PortablePhone extends Phone {
    private String portableCountry;
    private String status;

    public PortablePhone(int id, String name, int price, String branch, int quantity, String portableCountry, String status) {
        super(id, name, price, branch, quantity);
        this.portableCountry = portableCountry;
        this.status = status;
    }

    public String getPortableCountry() {
        return portableCountry;
    }

    public void setPortableCountry(String portableCountry) {
        this.portableCountry = portableCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
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
    public String getName() {
        return super.getName();
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
    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }
}

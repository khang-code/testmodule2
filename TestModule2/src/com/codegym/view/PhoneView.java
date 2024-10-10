package com.codegym.view;

import com.codegym.object.Phone;
import com.codegym.service.PhoneService;
import com.codegym.object.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneView {
    private PhoneService phoneService;
    private Scanner scanner;

    public PhoneView(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add(Category.PORTABLE_CATEGORY);
        options.add(Category.GENUINE_CATEGORY);
        options.add("All phones");
        options.add("return");
    }
    public void manageProduct() {
        List<String> options = new ArrayList<>();
        options.add("add Phone");
        options.add("delete Phone");
        options.add("edit Phone");
        options.add("all Phone had add");
        options.add("return");
        MainView.showMenu("Product Menu", options);
    }

    public String inputPhoneName() {
        String name;
        do {
            System.out.print("Enter Phone Name: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Phone name cannot be empty");
            }
        } while (name.trim().isEmpty());
        return name;
    }

    public String inputPhoneBrand() {
        String brand;
        do {
            System.out.print("Please enter Phone Brand: ");
            brand = scanner.nextLine();
            if (brand.trim().isEmpty()) {
                System.out.println("Phone brand cannot be empty.");
            }
        } while (brand.trim().isEmpty());
        return brand;
    }

    public double inputPhonePrice() {
        double price = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Enter Phone Price: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    throw new IllegalArgumentException("Phone price cannot be empty");
                }
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Number invalid");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public int inputPhoneQuantity() {
        int quantity = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Please enter Phone Quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    throw new IllegalArgumentException("Cannot have negative quantity");
                }
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Number must be integer");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return quantity;
    }
    public void displayPhoneDetail(int productId) {
        PhoneService.getPhoneById(productId).ifPresentOrElse(
                Phone -> System.out.println(Phone),
                () -> System.out.println("Phone not found")
        );
}}

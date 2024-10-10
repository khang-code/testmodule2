package com.codegym.controller;

import com.codegym.object.Phone;
import com.codegym.service.PhoneService;
import com.codegym.view.MainView;
import com.codegym.view.PhoneView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PhoneController {

    private PhoneService phoneService = new PhoneService();
    private PhoneView phoneView;
    private MainView mainView;
    private List<Phone> phones = new ArrayList<>();
    public PhoneController(PhoneService phoneService, PhoneView phoneView) {
        this.phoneService = phoneService;
        this.phoneView = phoneView;
    }

    public void showProductManagementMenu() {
        while (true) {
            phoneView.showCategory();
            String choice = mainView.getInput("Your choice: ");

            switch (choice) {
                case "1":
                    addNewPhone();
                    break;
                case "2":
                    removePhone();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    phoneService.displayAllProducts();
            }
        }
    }
    private void addNewPhone() {
        String name = phoneView.inputPhoneName();
        String branch = phoneView.inputPhoneBranch();
        double price = phoneView.inputPhonePrice();
        int quantity = phoneView.inputPhoneQuanity();
        Phone newPhone = new Phone(name,branch,price,quantity);

        String category = branch.equalsIgnoreCase("Genuine") ? "Genuine" : "Portable";

        phoneService.addPhone(newPhone, category);
    }
    private void removePhone() {
        int productId = Integer.parseInt(mainView.getInput("Enter product ID: "));
        phoneService.getPhoneById(productId).ifPresentOrElse(
                Phone -> {
                    phoneView.displayPhoneDetail(productId);
                    if (mainView.confirmAction("Are you sure you want to delete this product?")) {
                        phoneService.removePhone(Phone);
                    }
                },
                () -> mainView.showMessage("Product does not exist.")
        );
    }
    public void displayAllProducts() {
        if (phones.isEmpty()) {
            System.out.println("empty list.");
        } else {
            List<Phone> sortedphones = phones.values().stream()
                    .sorted(Comparator.comparingInt(Phone::getProductId))
                    .toList();

            System.out.println("====== All product ======");
            for (Phone Phone : sortedphones) {
                System.out.println(Phone);
            }
        }
    }
}

package com.codegym.service;

import com.codegym.object.Category;
import com.codegym.object.Phone;
import com.codegym.view.MainView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PhoneService {
    private static final String PHONE_FILE_PATH = "src\\com\\codegym\\file_stored\\moblies.csv";

    private Map<String, Phone> phones = new HashMap<>();
    private MainView mainView;
    private Category genuineCategory;
    private Category portableCategory;

    // Constructor to initialize the service
    public PhoneService(Map<String, Phone> phones, Category portableCategory, Category genuineCategory, MainView mainView) {
        this.phones = phones;
        this.portableCategory = portableCategory;
        this.genuineCategory = genuineCategory;
        this.mainView = mainView;
        Map<String, Phone> loadedPhones = readProductsFromFile();
    }

    // Get phone by its productId
    public Optional<Phone> getPhoneById(int productId) {
        return phones.values().stream()
                .filter(phone -> phone.getId() == productId)
                .findFirst();
    }

    // Load phones and set ID counter
    public Optional<Map<String, Phone>> loadedPhones() {
        if (!phones.isEmpty()) {
            return Optional.of(phones);
        }

        phones = readProductsFromFile();  // Load phones from file
        int maxProductId = phones.values().stream()
                .mapToInt(Phone::getId)
                .max()
                .orElse(0);

        Phone.setIdCounter(maxProductId); // Update the ID counter
        return Optional.ofNullable(phones);
    }
    public void addPhone(Phone Phone, String categoryName) {
        if (phones.containsKey(String.valueOf(Phone.getProductId()))) {
            System.out.println("Item with ID " + Phone.getProductId() + " already exists. cannot add more");
        } else {
            if (categoryName.equals(Category.GENUINE_CATEGORY)) {
                genuineCategory.addPhoneToCategory(Phone);
                System.out.println("Have add item into Apple category.");
            } else {
                portableCategory.addPhoneToCategory(Phone);
                System.out.println("have add item into Windows category.");
            }
            public void removephone(Phone phone) {
                if (phone != null) {
                    if (Category.isGenuinePhone(phone.getBrand())) {
                        genuineCategory.getphones().remove(phone);
                    } else if (Category.isPortablePhone(phone.getBrand())) {
                        portableCategory.getphones().remove(phone);
                    }

                    CartItem cartItem = new CartItem(phone.getProductId(), phone.getName(), phone.getQuantity(), phone.getPrice());
                    phones.remove(String.valueOf(phone.getProductId()));

                    Map<String, Cart> carts = cartService.getAllCartsForAdminOrSeller();
                    for (String username : carts.keySet()) {
                        Cart cart = carts.get(username);
                        if (cart != null && cart.containsProduct(phone.getProductId())) {
                            cartService.updateCart(username, cartItem, false);
                        }
                    }
                    writeProductToFile(phones);
                    System.out.println("item with ID " + phone.getProductId() + " has delete.");
                } else {
                    System.out.println("this phone not exist.");
                }
            }public void updatephone(int productId, String name, String brand, double price, int quantity, String description) {
                phone phone = phones.get(String.valueOf(productId));

                if (phone != null) {
                    if (name != null && !name.isEmpty()) {
                        phone.setName(name);
                    }
                    if (brand != null && !brand.isEmpty()) {
                        phone.setBrand(brand);
                    }
                    if (price > 0) {
                        phone.setPrice(price);
                    }
                    if (quantity >= 0) {
                        phone.setQuantity(quantity);
                    }
                    if (description != null && !description.isEmpty()) {
                        phone.setSpecifications(description);
                    }

                    if (!phone.getBrand().equalsIgnoreCase(brand)) {
                        if (Category.isWindowsBrand(phone.getBrand())) {
                            windowsCategory.getphones().remove(phone);
                        } else if (Category.isAppleBrand(phone.getBrand())) {
                            appleCategory.getphones().remove(phone);
                        }

                        if (Category.isWindowsBrand(brand)) {
                            System.out.println("Add List Window complete");
                            windowsCategory.addphoneToCategory(phone);
                        } else if (Category.isAppleBrand(brand)) {
                            System.out.println("Add List Apple complete");
                            appleCategory.addphoneToCategory(phone);
                        }
                    }

                    phones.put(String.valueOf(productId), phone);
                    writeProductToFile(phones);
                    System.out.println("Updated product information with ID " + productId + " completed");
                } else {
                    System.out.println("item with ID " + productId + " not exist.");
                }
            }
            // Thêm vào danh sách sản phẩm
            phones.put(String.valueOf(Phone.getProductId()), Phone);
            writeProductToFile(phones);
            System.out.println("has add item with ID " + Phone.getProductId() + " into list and save into file.");
        }
    }


    public void writeProductToFile(Map<String, Phone> phones) {
        try (FileWriter writer = new FileWriter(PHONE_FILE_PATH)) {
            writer.write("ProductId|Name|Brand|Price|Quantity|Description\n");

            List<Phone> sortedPhones = phones.values().stream()
                    .sorted(Comparator.comparingInt(Phone::getId))
                    .toList();

            for (Phone phone : sortedPhones) {
                writer.write(String.format("%d|%s|%s|%.2f|%d%n",
                        phone.getId(),
                        phone.getName(),
                        phone.getBranch(),
                        phone.getPrice(),
                        phone.getQuantity()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Phone> readProductsFromFile() {
        Map<String, Phone> phones = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PHONE_FILE_PATH))) {
            String line;
            reader.readLine(); // Skip the header

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] split = line.split("\\|");
                if (split.length != 5) {
                    continue;
                }

                try {
                    int productId = Integer.parseInt(split[0].trim());
                    String name = split[1].trim();
                    String branch = split[2].trim();
                    double price = Double.parseDouble(split[3].trim());
                    int quantity = Integer.parseInt(split[4].trim());

                    Phone phone = new Phone(productId, name, (int) price, branch, quantity);
                    phones.put(String.valueOf(productId), phone);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phones;
    }
}

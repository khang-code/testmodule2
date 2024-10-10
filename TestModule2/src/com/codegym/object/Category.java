package com.codegym.object;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Category {

    public static final String PORTABLE_CATEGORY = "Portable";
    public static final String GENUINE_CATEGORY = "Genuine";

    private String categoryName;
    private List<Phone> phones;

    public Category(String categoryName) {
        if (!categoryName.equalsIgnoreCase(PORTABLE_CATEGORY) &&
                !categoryName.equalsIgnoreCase(GENUINE_CATEGORY)) {
            throw new IllegalArgumentException("Invalid category name. Only 'Portable' and 'Genuine' categories are supported.");
        }
        this.categoryName = categoryName;
        this.phones = new ArrayList<>();
    }

    public void addPhoneToCategory(Phone phone) {
        if ((categoryName.equalsIgnoreCase(PORTABLE_CATEGORY) && phone instanceof PortablePhone) ||
                (categoryName.equalsIgnoreCase(GENUINE_CATEGORY) && phone instanceof GenuinePhone)) {
            phones.add(phone);
        } else {
            System.out.println("The phone does not belong to the " + categoryName + " category. Cannot add.");
        }
    }

    public void displayPhonesInCategory() {
        System.out.println("===== Category: " + categoryName + " =====");

        if (phones.isEmpty()) {
            System.out.println("There are no phones in this category.");
        } else {
            List<Phone> sortedPhones = phones.stream()
                    .sorted(Comparator.comparingInt(Phone::getId))
                    .toList();

            for (Phone phone : sortedPhones) {
                System.out.println(phone);
            }
        }
    }

    public List<Phone> getPhones() {
        return phones.stream()
                .sorted(Comparator.comparingInt(Phone::getId))
                .collect(Collectors.toList());
    }
    public static boolean isGenuinePhone(Phone phone) {
        return !GENUINE_CATEGORY.equalsIgnoreCase("genuine");
    }
    public static boolean isPortablePhone(Phone phone) {
        return !PORTABLE_CATEGORY.equalsIgnoreCase("portable");
    }
}

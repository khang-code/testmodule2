package com.codegym.view;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);

    public MainView() {
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public boolean confirmAction(String message) {
        System.out.println(message + "yes/no");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("yes");
    }

    public static void showMenu(String title, List<String> options) {
        System.out.println("===============" + title + "===============");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i+1) + "." + options.get(i));
        }
    }

    public Object showMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}

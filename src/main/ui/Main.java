package ui;

import javax.swing.*;

import model.BudgetTracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose application mode:");
        System.out.println("1. Console");
        System.out.println("2. GUI");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            BudgetTrackerApp budgetTrackerApp = new BudgetTrackerApp();
            budgetTrackerApp.runApp();
        } else if (choice == 2) {
            BudgetTracker budgetTracker = new BudgetTracker();
            new GUI(budgetTracker);
        } else {
            System.out.println("Invalid choice. Exiting application.");
        }
    }
}

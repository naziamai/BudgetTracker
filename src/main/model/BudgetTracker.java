package model;

import java.util.ArrayList;
import java.util.List;

// BudgetTracker class makes changes to list of categories and handles total budget 

public class BudgetTracker {
    private List<Category> listOfCategory;
    private double totalBudget;

    public BudgetTracker() {
        this.listOfCategory = new ArrayList<>();
        this.totalBudget = 0.00;
    }
    //MODIFIES: this
    //EFFECT: Adds a new category to the list of categories

    public void addCategory(Category category) {
        listOfCategory.add(category);
    }

    //REQUIRES: listOfCategory should not be empty 
    //MODIFIES: this
    //EFFECT: Removes a category from the list of categories

    public void removeCategory(Category category) {
        listOfCategory.remove(category);
    }
    //MODIFIES: this
    //EFFECTS: Calculates the total budget added across all categories 

    public void calculateBudget() {
        double total = 0.00;
        for (Category c: listOfCategory) {
            total += c.getLimit();
        }
        this.totalBudget = total;
    }
    // EFFECTS: Returns the total budget 

    public double getTotalBudget() {
        return this.totalBudget;
    }

    //EFFECTS: Returns list of categories
    public List<Category> getListOfCategory() {
        return this.listOfCategory;
    }


}

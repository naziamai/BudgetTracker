package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private double limit;
    private List<Double> listOfExpense;
    private List<LocalDate> dates;

    public Category(String name, double limit) {
       this.name = name;
       this.limit = limit;
       this.listOfExpense = new ArrayList<>(); 
    }

    //REQUIRES: limit > 0.00
    //MODIFIES: this 
    //EFFECTS: sets limit for the category 
    public void setLimit(double limit) {
        this.limit = limit;
    }
    //REQUIRES: expense > 0.00
    //MODIFIES: this 
    //EFFECTS: Adds a single expense to the list of expenses and adds the date to list of dates
    public void addExpense(double expense) {
        listOfExpense.add(expense);
    }

    // EFFECTS: Returns the remaining limit on the category
    public double getRemainingLimit() {
        double remainingLimit;
        double total = 0.00;
        for(Double e: listOfExpense) {
            total += e;
        }
        remainingLimit = this.limit - total;
        return remainingLimit;
    }
    //EFFECTS: returns list of expenses
    public List<Double> getListOfExpense() {
        return this.listOfExpense;
    }

    //EFFECTS: Returns the name of the category 
    public String getName(){
        return this.name;
    }
    //EFFECTS: Returns the limit of the category 
    public double getLimit() {
        return this.limit;
    }
}

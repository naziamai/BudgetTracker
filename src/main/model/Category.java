package model;
import java.util.List;

public class Category {
    String name;
    Double limit;
    List<Double> listOfExpense;

    public Category(String name, Double limit) {
       //stub
    }

    //REQUIRES: limit > 0.00
    //MODIFIES: this 
    //EFFECTS: sets limit for the category 
    public void setLimit(Double limit) {
        
    }
    //REQUIRES: expense > 0.00
    //MODIFIES: this 
    //EFFECTS: Adds a single expense to the list of expenses 
    public void addExpense(Double expense) {

    }
    // EFFECTS: Returns the remaining limit on the category
    public Double getRemainingLimit() {
        return 0.00;
    }
    //EFFECTS: returns list of expenses
    public List<Double> getListOfExpense() {
        return null;
    }

    //EFFECTS: Returns the name of the category 
    public String getName(){
        return name;
    }
    //EFFECTS: Returns the limit of the category 
    public Double getLimit() {
        return 0.00;
    }
}

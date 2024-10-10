package model;
import java.util.List;

public class Category {
    String name;
    int limit;
    List<Double> listOfExpense;

    public Category(String name) {
       this.name = name;
    }

    //MODIFIES: this 
    //EFFECTS: sets limit for the category 
    public void setLimit(int limit) {
        this.limit = limit;
    }

    //MODIFIES: this 
    //EFFECTS: Adds a single expense to the list of expenses 
    public void addExpense(int expense) {

    }
}

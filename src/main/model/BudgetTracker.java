package model;
import java.util.List;

public class BudgetTracker {
    List<Category> listOfCategory;
    double totalBudget;

    public BudgetTracker(){
        //stub
    }
    //MODIFIES: this
    //EFFECT: Adds a new category to the list of categories
    public void addCategory(Category category){

    }
    //REQUIRES: listOfCategory should not be empty 
    //MODIFIES: this
    //EFFECT: Removes a category from the list of categories
    public void removeCategory(Category category){

    }
    //MODIFIES: this
    //EFFECTS: Calculates the total budget added across all categories 
    public void calculateBudget(){
        
    }
    // EFFECTS: Returns the total budget 
    public int getTotalBudget(){
        return 0;
    }

    //EFFECTS: Returns list of categories
    public List<Category> getListOfCategory() {
        return null;
    }


}

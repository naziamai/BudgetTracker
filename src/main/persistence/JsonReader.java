package persistence;

import java.io.IOException;
import org.json.*;

import model.BudgetTracker;
import model.Category;
import java.time.LocalDate;
import java.util.List;

// Citation: Code modelled from JsonSterializationDemo

//Represents a reader that reads workroom from JSON data stored in file 
public class JsonReader {
    private String source;
    
    //EFFECTS: contructs reader to read from source file
    public JsonReader(String source) {
    
    } 

    //EFFECTS: reads BudgetTracker from file and returns it;
    // throws IOE Exception if an error occurs while reading data from file;
    public BudgetTracker read() throws IOException {
        return null;
    }

    //EFFECTS: Reads source as a string and returns it 
    private String readFile(String source) throws IOException {
        return "string";
    }

    //EFFECTS: parses BudgetTracker from JSON object and returns it 
    private BudgetTracker parseBudgetTracker() {
        return null;
    }

    //MODIFIES: budgetTracker
    //EFFECTS: parses categories from jsonObject and adds them to budgetTracker
    private void addCategories(BudgetTracker budgetTracker, JSONObject jsonObject) {
    }

    //EFFECTS: parses a Category from jsonObject and returns it 
    private Category parseCategory() {
        return null;
    }

    //EFFECTS: parses expenses from a jsonArray and returns it 
    private List<Double> parseExpense() {
        return null;
    }

    //EFFECTS: parses dates from a jsonArray and returns it 
    private List<LocalDate> parseDates() {
        return null;
    }



}

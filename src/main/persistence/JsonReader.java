package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;

import model.BudgetTracker;
import model.Category;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Citation: Code modelled from JsonSterializationDemo

//Represents a reader that reads budgetTracker from JSON data stored in file 
public class JsonReader {
    private String source;
    
    //EFFECTS: contructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    
    } 

    //EFFECTS: reads BudgetTracker from file and returns it;
    // throws IOE Exception if an error occurs while reading data from file;
    public BudgetTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudgetTracker(jsonObject);
    }

    //EFFECTS: Reads source as a string and returns it 
    private String readFile(String source) throws IOException {
         StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses BudgetTracker from JSON object and returns it 
    private BudgetTracker parseBudgetTracker(JSONObject jsonObject) {
        BudgetTracker budgetTracker = new BudgetTracker();
        addCategories(budgetTracker, jsonObject);    
        budgetTracker.calculateBudget();
        return budgetTracker;
    }

    //MODIFIES: budgetTracker
    //EFFECTS: parses categories from jsonObject and adds them to budgetTracker
    private void addCategories(BudgetTracker budgetTracker, JSONObject jsonObject) {
        JSONArray jsonCategories = jsonObject.getJSONArray("categories");
        for (Object obj : jsonCategories) {
            JSONObject jsonCategory = (JSONObject) obj;
            Category category = parseCategory(jsonCategory);
            budgetTracker.addCategory(category);
        }
    }

    //EFFECTS: parses a Category from jsonObject and returns it 
    private Category parseCategory(JSONObject jsonCategory) {
        String name = jsonCategory.getString("name");
        double limit = jsonCategory.getDouble("limit");
        List<Double> expenses = parseExpense(jsonCategory.getJSONArray("listOfExpense"));
        List<LocalDate> dates = parseDates(jsonCategory.getJSONArray("dates"));


        Category category = new Category(name, limit);
        category.getListOfExpense().addAll(expenses);
        category.getDates().addAll(dates);

        return category;
        
        
    }

    //EFFECTS: parses expenses from a jsonArray and returns it 
    private List<Double> parseExpense(JSONArray jsonArray) {
            List<Double> expenses = new ArrayList<>();
            for (Object obj : jsonArray) {
                if (obj instanceof Number) {
                    expenses.add(((Number) obj).doubleValue());
            }
        }
            return expenses;
    }

    //EFFECTS: parses dates from a jsonArray and returns it 
    private List<LocalDate> parseDates(JSONArray jsonArray) {
        List<LocalDate> dates = new ArrayList<>();
        for (Object obj : jsonArray) {
            dates.add(LocalDate.parse((String) obj));
        }
        return dates;
    
    }



}

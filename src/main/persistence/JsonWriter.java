package persistence;
import model.BudgetTracker;
import org.json.JSONObject;

import java.io.*;

//Citation: Class modelled from JsonSterilization Demo -- JsonWriter Class

//Represents a writer that writes JSON representation of budgetTracker to file 
public class JsonWriter {
    private static int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file 
    public JsonWriter(String destination) {
        
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException() if destination file cannot
    // be opened for writing 
    public void open() throws FileNotFoundException {

    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of budgetTracker to file 
    public void write(BudgetTracker bt) {

    }

    //MODIFIES: this 
    //EFFECTS: writes string to file 
    private void saveToFile(String json) {

    }


}

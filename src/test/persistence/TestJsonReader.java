package persistence;

import org.junit.jupiter.api.Test;
import model.BudgetTracker;
import model.Category;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BudgetTracker bt = reader.read();
            fail("IOException expected");
        } catch(IOException e) {
            //pass
        }


    }

    @Test 
    void testReaderEmptyBudgetTracker() {
        JsonReader reader = new JsonReader("./data/TestReaderEmptyBudgetTracker.json");
        try {
            BudgetTracker bt = reader.read();
            assertEquals (0, bt.getListOfCategory().size());
            assertEquals (0.00, bt.getTotalBudget());

        } catch (IOException e) {
            fail("Couldn't read from fail");
        }

    }

    @Test 
    void testReaderGeneralBudgetTracker() {
        JsonReader reader = new JsonReader("./data/TestReaderGeneralBudgetTracker.json");
        try {
            BudgetTracker bt = reader.read();
            assertEquals (2, bt.getListOfCategory().size());
            checkCategory("grocery", 100.50, bt.getListOfCategory().get(0));
            checkCategory("bills", 330.00, bt.getListOfCategory().get(1));
            assertEquals (430.50, bt.getTotalBudget());


        } catch (IOException e) {
            fail("Couldn't load file");

        }

    }

    

}

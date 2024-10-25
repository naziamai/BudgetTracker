package persistence;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;
import model.BudgetTracker;
import model.Category;


public class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            BudgetTracker bt = new BudgetTracker();
            JsonWriter writer = new JsonWriter("./data/");
            writer.open();
        } catch (IOException e) {
            //pass 

        }
    }

    @Test 
    void testWriterEmptyBudgetTracker() {
        try {
            BudgetTracker bt = new BudgetTracker();
            JsonWriter writer = new JsonWriter("./data/TestWriterEmptyGeneralBudgetTracker.json");
            writer.open();
            writer.write(bt);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterEmptyGeneralBudgetTracker.json");
            bt = reader.read();
            assertTrue(bt.getListOfCategory().isEmpty());
            assertEquals(0.00, bt.getTotalBudget());


        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    } 

    @Test
    void testWriterGeneralBudgetTracker() {
        try {
            BudgetTracker bt = new BudgetTracker();
            bt.addCategory(new Category("grocery", 100.50));
            bt.addCategory(new Category("bills", 330.00));
            JsonWriter writer = new JsonWriter("./data/TestWriterGeneralBudgetTracker.json");
            writer.open();
            writer.write(bt);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterGeneralBudgetTracker.json");
            bt = reader.read();
            assertEquals(2, bt.getListOfCategory().size());
            checkCategory("grocery", 100.50, bt.getListOfCategory().get(0));
            checkCategory("bills", 330.00, bt.getListOfCategory().get(1));
            assertEquals (430.50, bt.getTotalBudget());

        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }
    
}

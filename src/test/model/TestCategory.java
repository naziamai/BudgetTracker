package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCategory {
    Category testCategory;
    List<Double> testExpenseAdded;
    List<LocalDate> testDates;
    LocalDate date1;
    LocalDate date2;
    
    @BeforeEach
    void runBefore() {
        testCategory = new Category("Grocery", 300.00);
        testExpenseAdded = new ArrayList<>();
        testDates = new ArrayList<>();
        date1 = LocalDate.of(2024, 10, 10);
        date2 = LocalDate.of(2024, 9, 10);

    }

    @Test 
    void testContructor() {
        assertEquals("Grocery", testCategory.getName());
        assertEquals(300.00, testCategory.getLimit());

    }

    @Test
    void testAddSingleExpense() {
        
        testCategory.addExpense(50.00, date1);
       
        testExpenseAdded.add(50.00);
        assertEquals(testExpenseAdded, testCategory.getListOfExpense());

        testDates.add(date1);
        assertEquals(testDates, testCategory.getDates());
    }

    @Test
    void testRemoveExpense() {
        testCategory.addExpense(50.00, date1);
        testCategory.addExpense(60.00, date1);
        testCategory.removeExpense(50.00, date1);

        testExpenseAdded.add(60.00);
        testDates.add(date1);
        assertEquals(testExpenseAdded, testCategory.getListOfExpense());
        assertEquals(testDates, testCategory.getDates());
    }

    @Test 
    void testAddMultipleExpense() {
       
        testCategory.addExpense(60.00, date1);
        testCategory.addExpense(75.10, date2);

        
        testExpenseAdded.add(60.00);
        testExpenseAdded.add(75.10);
        testDates.add(date1);
        testDates.add(date2);
        assertEquals(testExpenseAdded, testCategory.getListOfExpense());
        assertEquals(testDates, testCategory.getDates());
    }
    
    @Test 
    void testGetRemainingLimit() {
        testCategory.addExpense(60.00, date1);
        testCategory.addExpense(75.10,date2);

        testExpenseAdded.add(60.00);
        testExpenseAdded.add(75.10);
        assertEquals(164.90, testCategory.getRemainingLimit());
    }

    @Test 
    void testSetLimit() {
        testCategory.setLimit(500.00);
        assertEquals(testCategory.getLimit(), 500.00);
    }
    
    @Test 
    void testGetExpenseByDate() {
        testCategory.addExpense(60.00, date1);
        testCategory.addExpense(75.00, date2);
        assertEquals(60.00, testCategory.getExpenseByDate(date1));
    }
}

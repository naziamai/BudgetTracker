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
    
    @BeforeEach
    void runBefore() {
        testCategory = new Category("Grocery", 300.00);
        testExpenseAdded = new ArrayList<>();
        testDates = new ArrayList<>();

    }

    @Test 
    void testContructor() {
        assertEquals("Grocery", testCategory.getName());
        assertEquals(300.00, testCategory.getLimit());

    }

    @Test
    void testAddSingleExpense() {
        LocalDate date1 = LocalDate.of(2024, 10, 10);
        testCategory.addExpense(50.00, date1);
       
        testExpenseAdded.add(50.00);
        assertEquals(testExpenseAdded, testCategory.getListOfExpense());

        testDates.add(date1);
        assertEquals(testDates, testCategory.getDates());
    }

    @Test 
    void testAddMultipleExpense() {
        LocalDate date1 = LocalDate.of(2024, 10, 10);
        testCategory.addExpense(60.00, date1);
        
        LocalDate date2 = LocalDate.of(2024, 9, 10);
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
        LocalDate date1 = LocalDate.of(2024, 10, 10);
        testCategory.addExpense(60.00, date1);
        LocalDate date2 = LocalDate.of(2024, 9, 10);
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
}

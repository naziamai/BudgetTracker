package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCategory {
    Category testCategory;
    List<Double> testListOfExpense;
    
    @BeforeEach
    void runBefore() {
        testCategory = new Category("Grocery", 300.00);

    }

    @Test 
    void testContructor() {
        assertEquals("Grocery", testCategory.getName());
        assertEquals(300.00, testCategory.getLimit());

    }

    @Test
    void testAddSingleExpense() {
        testCategory.addExpense(50.00);
        List<Double> expenseAdded = new ArrayList<>();
        expenseAdded.add(50.00);
        assertEquals(expenseAdded, testCategory.getListOfExpense());
    }

    @Test 
    void testAddMultipleExpense() {
        testCategory.addExpense(60.00);
        testCategory.addExpense(75.10);
        List<Double> expenseAdded = new ArrayList<>();
        expenseAdded.add(60.00);
        expenseAdded.add(75.10);
        assertEquals(expenseAdded, testCategory.getListOfExpense());
    }
    @Test 
    void testGetRemainingLimit() {
        testCategory.addExpense(60.00);
        testCategory.addExpense(75.10);
        List<Double> expenseAdded = new ArrayList<>();
        expenseAdded.add(60.00);
        expenseAdded.add(75.10);
        assertEquals(165.00, testCategory.getRemainingLimit());
    }
}

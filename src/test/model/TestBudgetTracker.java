package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBudgetTracker {
    BudgetTracker testBudgetTracker;
    List<Category> testListOfCategories;
    double testTotalBudget;
    Category testc1;
    Category testc2;

    @BeforeEach
    void runBefore(){
        testBudgetTracker = new BudgetTracker();
        testListOfCategories = new ArrayList<>();
        testc1 = new Category("Grocery", 500.00);
        testc2 = new Category("Bills", 1000.00);

    }

    @Test 
    void testContructor() {
        assertEquals(testBudgetTracker.getListOfCategory(), null);
        assertEquals(testBudgetTracker.getTotalBudget(), 0.00);
    }
    @Test 
    void testAddSingleCategory() {
        testBudgetTracker.addCategory(testc1);
        testListOfCategories.add(new Category("Grocery", 500.00));
        assertEquals(testListOfCategories, testBudgetTracker.getListOfCategory());
    }
    @Test
    void testAddMultipleCategories() {
        testBudgetTracker.addCategory(testc1);
        testBudgetTracker.addCategory(testc2);
        testListOfCategories.add(new Category("Grocery", 500.00));
        testListOfCategories.add(new Category("Bills", 1000.00));
        assertEquals(testListOfCategories, testBudgetTracker.getListOfCategory());
    }
    @Test
    void testRemoveSingleCategory() {
        testBudgetTracker.addCategory(testc1);
        testBudgetTracker.addCategory(testc2);
        testListOfCategories.add(new Category("Grocery", 500.00));
        testListOfCategories.add(new Category("Bills", 1000.00));

        testBudgetTracker.removeCategory(testc2);
        testListOfCategories.remove(new Category("Bills", 1000.00));

        assertEquals(testListOfCategories, testBudgetTracker.getListOfCategory());
    }

    @Test
    void testRemoveMultipleCategories() {
        testBudgetTracker.addCategory(testc1);
        testBudgetTracker.addCategory(testc2);
        testListOfCategories.add(new Category("Grocery", 500.00));
        testListOfCategories.add(new Category("Bills", 1000.00));

        testBudgetTracker.removeCategory(testc2);
        testListOfCategories.remove(new Category("Bills", 1000.00));
        testBudgetTracker.removeCategory(testc1);
        testListOfCategories.remove(new Category("Grocery", 500.00));

        assertEquals(testListOfCategories, testBudgetTracker.getListOfCategory());
    }
}
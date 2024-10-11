package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestReport {
    Report testReport;
    List<Category> testCategories;
    List<LocalDate> testDates;
    LocalDate startDate;
    LocalDate endDate;

    @BeforeEach
    void runBefore() {
        testCategories = new ArrayList<>();
        Category c1 = new Category("Grocery", 600);
        Category c2 = new Category("Bills", 1000);
        Category c3 = new Category("Entertainment", 30);

        LocalDate midDate = LocalDate.of(2024, 9, 20);
        startDate = LocalDate.of(2024, 9, 10);
        endDate = LocalDate.of(2024, 10, 10);

        testReport = new Report(testCategories, startDate, endDate);
        
        c1.addExpense(50, midDate);
        c2.addExpense(300, startDate);
        c3.addExpense(20, midDate);

        testCategories.add(c1);
        testCategories.add(c2);
        testCategories.add(c3);
    }

    @Test
    void testGenerateSummary() {
        List<String> testSummary = new ArrayList<>();
        List<LocalDate> testDates = new ArrayList<>();

        LocalDate midDate = LocalDate.of(2024, 9, 20);
        testDates.add(midDate);
        testDates.add(startDate);
        testDates.add(midDate);

        String s1 = "Grocery" + ": " + "50.0"; 
        String s2 = "Bills" + ": " + "300.0";
        String s3 = "Entertainment" + ": " + "20.0";
   

        testSummary.add(s1);
        testSummary.add(s2);
        testSummary.add(s3);

        assertEquals(testSummary, testReport.generateSummary(startDate, endDate));
    }


}

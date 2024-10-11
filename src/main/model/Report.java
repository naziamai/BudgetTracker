package model;

import java.time.LocalDate;
import java.util.List;

public class Report {
    private List<Category> listOfCategory;
    private List<LocalDate> listOfDates;
    private LocalDate startDate;
    private LocalDate endDate;

    public Report(List<Category> listOfCategories, LocalDate startDate, LocalDate endDate){
         
    }
    

    //REQUIRES: Valid start and end date entered 
    //EFFECTS: If a date in list of date is between the start and end date then that expense will generated in summary
    //         in the form Category: Expense
      
    public void generateSummary(LocalDate startDate, LocalDate endDate){

    }
}

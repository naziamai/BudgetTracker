package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Report class generates a budget report according to the input dates 

public class Report {
    private List<Category> listOfCategory;
    

    public Report(List<Category> listOfCategories, LocalDate startDate, LocalDate endDate) {
        this.listOfCategory = listOfCategories;
         
    }
    

    //REQUIRES: Valid start and end date entered 
    //EFFECTS: If a date in list of date is between the start and end date then that expense will generated in summary
    //         in the form Category: Expense, as a list 
      
    public List<String> generateSummary(LocalDate startDate, LocalDate endDate) {
        List<String> summary = new ArrayList<>();

        for (Category c : listOfCategory) {
            List<LocalDate> dates = c.getDates(); 
            for (int i = 0; i < dates.size(); i++) {
                LocalDate date = dates.get(i);
                
                if ((date.isAfter(startDate) || date.equals(startDate)) 
                        && 
                        (date.isBefore(endDate) || date.equals(endDate))) {
                    double expense = c.getExpenseByDate(date); 
                    String entry = c.getName() + ": " + expense;
                    summary.add(entry);
                }
            }
        }
        EventLog.getInstance().logEvent(new Event("Generated report from: " + startDate + " to " +  endDate));
        return summary;
    }
        
         
}

package ui;

import java.util.*;

import model.BudgetTracker;
//import model.BudgetTracker;
import model.Category;
import model.Report;
import java.time.LocalDate;

public class BudgetTrackerApp {
    private BudgetTracker budgetTracker;
    private Scanner scanner;
   

    
    //EFFECTS: runs the budget tracker app
    public BudgetTrackerApp() {
        this.scanner = new Scanner(System.in);
        this.budgetTracker = new BudgetTracker();
        
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                executeCommand(command);
            }
        }
        System.out.println("\nGoodbye!");

    }
        
    //MODIFIES: this
    //EFFECTS: processes user input 

    private void executeCommand(String command) {
        if (command.equals("v")) {
            viewCategories();
        } else if (command.equals("a")) {
            addCategory();
        } else if (command.equals("e")) {
            addExpense();
        } else if (command.equals("r")) {
            generateReport();
        } else if (command.equals("b")) {
            viewBudget();
        } else if (command.equals("x")) {
            removeCategory();
        } else if (command.equals("l")) {
            removeExpense();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    
    //MODIFIES: this
    //EFFECTS: display the menu 
    private  void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view categories");
        System.out.println("\ta -> add category");
        System.out.println("\te -> add expense");
        System.out.println("\tr -> report");
        System.out.println("\tb -> view budget");
        System.out.println("\tx -> remove category");
        System.out.println("\tl -> remove expense");
        System.out.println("\tq -> quit");

    }

    //MODIFIES: this
    //EFFECTS: Allows user to add a category 

    private void addCategory() {
        System.out.println("Enter name of category: ");
        String name = scanner.next();
        name = name.toLowerCase();

        System.out.println("Enter limit for category: ");
        double limit = scanner.nextDouble();
        scanner.nextLine();
    
        Category category = new Category(name,limit);
        budgetTracker.addCategory(category);


    }
    //MODIFIES: this
    //EFFECTS: Allows user to add an expense, send an alert if the new expense is exceeding remaining limit
    
    private void addExpense() {
        System.out.println("Select a category from below: ");   
        List<Category> categories = budgetTracker.getListOfCategory();
        
        for (Category c : categories) {
            System.out.println(c.getName()); 
        }
        
        System.out.print("Enter the category name: ");
        String name = scanner.next(); 

        Category category = findCategory(name);
        if (category == null) {
            System.out.println("Category not found. Please enter a valid category.");
            return;
        }
        System.out.println("Enter the expense: ");
        double expense = scanner.nextDouble();
        scanner.nextLine();
        if (expense >= category.getRemainingLimit()) {
            alert(); 
        }
        System.out.println("Enter date (YYYY-MM-DD): ");
        String dateInput = scanner.next();
        LocalDate date = LocalDate.parse(dateInput);

        category.addExpense(expense, date);
        System.out.println("Expense successfully added");

    }
    //MODIFIES: this 
    //EFFECTS: Allows user to remove an expense

    private void removeExpense() {
        System.out.println("Select a category from below: ");
        viewCategories();
        
        System.out.print("Enter the category name: ");
        String name = scanner.next(); 

        Category category = findCategory(name);
        if (category == null) {
            System.out.println("Category not found. Please enter a valid category.");
            return;
        }

        System.out.println("Enter the expense: ");
        double expense = scanner.nextDouble();
        scanner.nextLine();


        System.out.println("Enter date (YYYY-MM-DD): ");
        String dateInput = scanner.next();
        LocalDate date = LocalDate.parse(dateInput);

        category.removeExpense(expense, date);
       
        System.out.println("Expense successfully removed from" + category);

    }
    //MODIFIES: this
    //EFFECTS: Allows user to remove a category

    private void removeCategory() {
        System.out.println("Enter name of category: ");
        String name = scanner.next();
        System.out.println("Enter limit for category: ");
        double limit = scanner.nextDouble();
    
        Category category = new Category(name,limit);
        if (category.getListOfExpense() == null) {
            System.out.println("Category not found. Please enter a valid category.");
            return;
        }
        budgetTracker.removeCategory(category);
        

    }

    //MODIFIES: this 
    //EFFECTS: generates a report based on date enetered 

    private void generateReport() {
        System.out.println("Enter a start date (YYYY-MM-DD): ");
        String startInput = scanner.next();
        LocalDate startDate = LocalDate.parse(startInput);
        System.out.println("Enter a end date (YYYY-MM-DD): ");
        String endInput = scanner.next();
        LocalDate endDate = LocalDate.parse(endInput);
        
        Report report = new Report(budgetTracker.getListOfCategory(), startDate, endDate);
        System.out.println(report.generateSummary(startDate, endDate));


    }
    //MODIFIES: this
    //EFFECTS: displays the list of categories added by the user 

    private void viewCategories() {
        if (budgetTracker.getListOfCategory() != null && !budgetTracker.getListOfCategory().isEmpty()) {
            System.out.println("--- Categories ---");
            for (Category c : budgetTracker.getListOfCategory()) {
                System.out.println("Category: " + c.getName() + ", Limit: " + c.getLimit());
            }
        } else {
            System.out.println("No categories added.");
        }

    }
        
    //EFFECTS: displays the total budget addded across all categories

    private void viewBudget() {
        budgetTracker.calculateBudget();
        double total = budgetTracker.getTotalBudget();
        System.out.println(total);
        
    }
    //EFFECTS: finds the category from list of category

    private Category findCategory(String name) {
        for (Category c: budgetTracker.getListOfCategory()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
    //EFFECTS: Provides alert messsage if expense exceeds remainiing limmit
    
    private void alert() {
        System.out.println("Alert: this expense exceeds remaining limit");
        System.out.println("Do you still want to proceed with expense? Yes or No? :");
        String answer = scanner.next();

        if (answer.equals("Yes")) {
            System.out.println("Procced to add details");
        } else {
            displayMenu();
        }

        
    }
}

    
    

    
package ui;

import model.BudgetTracker;
import model.Category;
import model.Report;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
/**
 * Class GUI
 * Represents the graphical user interface for the Budget Tracker application.
 */

public class GUI {
    private BudgetTracker budgetTracker;
    private Map<String, Category> categoryMap = new HashMap<>();
    private static final String DATA_FILE = "./data/budget_tracker.json";
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JComboBox<String> categoryDropdown;
    private JTextField categoryNameField;
    private JTextField categoryLimitField;
    private JTextField expenseAmountField;
    private JTextField expenseDateField;
    
    public GUI(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;

        // Create JFrame
        JFrame jframe = new JFrame("Budget Tracker");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(400, 400);

        // Add main panel to the frame
        jframe.add(mainPanel);

        // Initialize the pages
        initializeMainPage();
        initializeCategoryPage();
        initializeReportPage();
        initializeExpensePage();
        initializeCategoryListPage();

        // Show the frame
        jframe.setVisible(true);
    }

    

    /**
     * Modifies: This.
     * Effects: Creates the main page of the GUI with buttons for navigation and adds it to the main panel.
     */
    @SuppressWarnings("methodlength")
    private void initializeMainPage() {
        JPanel mainPage = new JPanel(new GridLayout(0, 1));
    
        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome to Budget Tracker App!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPage.add(welcomeLabel);
    
        // Add buttons
        JButton categoryButton = new JButton("Category");
        JButton expenseButton = new JButton("Expense");
        JButton totalBudgetButton = new JButton("Total Budget");
        JButton reportButton = new JButton("Report");
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");
        JButton categoryListButton = new JButton("View Categories");
    
        mainPage.add(categoryButton);
        mainPage.add(totalBudgetButton);
        mainPage.add(categoryListButton);
        mainPage.add(reportButton);
        mainPage.add(expenseButton);
        mainPage.add(saveButton);
        mainPage.add(loadButton);
    
    
        // Action Listeners
        categoryButton.addActionListener(e -> cardLayout.show(mainPanel, "CategoryPage"));
        expenseButton.addActionListener(e -> cardLayout.show(mainPanel, "ExpensePage"));
        totalBudgetButton.addActionListener(e -> handleTotalBudget());
        reportButton.addActionListener(e -> cardLayout.show(mainPanel, "ReportPage"));
        saveButton.addActionListener(e -> handleSaveData());
        loadButton.addActionListener(e -> handleLoadData());
    
        categoryListButton.addActionListener(e -> {
            updateCategoryTable((DefaultTableModel) ((JTable) ((JScrollPane) ((JPanel) mainPanel.getComponent(4))
                    .getComponent(0)).getViewport().getView()).getModel());
            cardLayout.show(mainPanel, "CategoryListPage");
        });
    
        mainPanel.add(mainPage, "MainPage");
    }

    /**
     * Modifies: This.
     * Effects: Creates the category management page with options to add and delete categories.
     */
    @SuppressWarnings("methodlength")
    private void initializeCategoryPage() {
        JPanel categoryPage = new JPanel(new GridLayout(0, 1));
    
        // Fields for adding a new category
        categoryNameField = new JTextField();
        categoryPage.add(new JLabel("Category Name:"));
        categoryPage.add(categoryNameField);
    
        categoryLimitField = new JTextField();
        categoryPage.add(new JLabel("Category Limit:"));
        categoryPage.add(categoryLimitField);
    
        JButton addCategoryButton = new JButton("Add Category");
        categoryPage.add(addCategoryButton);
    
        // Dropdown for selecting a category to delete
        JComboBox<String> deleteCategoryDropdown = new JComboBox<>();
        categoryPage.add(new JLabel("Delete Category:"));
        categoryPage.add(deleteCategoryDropdown);
    
        // Button to delete the selected category
        JButton deleteCategoryButton = new JButton("Delete Category");
        categoryPage.add(deleteCategoryButton);
    
        // Back button
        JButton backButton = new JButton("Back");
        categoryPage.add(backButton);
    
        // Add Action Listeners
        addCategoryButton.addActionListener(e -> {
            handleAddCategory();
            refreshCategoryDropdown(deleteCategoryDropdown);
        });
    
        deleteCategoryButton.addActionListener(e -> {
            String selectedCategory = (String) deleteCategoryDropdown.getSelectedItem();
            if (selectedCategory != null) {
                handleDeleteCategory(selectedCategory, deleteCategoryDropdown);
            } else {
                JOptionPane.showMessageDialog(null,
                         "No category selected to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainPage"));
    
        // Add to main panel
        mainPanel.add(categoryPage, "CategoryPage");
    }

    /**
     * Modifies: This.
     * Effects: Creates the expense management page with options to add expenses to categories.
     */
    private void initializeExpensePage() {
        JPanel expensePage = new JPanel(new GridLayout(0, 1));
    
        // Dropdown to select category
        expensePage.add(new JLabel("Select Category:"));
        categoryDropdown = new JComboBox<>();
        expensePage.add(categoryDropdown);
    
        // Expense Fields
        expenseAmountField = new JTextField();
        expensePage.add(new JLabel("Expense Amount:"));
        expensePage.add(expenseAmountField);
    
        expenseDateField = new JTextField();
        expensePage.add(new JLabel("Expense Date (YYYY-MM-DD):"));
        expensePage.add(expenseDateField);
    
        JButton addExpenseButton = new JButton("Add Expense");
        expensePage.add(addExpenseButton);
    
        JButton backButton = new JButton("Back");
        expensePage.add(backButton);
    
        // Action Listeners
        addExpenseButton.addActionListener(e -> handleAddExpense());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainPage"));
    
        mainPanel.add(expensePage, "ExpensePage");
    }

    /**
     * Modifies: This.
     * Effects: Creates the report page to generate expense summaries between dates and adds it to the main panel.
     */
    private void initializeReportPage() {
        JPanel reportPage = new JPanel();
        reportPage.setLayout(new GridLayout(0, 1));
    
        // Start and End Date Fields
        JTextField startDateField = new JTextField();
        reportPage.add(new JLabel("Start Date (YYYY-MM-DD):"));
        reportPage.add(startDateField);
    
        JTextField endDateField = new JTextField();
        reportPage.add(new JLabel("End Date (YYYY-MM-DD):"));
        reportPage.add(endDateField);
    
        // Generate Report Button
        JButton generateReportButton = new JButton("Generate Report");
        reportPage.add(generateReportButton);
            
        // Report Area
        JTextArea reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        reportPage.add(scrollPane);
    
        // Back Button
        JButton backButton = new JButton("Back");
        reportPage.add(backButton);
    
        // Action Listeners
        actionListenerReport(startDateField, endDateField, generateReportButton, reportArea);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainPage"));
        mainPanel.add(reportPage, "ReportPage");
    }
    

    private void actionListenerReport(JTextField startDateField, JTextField endDateField, JButton generateReportButton,
            JTextArea reportArea) {
        generateReportButton.addActionListener(e -> {
            try {
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());

                Report report = new Report(budgetTracker.getListOfCategory(), startDate, endDate);
                java.util.List<String> summary = report.generateSummary(startDate, endDate);

                reportArea.setText("Expenses from " + startDate + " to " + endDate + ":\n");
                for (String entry : summary) {
                    reportArea.append(entry + "\n");
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, 
                        "Please enter valid dates in the format YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Modifies: This.
     * Effects: Creates a page to display a table of all categories and their details, and adds it to the main panel.
     */
    private void initializeCategoryListPage() {
        // Create a panel for the category list
        JPanel categoryListPage = new JPanel(new BorderLayout());
    
        // Create a table to display categories
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Category Name", "Limit", "Total Expenses"}, 0);
        JTable categoryTable = new JTable(model);
        categoryListPage.add(new JScrollPane(categoryTable), BorderLayout.CENTER);
    
        // Back Button
        JButton backButton = new JButton("Back");
        categoryListPage.add(backButton, BorderLayout.SOUTH);
    
        // Action Listener for Back Button
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainPage"));
    
        // Add the page to the main panel
        mainPanel.add(categoryListPage, "CategoryListPage");
    }

    /**
     * handleAddCategory
     * 
     * Requires: Valid input for category name and limit.
     * Modifies: this, budgetTracker.
     * Effects: Adds a new category to the list of categories and updates the dropdown menu.
     */
    private void handleAddCategory() {
        String name = categoryNameField.getText();
        String limitText = categoryLimitField.getText();
    
        try {
            double limit = Double.parseDouble(limitText);
            Category category = new Category(name, limit);
            budgetTracker.addCategory(category);
            categoryMap.put(name, category); // Add to local map
            categoryDropdown.addItem(name); // Update dropdown
    
            // Display success message with dancing cat GIF
            showDancingCat();
    
            categoryNameField.setText("");
            categoryLimitField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid number for the limit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Modifies: this, budgetTracker.
     * Effects: Deletes the specified category and updates all related dropdowns.
     */
    private void handleDeleteCategory(String categoryName, JComboBox<String> deleteCategoryDropdown) {
        // Remove category from the map and budget tracker
        Category category = categoryMap.remove(categoryName);
        if (category != null) {
            budgetTracker.getListOfCategory().remove(category);
            deleteCategoryDropdown.removeItem(categoryName);
            categoryDropdown.removeItem(categoryName); // Update the dropdown in Expense Page
            JOptionPane.showMessageDialog(null, "Category '" + categoryName + "' deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete category!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Requires: A valid category exists in the dropdown, and valid inputs for expense amount and date.
     * Modifies: this, budgetTracker.
     * Effects: Adds a new expense to the selected category.
     */
    private void handleAddExpense() {
        String selectedCategory = (String) categoryDropdown.getSelectedItem();
        String amountText = expenseAmountField.getText();
        String dateText = expenseDateField.getText();
    
        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(null, "Please select a category.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double amount = Double.parseDouble(amountText);
            LocalDate date = LocalDate.parse(dateText);
    
            Category category = categoryMap.get(selectedCategory); // Fetch from map
            if (category == null) {
                JOptionPane.showMessageDialog(null, "Category not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            category.addExpense(amount, date);
    
            // Display success message with dancing cat GIF
            showMoneyGif();
    
            expenseAmountField.setText("");
            expenseDateField.setText("");
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null,
                    "Invalid input. Please check the amount and date format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Effects: Displays a summary of the total budget, expenses, and remaining funds across all categories.
     */

    private void handleTotalBudget() {
        double totalLimit = 0.0;
        double totalExpenses = 0.0;

        for (Category category : budgetTracker.getListOfCategory()) {
            totalLimit += category.getLimit();
            totalExpenses += category.getLimit() - category.getRemainingLimit();
        }

        double remainingBudget = totalLimit - totalExpenses;

        JOptionPane.showMessageDialog(
                null,
                "Total Limit: $" + totalLimit 
                + "\nTotal Expenses: $" + totalExpenses 
                + "\nRemaining Budget: $" + remainingBudget,
                "Total Budget",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    /** 
     * Modifies: this
     * Effects : Convert BudgetTracker to JSON and save to file
    */

    private void handleSaveData() {
        try {
            JSONObject json = budgetTracker.toJson();
            java.nio.file.Files.write(java.nio.file.Paths.get(DATA_FILE), json.toString().getBytes());
            JOptionPane.showMessageDialog(null, 
                    "Data saved successfully!", "Save Data", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                    "Failed to save data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this;
    //EFFECTS: Read JSON data from file and recreate BudgetTracker
    private void handleLoadData() {
        try {
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(DATA_FILE)));
            JSONObject json = new JSONObject(content);

            // Clear current data
            budgetTracker.getListOfCategory().clear();
            categoryMap.clear();
            categoryDropdown.removeAllItems();

            // Populate categories from loaded data
            JSONArray categoriesJson = json.getJSONArray("categories");
            for (int i = 0; i < categoriesJson.length(); i++) {
                JSONObject categoryJson = categoriesJson.getJSONObject(i);
                String name = categoryJson.getString("name");
                double limit = categoryJson.getDouble("limit");
                Category category = new Category(name, limit);
                budgetTracker.addCategory(category);
                categoryMap.put(name, category);
                categoryDropdown.addItem(name);
            }

            JOptionPane.showMessageDialog(null, 
                    "Data loaded successfully!", "Load Data", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                    "Failed to load data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Modifies: model
     * Effects: Updates the table model with current category details.
     */
    private void updateCategoryTable(DefaultTableModel model) {
        model.setRowCount(0); // Clear existing rows
        for (Category category : budgetTracker.getListOfCategory()) {
            double totalExpenses = category.getListOfExpense().stream().mapToDouble(Double::doubleValue).sum();
            model.addRow(new Object[]{category.getName(), category.getLimit(), totalExpenses});
        }
    }

    /**
     * Modifies: dropdown
     * Effects: Clears and repopulates the dropdown with the current list of categories.
     */
    private void refreshCategoryDropdown(JComboBox<String> dropdown) {
        dropdown.removeAllItems(); // Clear existing items
        for (String categoryName : categoryMap.keySet()) {
            dropdown.addItem(categoryName); // Add all categories
        }
    }

    /**
     * Effects: Displays a dialog box with a dancing cat GIF.
     */
    private void showDancingCat() {
        // Create a dialog to display the GIF
        JDialog dialog = new JDialog();
        dialog.setTitle("Category Added!");
    
        // Add the dancing cat GIF
        JLabel gifLabel = new JLabel(new ImageIcon("200w.gif"));
        dialog.add(gifLabel);
    
        // Set dialog properties
        dialog.setSize(300, 300); // Adjust size based on GIF dimensions
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
        // Automatically close the dialog after a delay
        Timer timer = new Timer(3000, e -> dialog.dispose()); // 3 seconds
        timer.setRepeats(false);
        timer.start();
    
        dialog.setVisible(true);
    }
    /**
     * Effects: Displays a dialog box with a money-related GIF.
     */

    private void showMoneyGif() {
        // Create a dialog to display the GIF
        JDialog dialog = new JDialog();
        dialog.setTitle("Expense Added!");
    
        // Add the dancing cat GIF
        JLabel gifLabel = new JLabel(new ImageIcon("giphy.gif"));
        dialog.add(gifLabel);
    
        // Set dialog properties
        dialog.setSize(300, 300); // Adjust size based on GIF dimensions
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
        // Automatically close the dialog after a delay
        Timer timer = new Timer(3000, e -> dialog.dispose()); // 3 seconds
        timer.setRepeats(false);
        timer.start();
    
        dialog.setVisible(true);
    }
}

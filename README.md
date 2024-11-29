# My Personal Project

## Budget tracking application

### *Project Proposal*
The budget tracking application will be designed to effectively help individuals/families manage and track their expenses in an organised manner.There will be three key features in this application:
- **Categorized spending**: Account for spendings being made in one's daily life such as grocery, entertainment, bills, and recurring subscription payments.

- **Spending Limits**: It will allow users to set limits on the amount of money that should be spent in each category. Additionally, it will also send out alerts to users when they are near/exceeding this amount. 

- **Generate reports**: Towards the end of every month the budget tracker should generate a report summarizing spending on each category, checking if financial goals were met.

This project is of particular interest to me since it can be a very helpful tool to many college students/individuals like myself who often need to manage tight budgets and keep track of various expenses. This application may help users make informed decisions about their spending, ultimately leading to healthier financial habits.

### *User Stories*
*Phase 1*
- As a user, I want to be able to add an expense to a category.
- As a user, I want to be able to remove an expense from a category. 
- As a user, I want to be able to view the list of categories I spend money on.
- As a user, I want to be able to add additional categories.
- As a user, I want to be able to remove categories. 
- As a user, I want to be able to set a budget limit to a category.
- As a user I want to be able to view the list of budget in each category and the total budget added. 
- As a user I want be able to select a category and view the spending details.  
- As a user, I want to be able to view my monthly expense report.  

*Phase 2*
- As a user I want to be able to save the entire state of my application to file (if and when I choose to). 
- As a user I want to be able to load the entire state of my application (if and when I choose to), from file and resume where I left off. 


### Instructions for End User
*Phase 3*
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting the category button and adding/removing a category from list of categories.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by adding expenses to any selected category from list of categories 
- You can locate my visual component after successfully adding a category or expense from the respective button options.
- You can save the state of my application by clicking on the save button.
- You can reload the state of my application by clicking on the load button .


*Phase 4: Task 2*
Sample copy pasted: 

Logged events:
Thu Nov 28 19:11:08 PST 2024
Added category : Bills
Thu Nov 28 19:11:23 PST 2024
Added expense for Bills: 30.0 dollars
Thu Nov 28 19:11:36 PST 2024
Added category : Shopping
Thu Nov 28 19:11:48 PST 2024
Added expense for Shopping: 10.0 dollars
Thu Nov 28 19:12:06 PST 2024
Generated report from: 2019-01-01 to 2024-01-01
Thu Nov 28 19:12:20 PST 2024
Removed category : Shopping
Application is exiting...

*Phase 4: Task 3*

Both the CLI (BudgetTrackerApp) and GUI share similar logic for adding/removing categories and expenses, as well as saving/loading data. Refactoring this shared functionality into reusable utility methods or a shared controller would reduce redundancy and simplify future modifications. Additionally, separate classes could be made for the GUI where each class would separately handle user interactions with specific tasks,  such as UICategories for category management and UIExpenses for expense management. This modular design would improve readability, maintainability, and scalability while adhering to the Single Responsibility Principle. 
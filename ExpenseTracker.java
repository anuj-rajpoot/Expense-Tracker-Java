import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "Category: " + category + ", Amount: " + amount + ", Date: " + date;
    }
}

public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Total Expenses");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> editExpense();
                case 4 -> deleteExpense();
                case 5 -> totalExpenses();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again!");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        expenses.add(new Expense(category, amount, date));
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if(expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        System.out.println("\nAll Expenses:");
        for(int i=0; i<expenses.size(); i++) {
            System.out.println((i+1) + ". " + expenses.get(i));
        }
    }

    private static void editExpense() {
        viewExpenses();
        if(expenses.isEmpty()) return;

        System.out.print("Enter expense number to edit: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if(index < 0 || index >= expenses.size()) {
            System.out.println("Invalid expense number!");
            return;
        }

        Expense e = expenses.get(index);
        System.out.print("Enter new category (" + e.getCategory() + "): ");
        String category = sc.nextLine();
        System.out.print("Enter new amount (" + e.getAmount() + "): ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter new date (" + e.getDate() + "): ");
        String date = sc.nextLine();

        e.setCategory(category);
        e.setAmount(amount);
        e.setDate(date);
        System.out.println("Expense updated successfully!");
    }

    private static void deleteExpense() {
        viewExpenses();
        if(expenses.isEmpty()) return;

        System.out.print("Enter expense number to delete: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if(index < 0 || index >= expenses.size()) {
            System.out.println("Invalid expense number!");
            return;
        }
        expenses.remove(index);
        System.out.println("Expense deleted successfully!");
    }

    private static void totalExpenses() {
        double total = 0;
        for(Expense e : expenses) total += e.getAmount();
        System.out.println("Total Expenses: " + total);
    }
}

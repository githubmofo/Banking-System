import java.util.*;

class Account {
    private static int idCounter = 1000;
    private int accountId;
    private String holderName;
    private double balance;

    public Account(String holderName) {
        this.holderName = holderName;
        this.balance = 0.0;
        this.accountId = idCounter++;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + "\nHolder: " + holderName + "\nBalance: $ " + balance;
    }
}

public class SimpleBankingSystem {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Simple Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccount();
                case 5 -> System.out.println("Thank you for using the system.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine();  // Consume newline
        String name = scanner.nextLine();
        Account newAccount = new Account(name);
        accounts.put(newAccount.getAccountId(), newAccount);
        System.out.println("Account created successfully! Account ID: " + newAccount.getAccountId());
    }

    private static void deposit() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        Account acc = accounts.get(id);
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        Account acc = accounts.get(id);
        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccount() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        Account acc = accounts.get(id);
        if (acc != null) {
            System.out.println("\n--- Account Details ---");
            System.out.println(acc);
        } else {
            System.out.println("Account not found.");
        }
    }
}

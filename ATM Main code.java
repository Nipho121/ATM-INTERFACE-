import java.util.List;
import java.util.*;
import java.lang.String;

public class ATM {
    private Account currentAccount;

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }

    private void run() {
        // Create a sample account
        Account sampleAccount = new Account("123456789", "1234", 1700.0);
        Account sampleAccount2 = new Account("19125251", "1122", 1500.0);

        // Simulate login
        if (login(sampleAccount)) {
            displayMenu();
        } else {
            System.out.println("Invalid login Details. Exiting...");
        }
        if (login(sampleAccount2)) {
            displayMenu();
        } else {
            System.out.println("Invalid login Details. Exiting...");
        }
    }

    private boolean login(Account account) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (account.getAccountNumber().equals(accountNumber) && account.validatePin(pin)) {
            currentAccount = account;
            return true;
        } else {
            return false;
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewBalance() {
        System.out.println("Your balance: R" + currentAccount.getBalance());
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount to deposit : R ");
        double amount = scanner.nextDouble();

        currentAccount.deposit(amount);
        System.out.println("Deposit successful. New balance is: R " + currentAccount.getBalance());
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Your withdrawal amount: R ");
        double amount = scanner.nextDouble();

        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: R " + currentAccount.getBalance());
        }
    }

    private void transfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipient's account number: ");
        String recipientAccountNumber = scanner.nextLine();


        System.out.print("Enter amount to transfer : R");
        double amount = scanner.nextDouble();
        Account recipientAccount = new Account(recipientAccountNumber, "", 0);
        if (currentAccount.transfer(recipientAccount, amount)) {
            System.out.println("Transfer successful. New balance: $" + currentAccount.getBalance());
        }


    }

    private void viewTransactionHistory() {
        List<String> history = currentAccount.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (String transaction : history) {
            System.out.println(transaction);
        }


    }
}


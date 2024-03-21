import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private List <String> transactionHistory;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addToTransactionHistory("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addToTransactionHistory("Withdrawal: -" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }
    public boolean transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            addToTransactionHistory("Transfer to " + recipient.getAccountNumber() + ": -" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }


    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    private void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

}

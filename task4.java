import java.util.Scanner;
interface ATM {
    void checkBalance();
    void deposit();
    void withdraw();
    void exit();
}
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class BasicATM implements ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public BasicATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void checkBalance() {
        System.out.println("Your account balance is: $" + bankAccount.getBalance());
    }

    @Override
    public void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            bankAccount.deposit(amount);
            System.out.println("Deposit of $" + amount + " successful. Your new balance is: $" + bankAccount.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    @Override
    public void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            if (bankAccount.withdraw(amount)) {
                System.out.println("Withdrawal of $" + amount + " successful. Your new balance is: $" + bankAccount.getBalance());
            } else {
                System.out.println("Insufficient balance. Withdrawal failed.");
            }
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    @Override
    public void exit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }

    public void showOptions() {
        System.out.println("Welcome to the ATM.");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public int getSelectedOption() {
        return scanner.nextInt();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void executeOption(int option) {
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

public class task4{
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000); // Initial balance of $1000
        BasicATM atm = new BasicATM(bankAccount);

        while (true) {
            atm.showOptions();
            System.out.print("Select an option: ");
            int option = atm.getSelectedOption();
            atm.executeOption(option);
            System.out.println();
 }
}
}
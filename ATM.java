import java.util.Scanner;

public class ATM {
    private double balance;
    private String lastTransaction;
    private String pin;

    public ATM(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.lastTransaction = "None";
    }

    public void runATM() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean authenticated = false;

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (enteredPin.equals(pin)) {
            authenticated = true;
        } else {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        while (authenticated) {
            System.out.println("\nWelcome to the ATM!");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. View Last Transaction");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    viewLastTransaction();
                    break;
                case 5:
                    changePin(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            lastTransaction = "Failed withdrawal of " + amount;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
            lastTransaction = "Withdrawal of $" + amount;
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
        lastTransaction = "Deposit of $" + amount;
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    private void viewLastTransaction() {
        System.out.println("Your last transaction was: " + lastTransaction);
    }

    private void changePin(Scanner scanner) {
        System.out.print("Enter current PIN: ");
        String currentPin = scanner.next();

        if (currentPin.equals(pin)) {
            System.out.print("Enter new PIN: ");
            String newPin = scanner.next();
            pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM(1000.0, "1234");
        atm.runATM();
    }
}

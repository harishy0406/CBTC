import java.util.*;
import java.io.*;

class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accNum;
    private String accHolder;
    private double balance;
    private int securityPin;

    Account(String accNum, String accHolder, int securityPin) {
        this.accNum = accNum;
        this.accHolder = accHolder;
        this.securityPin = securityPin;
        this.balance = 0;
    }

    String getAccNum() {
        return accNum;
    }

    String getAccHolder() {
        return accHolder;
    }

    double getBalance() {
        return balance;
    }

    int getSecurityPin() {
        return securityPin;
    }

    void deposit(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
                printStars();
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } else {
            System.out.println("Invalid security pin.");
        }
    }

    void withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
                printStars();
            } else {
                System.out.println("Invalid withdrawal amount or insufficient funds.");
            }
        } else {
            System.out.println("Invalid security pin.");
        }
    }

    void transfer(Account to, double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                to.balance += amount;
                System.out.println("Transferred: " + amount + " to " + to.getAccHolder());
                printStars();
            } else {
                System.out.println("Invalid transfer amount or insufficient funds.");
            }
        } else {
            System.out.println("Invalid security pin.");
        }
    }

    private boolean validatePin(int pin) {
        return this.securityPin == pin;
    }

    private void printStars() {
        System.out.println("****************************************");
    }
}

public class BankY {
    private static List<Account> accounts = new ArrayList<>();
    private static final String DATA_FILE = "accounts.dat";

    public static void main(String[] args) {
        loadAccounts();
        Scanner sc = new Scanner(System.in);

        System.out.println("############################################");
        System.out.println("********** Welcome to Harish Banky **********");
        System.out.println("############################################");

        while (true) {
            System.out.println("\n############# Menu #############");
            System.out.println("* 1. Create Account             *");
            System.out.println("* 2. Deposit Funds              *");
            System.out.println("* 3. Withdraw Funds             *");
            System.out.println("* 4. Transfer Funds             *");
            System.out.println("* 5. Check Balance              *");
            System.out.println("* 6. Exit                       *");
            System.out.println("################################");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();  // Consume newline

            if (option == 1) {
                createAccount(sc);
            } else if (option == 2) {
                depositFunds(sc);
            } else if (option == 3) {
                withdrawFunds(sc);
            } else if (option == 4) {
                transferFunds(sc);
            } else if (option == 5) {
                checkBalance(sc);
            } else if (option == 6) {
                saveAccounts();
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();
    }

    private static void createAccount(Scanner sc) {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter account holder name: ");
        String accHolder = sc.nextLine();
        System.out.print("Enter security PIN: ");
        int securityPin = sc.nextInt();
        Account acc = new Account(accNum, accHolder, securityPin);
        accounts.add(acc);
        System.out.println("Account created successfully.");
    }

    private static void depositFunds(Scanner sc) {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc != null) {
            System.out.print("Enter security PIN: ");
            int pin = sc.nextInt();
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            acc.deposit(amount, pin);
            saveAccounts(); // Save after successful transaction
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds(Scanner sc) {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc != null) {
            System.out.print("Enter security PIN: ");
            int pin = sc.nextInt();
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount, pin);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferFunds(Scanner sc) {
        System.out.print("Enter your account number: ");
        String fromAccNum = sc.nextLine();
        Account fromAcc = findAccount(fromAccNum);

        if (fromAcc != null) {
            System.out.print("Enter security PIN: ");
            int pin = sc.nextInt();
            System.out.print("Enter the recipient's account number: ");
            String toAccNum = sc.nextLine();
            Account toAcc = findAccount(toAccNum);

            if (toAcc != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = sc.nextDouble();
                fromAcc.transfer(toAcc, amount, pin);
                saveAccounts();
            } else {
                System.out.println("Recipient's account not found.");
            }
        } else {
            System.out.println("Your account not found.");
        }
    }

    private static void checkBalance(Scanner sc) {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc != null) {
            System.out.print("Enter security PIN: ");
            int pin = sc.nextInt();
            if (acc.getSecurityPin() == pin) {
                System.out.println("Account holder: " + acc.getAccHolder());
                System.out.println("Balance: " + acc.getBalance());
            } else {
                System.out.println("Invalid security PIN.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
            System.out.println("Accounts saved.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                accounts = (List<Account>) obj;
                System.out.println("Accounts loaded.");
            } else {
                System.out.println("Invalid data format in file.");
            }
        } catch (IOException e) {
            System.out.println("No existing accounts found. Starting fresh.");
            accounts = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    private static Account findAccount(String accNum) {
        for (Account acc : accounts) {
            if (acc.getAccNum().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }
}

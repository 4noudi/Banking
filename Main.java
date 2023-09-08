import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        double amount;
        Account accountObj = new Account(0, "", 0);
        Deposit depositObj = new Deposit();
        Withdraw withdrawObj = new Withdraw();
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Menu: ");
            System.out.println("- Press 1 to enter account details");
            System.out.println("- Press 2 to deposit");
            System.out.println("- Press 3 to withdraw");
            System.out.println("- Press 4 to show current balance");
            System.out.println("- Press 5 to cancel last transaction");
            System.out.println("- Press 6 to exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> { //acc details
                    System.out.print("Enter Account Number: ");
                    accountObj.accountNumber = scan.nextInt();
                    System.out.print("Enter Owner Name: ");
                    accountObj.ownerName = scan.next();
                    System.out.print("Enter balance: ");
                    accountObj.balance = scan.nextDouble();
                }
                case 2 -> { //deposit
                    System.out.print("Enter the required amount to deposit: ");
                    depositObj.amount = scan.nextDouble();
                    System.out.println("New balance = " + depositObj.execute(accountObj));
                }
                case 3 -> { //withdraw
                    System.out.println("Enter the required amount to Withdraw: ");
                    withdrawObj.amount = scan.nextDouble();
                    System.out.println("New balance = " + withdrawObj.execute(accountObj));
                }
                case 4 -> { //current balance
                    System.out.println("Enter the required currency type 'U' for USD, 'E' for euro ");
                    String currency = scan.next();
                    BalanceInquiry obj = new BalanceInquiry(currency);
                    System.out.println(obj.execute(accountObj));
                }
                case 5 ->{//cancel last transaction
                    System.out.println("Last Transaction Canceled");
                    if(Transaction.transactionId == 1){
                        depositObj.cancelTransaction(accountObj);
                        System.out.println("New Balance = " + accountObj.balance );
                    }
                    else if (Transaction.transactionId == 2) {
                        withdrawObj.cancelTransaction(accountObj);
                        System.out.println("New Balance = " + accountObj.balance);
                    }
                }
                case 6 -> { //exit
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while (choice != 6);
    }
}

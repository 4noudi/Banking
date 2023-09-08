import java.util.Objects;

// Transaction interface
interface Transaction {
    double execute(Account account);
    double cancelTransaction(Account account);
}

// Deposit class
class Deposit implements Transaction {
    private double amount;

    public Deposit(double amount) {
        this.amount = amount;
    }

    @Override
    public double execute(Account account) {
        account.balance += amount;
        return account.getBalance();
    }

    @Override
    public double cancelTransaction(Account account) {
        account.balance -= amount;
        return account.getBalance();
    }
}

// Withdraw class
class Withdraw implements Transaction {
    private double amount;

    public Withdraw(double amount) {
        this.amount = amount;
    }

    @Override
    public double execute(Account account) {
        if (amount <= account.getBalance()) {
            account.balance -= amount;
        }
        return account.getBalance();
    }

    @Override
    public double cancelTransaction(Account account) {
        account.balance += amount;
        return account.getBalance();
    }
}

// BalanceInquiry class
class BalanceInquiry implements Transaction {
    private String currencyType;

    public BalanceInquiry(String currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public double execute(Account account) {
        // Assuming a fixed exchange rate for simplicity
        double exchangeRate = currencyType.equals("E") ? 0.9 : 1.0; // Euro to USD exchange rate
        return account.getBalance() * exchangeRate;
    }

    @Override
    public double cancelTransaction(Account account) {
        // Balance inquiry is a read-only operation and doesn't affect the account balance
        return account.getBalance();
    }
}

// TransactionHistory class to keep track of transactions
class TransactionHistory {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void cancelLastTransaction(Account account) {
        if (!transactions.isEmpty()) {
            Transaction lastTransaction = transactions.remove(transactions.size() - 1);
            lastTransaction.cancelTransaction(account);
        }
    }
}
/**
 * This class represents an account, consisting of a unique account number, a bank code, and its balance
 *
 * @author john
 * @version 1.0
 */

public class Account {
    private int accountNumber;
    private final int bankCode;
    private int balance;

    /**
     * Initializes the balance, bankcode, and account number
     *
     * @param bankCode is the bank's code
     * @param accountNumber the accounts specific ID
     */
    public Account(int bankCode, int accountNumber){
        this.bankCode = bankCode;
        this.balance = 1000;
        this.accountNumber = accountNumber;
    }

    /**
     * Method to withdraw money from an account's balance
     *
     * @param amount to withdraw
     * @return
     */
    public boolean withdraw(int amount){
        // ehe i did the thing
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    /**
     * Method to deposit money into the account
     *
     * @param amount to deposit
     */
    public void deposit(int amount){
        balance += amount;
    }

    /**
     * Method to transfer money between accounts
     *
     * @param account to deposit into
     * @param amount to deposit
     * @return
     */
    public boolean transfer(Account account, int amount){
        if (amount > balance) return false;
        // transfer
        balance -= amount;
        account.deposit(amount);
        return true;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}

/**
 * This class represents a bank with accounts and a unique bank code
 *
 * @author john
 * @version 1.0
 */

public class Bank {
    private int accNum = 0;
    private final int bankCode;
    private Account[] accounts = new Account[8];

    // i watched an alex lee vid for this :thumbs_up:

    /**
     * Initializes the bankcode
     *
     * @param bankCode unique bank ID
     */
    public Bank(int bankCode){
        this.bankCode = bankCode;
    }

    /**
     * Creates an account and adds it to the bank's accounts
     *
     * @return the account number
     */
    public int createAccount(){
        Account account = new Account(bankCode,accNum);
        if (accNum == accounts.length - 1){
            Account[] tmp = new Account[2*accounts.length];
            // copies old array into new one
            System.arraycopy(accounts, 0, tmp, 0, accounts.length);
            setAccounts(tmp);
        }

        // adds account to array AND assigns account number
        accounts[accNum] = account;
        accNum++;
        return accNum - 1;
    }

    /**
     * Removes an account
     *
     * @param accountNumber unique account ID
     * @return true if removed, false if not
     */
    public boolean removeAccount(int accountNumber){
        // bro SO MANY LOOPS T-T
        int loop = 0;
        // checks if account is in bank
        for (int i = 0; i < length(); i++){
            if (accounts[i] == null) continue;
            if (accounts[i].getAccountNumber() == accountNumber){
                // checks if can resize
                if (size() <= (length()/4) && length() > 8){
                    // create temp array half the size
                    Account[] tmp = new Account[(length())/2];
                    // adds to tmp array to eliminate empty gaps
                    for (int j = 0; j < length(); j++){
                        if (accounts[j] == null) continue;
                        tmp[loop] = accounts[j];
                        loop++;
                        }
                    setAccounts(tmp);
                    }
                accounts[i] = null;
                return true;
                }
            }
            return false;
        }

    /**
     * Checks to see if an account is in a bank
     *
     * @param accountNumber unique ID to check
     * @return true if there, false if not
     */
    public boolean containsAccount(int accountNumber){
        for (int i = 0; i < length(); i++){
            if (accounts[i] == null) continue; // prevent null pointer exception
            if (accounts[i].getAccountNumber() == accountNumber) return true; // checks if present
        }
        return false;
    }

    /**
     * Tranfers money between accounts
     *
     * @param fromAccountNumber take money
     * @param toAccountNumber put money
     * @param amount amount of money
     * @return true if transferred, false if not
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount){
        if (accounts[fromAccountNumber] == null || accounts[toAccountNumber] == null || amount > accounts[fromAccountNumber].getBalance()) return false;
        accounts[fromAccountNumber].withdraw(amount);
        accounts[toAccountNumber].deposit(amount);
        return true;

//        accounts[fromAccountNumber].transfer(accounts[toAccountNumber], amount);

    }

    /**
     * Returns the length of the accounts array
     *
     * @return length
     */
    public int length(){
        return accounts.length;
    }

    /**
     * Returns number of accounts
     *
     * @return num of accs
     */
    public int size(){
        int size = 0;
        for (int i = 0; i < accounts.length; i ++){
            if (accounts[i] != null) size++;
        }
        return size;
    }

    /**
     * Gets the account at the specific index from the array
     *
     * @param index place where the account is stored
     * @return the account
     */
    public Account getAccount(int index){
        if (accounts[index] == null) return null;
        return accounts[index];
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

}

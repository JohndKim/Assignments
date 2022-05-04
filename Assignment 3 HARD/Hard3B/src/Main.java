public class Main {
    public static void main(String[] args){

        // uh what are the sonarlint msg about "Replace this use of System.out or System.err by a logger" mean
        Bank bank = new Bank(123);
        int firstAccNum = bank.createAccount();
        int secondAccNum = bank.createAccount();
        System.out.println(firstAccNum); // 0
        System.out.println(secondAccNum); // 1

        System.out.println(bank.removeAccount(1)); // true (it is removed)
        System.out.println(bank.containsAccount(1)); // false (no longer there
        int thirdAccNum = bank.createAccount();
        System.out.println(thirdAccNum); // 2

        System.out.println("Accounts Array Length: " + bank.length()); // 8
        System.out.println("Number of Accounts: " + bank.size()); // 2
        System.out.println("Account: " + bank.getAccount(1)); // null (as we removed it)

        bank.getAccount(0).setBalance(1000);
        bank.getAccount(2).setBalance(1000);
        System.out.println("Account 0's Balance: " + bank.getAccount(0).getBalance()); // balance = 1000
        System.out.println("Account 2's Balance: " + bank.getAccount(2).getBalance()); // balance = 1000
        bank.internalBankTransfer(0, 2, 500);
        System.out.println("Account 0's Balance After Transfer: " + bank.getAccount(0).getBalance());
        System.out.println("Account 2's Balance After Transfer: " + bank.getAccount(2).getBalance());

        System.out.println("600 was withdrawn: " + bank.getAccount(0).withdraw(600)); // false as 600 < 500
        bank.getAccount(0).deposit(600);
        System.out.println("Balance after 600 deposited: " + bank.getAccount(0).getBalance()); // 500 + 600 = 1100
        System.out.println("Account 0's Account Number: " + bank.getAccount(0).getAccountNumber()); // 0
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        bank.createAccount();
        System.out.println(bank.size()); // 11
        System.out.println(bank.length()); // 16
        bank.removeAccount(10);
        bank.removeAccount(9);
        bank.removeAccount(8);
        bank.removeAccount(7);
        bank.removeAccount(6);
        bank.removeAccount(5);
        bank.removeAccount(4);
        bank.removeAccount(3);
        System.out.println(bank.size()); // 3
        System.out.println(bank.length()); // 8
    }
}

package br.com.rodrigodonizettio.week3.bank;

public class CheckingAccount {
    private String accountNumber;
    private Double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

package br.com.rodrigodonizettio.week3.bank;

import br.com.rodrigodonizettio.week3.bank.hardware.exception.HardwareException;
import br.com.rodrigodonizettio.week3.bank.remoteservice.CheckingAccountRemoteService;
import br.com.rodrigodonizettio.week3.bank.remoteservice.exception.RemoteServiceException;
import br.com.rodrigodonizettio.week3.bank.hardware.Hardware;

public class Atm {
    private Hardware hardware;
    private CheckingAccountRemoteService remoteService;
    private CheckingAccount checkingAccount;

    public Atm(Hardware hardware, CheckingAccountRemoteService remoteService) {
        this.hardware = hardware;
        this.remoteService = remoteService;
    }

    public String login() throws HardwareException, RemoteServiceException {
        String accountNumber = hardware.getCheckingAccountNumber();
        checkingAccount = remoteService.getData(accountNumber);
        return "Client authenticated successfully";
    }

    public String withdrawMoney(Double amount) throws HardwareException, RemoteServiceException {
        Double newBalance = checkingAccount.getBalance() - amount;

        if(newBalance >= 0) {
            hardware.unlockWithdrawMoney();
            checkingAccount.setBalance(newBalance);
            remoteService.updateData(checkingAccount);
            return "Take your money";
        }
        return "Insufficient balance";
    }

    public String depositMoney(Double amount) throws HardwareException, RemoteServiceException {
        hardware.scanMoneyEnvelope();

        Double newBalance = checkingAccount.getBalance() + amount;
        checkingAccount.setBalance(newBalance);
        remoteService.updateData(checkingAccount);

        return "Money deposited successfully";
    }

    public String checkBalance() {
        return String.format("The account balance is: R$%,.2f", checkingAccount.getBalance());
    }
}

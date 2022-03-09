package br.com.rodrigodonizettio.week3.bank.hardware.mock;

import br.com.rodrigodonizettio.week3.bank.hardware.exception.HardwareException;
import br.com.rodrigodonizettio.week3.bank.hardware.Hardware;

import java.util.Map;

import static br.com.rodrigodonizettio.week3.bank.hardware.mock.HardwareOperationEnum.*;

public class ManualHardwareMock implements Hardware {
    private final Map<HardwareOperationEnum, Boolean> willHardwareFailMap;
    private final String accountNumber;

    public ManualHardwareMock(Map<HardwareOperationEnum, Boolean> willHardwareFailMap, String accountNumber) {
        this.willHardwareFailMap = willHardwareFailMap;
        this.accountNumber = accountNumber;
    }

    @Override
    public String getCheckingAccountNumber() throws HardwareException {
        if(Boolean.TRUE.equals(willHardwareFailMap.get(LOGIN))) {
            throw new HardwareException("Getting Checking Account Number. Client couldn't be authenticated");
        }
        return accountNumber;
    }

    @Override
    public void unlockWithdrawMoney() throws HardwareException {
        if(Boolean.TRUE.equals(willHardwareFailMap.get(WITHDRAW))) {
            throw new HardwareException("Unlocking Withdraw Money. Rolling back withdraw");
        }
    }

    @Override
    public void scanMoneyEnvelope() throws HardwareException {
        if(Boolean.TRUE.equals(willHardwareFailMap.get(DEPOSIT))) {
            throw new HardwareException("Scanning Deposit Money Envelope. Rolling back deposit");
        }
    }
}

package br.com.rodrigodonizettio.week3.bank.hardware;

import br.com.rodrigodonizettio.week3.bank.hardware.exception.HardwareException;

public interface Hardware {
    String getCheckingAccountNumber() throws HardwareException;

    void unlockWithdrawMoney() throws HardwareException;

    void scanMoneyEnvelope() throws HardwareException;
}

package br.com.rodrigodonizettio.week3.bank.remoteservice;

import br.com.rodrigodonizettio.week3.bank.CheckingAccount;
import br.com.rodrigodonizettio.week3.bank.remoteservice.exception.RemoteServiceException;

public interface CheckingAccountRemoteService {
    CheckingAccount getData(String accountNumber) throws RemoteServiceException;

    void updateData(CheckingAccount checkingAccount) throws RemoteServiceException;
}

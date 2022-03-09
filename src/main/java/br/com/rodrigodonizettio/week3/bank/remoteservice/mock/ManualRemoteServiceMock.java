package br.com.rodrigodonizettio.week3.bank.remoteservice.mock;

import br.com.rodrigodonizettio.week3.bank.CheckingAccount;
import br.com.rodrigodonizettio.week3.bank.remoteservice.CheckingAccountRemoteService;
import br.com.rodrigodonizettio.week3.bank.remoteservice.exception.RemoteServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


public class ManualRemoteServiceMock implements CheckingAccountRemoteService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<CheckingAccount> checkingAccountDb;

    @Override
    public CheckingAccount getData(String accountNumber) throws RemoteServiceException {
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, CheckingAccount.class);
            checkingAccountDb = objectMapper.readValue(Paths.get("src/main/resources/checkingAccountsDb.json").toFile(), javaType);

            Optional<CheckingAccount> optionalCheckingAccount = checkingAccountDb.stream()
                    .filter(v -> accountNumber.equals(v.getAccountNumber()))
                    .findFirst();

            if(optionalCheckingAccount.isPresent()) {
                return optionalCheckingAccount.get();
            } else {
                throw new RemoteServiceException("Retrieve Checking Account");
            }
        } catch (IOException ioe) {
            throw new RemoteServiceException("Retrieve Checking Account. IOException");
        }
    }

    @Override
    public void updateData(CheckingAccount checkingAccount) throws RemoteServiceException {
        Optional<CheckingAccount> optionalCheckingAccount = checkingAccountDb.stream()
                .filter(v -> checkingAccount.getAccountNumber().equals(v.getAccountNumber()))
                .findFirst();

        if(optionalCheckingAccount.isPresent()) {
            optionalCheckingAccount
                    .get()
                    .setBalance(checkingAccount.getBalance());
        } else {
            throw new RemoteServiceException("Retrieve Checking Account");
        }
        //TODO: We could update data in "src/main/resources/checkingAccountsDb.json" file
    }
}

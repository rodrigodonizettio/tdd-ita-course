package br.com.rodrigodonizettio.week3.bank;

import br.com.rodrigodonizettio.week3.bank.remoteservice.CheckingAccountRemoteService;
import br.com.rodrigodonizettio.week3.bank.remoteservice.exception.RemoteServiceException;
import br.com.rodrigodonizettio.week3.bank.remoteservice.mock.ManualRemoteServiceMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CheckingAccountTest {
    CheckingAccountRemoteService remoteService = new ManualRemoteServiceMock();

    CheckingAccount checkingAccount;

    @BeforeEach
    void beforeEachTest() throws RemoteServiceException {
        checkingAccount = remoteService.getData("00000-1");
    }


    @Test
    void shouldRetrieveAccountNumberTest() {
        assertEquals("00000-1", checkingAccount.getAccountNumber());
    }

    @Test
    void shouldRetrieveBalanceTest() {
        assertEquals(456, checkingAccount.getBalance());
    }

    @Test
    void shouldUpdateBalanceTest() {
        checkingAccount.setBalance(457.0);
        assertEquals(457, checkingAccount.getBalance());
    }

    @Test
    void shouldThrowRemoteServiceExceptionForInvalidAccountNumberTest() {
        String invalidCheckingAccountNumber = "00000-2";
        Throwable exception = assertThrows(RemoteServiceException.class, () -> {
            remoteService.getData(invalidCheckingAccountNumber);
        });
        assertEquals(exception.getMessage(), "Something went wrong during Remote Service operation. " +
                "Operation: Retrieve Checking Account");
    }
}

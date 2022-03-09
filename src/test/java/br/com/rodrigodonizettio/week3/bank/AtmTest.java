package br.com.rodrigodonizettio.week3.bank;

import br.com.rodrigodonizettio.week3.bank.remoteservice.CheckingAccountRemoteService;
import br.com.rodrigodonizettio.week3.bank.remoteservice.exception.RemoteServiceException;
import br.com.rodrigodonizettio.week3.bank.remoteservice.mock.ManualRemoteServiceMock;
import br.com.rodrigodonizettio.week3.bank.hardware.Hardware;
import br.com.rodrigodonizettio.week3.bank.hardware.exception.HardwareException;
import br.com.rodrigodonizettio.week3.bank.hardware.mock.HardwareOperationEnum;
import br.com.rodrigodonizettio.week3.bank.hardware.mock.ManualHardwareMock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static br.com.rodrigodonizettio.week3.bank.hardware.mock.HardwareOperationEnum.DEPOSIT;
import static br.com.rodrigodonizettio.week3.bank.hardware.mock.HardwareOperationEnum.LOGIN;
import static br.com.rodrigodonizettio.week3.bank.hardware.mock.HardwareOperationEnum.WITHDRAW;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtmTest {
    private static final Logger log = LogManager.getLogger(AtmTest.class);

    private final Map<HardwareOperationEnum, Boolean> hardwareWorksMap = Map.of(
            LOGIN, false,
            WITHDRAW, false,
            DEPOSIT, false);

    private final Map<HardwareOperationEnum, Boolean> hardwareFailsLoginMap = Map.of(
            LOGIN, true,
            WITHDRAW, false,
            DEPOSIT, false);

    private final Map<HardwareOperationEnum, Boolean> hardwareFailsWithdrawMap = Map.of(
            LOGIN, false,
            WITHDRAW, true,
            DEPOSIT, false);

    private final Map<HardwareOperationEnum, Boolean> hardwareFailsDepositMap = Map.of(
            LOGIN, false,
            WITHDRAW, false,
            DEPOSIT, true);

    Hardware workingHardwareAccount0 = new ManualHardwareMock(hardwareWorksMap, "00000-0");
    Hardware workingHardwareAccount1 = new ManualHardwareMock(hardwareWorksMap, "00000-1");

    Hardware failingLoginHardwareAccount0 = new ManualHardwareMock(hardwareFailsLoginMap, "00000-0");
    Hardware failingWithdrawHardwareAccount0 = new ManualHardwareMock(hardwareFailsWithdrawMap, "00000-0");
    Hardware failingDepositHardwareAccount0 = new ManualHardwareMock(hardwareFailsDepositMap, "00000-0");

    CheckingAccountRemoteService remoteService = new ManualRemoteServiceMock();

    Atm atmMock = Mockito.mock(Atm.class);

    @Test
    void shouldMockAtmLoginMethodReturnUsingMockitoFrameworkTest() throws HardwareException, RemoteServiceException {
        when(atmMock.login()).thenReturn("Client authenticated successfully");
        String loginResult = atmMock.login();

        assertEquals("Client authenticated successfully", loginResult);
        verify(atmMock).login();
    }

    @Test
    void shouldMockAtmDepositMoneyMethodReturnUsingMockitoFrameworkTest() throws HardwareException, RemoteServiceException {
        when(atmMock.depositMoney(123.4)).thenReturn("Money deposited successfully");
        String depositResult = atmMock.depositMoney(123.4);

        assertEquals("Money deposited successfully", depositResult);
        verify(atmMock).depositMoney(123.4);
    }

    @Test
    void shouldLoginWhenHardwareWontFailTest() {
        Atm atm = new Atm(workingHardwareAccount0, remoteService);
        assertDoesNotThrow(() -> {
            String result = atm.login();
            assertEquals("Client authenticated successfully", result);
        });
    }

    @Test
    void shouldThrowHardwareExceptionDuringLoginTest() {
        Atm atm = new Atm(failingLoginHardwareAccount0, remoteService);
        Throwable exception = assertThrows(HardwareException.class, atm::login);
        assertEquals(exception.getMessage(), "Something went wrong during hardware operation. " +
                "Operation: Getting Checking Account Number. Client couldn't be authenticated");
    }

    @Test
    void shouldWithdrawMoneyWhenBalanceWillBePositiveTest() {
        Atm atm = new Atm(workingHardwareAccount1, remoteService);
        String result = null;
        try {
            atm.login();
            result = atm.withdrawMoney(123.4);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        assertEquals("Take your money", result);
    }

    @Test
    void shouldNotWithdrawMoneyWhenBalanceWillBeNegativeTest() {
        Atm atm = new Atm(workingHardwareAccount0, remoteService);
        String result = null;
        try {
            atm.login();
            result = atm.withdrawMoney(123.4);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        assertEquals("Insufficient balance", result);
    }

    @Test
    void shouldThrowHardwareExceptionDuringWithdrawMoneyTest() throws RemoteServiceException, HardwareException {
        Atm atm = new Atm(failingWithdrawHardwareAccount0, remoteService);

        atm.login();
        Throwable exception = assertThrows(HardwareException.class, () -> {
            atm.withdrawMoney(123.0);
        });
        assertEquals(exception.getMessage(), "Something went wrong during hardware operation. " +
                "Operation: Unlocking Withdraw Money. Rolling back withdraw");
    }

    @Test
    void shouldDepositMoneyTest() {
        Atm atm = new Atm(workingHardwareAccount0, remoteService);
        String result = null;
        try {
            atm.login();
            result = atm.depositMoney(123.4);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        assertEquals("Money deposited successfully", result);
    }

    @Test
    void shouldThrowHardwareExceptionDuringDepositMoneyTest() throws RemoteServiceException, HardwareException {
        Atm atm = new Atm(failingDepositHardwareAccount0, remoteService);

        atm.login();
        Throwable exception = assertThrows(HardwareException.class, () -> {
            atm.depositMoney(123.0);
        });
        assertEquals(exception.getMessage(), "Something went wrong during hardware operation. " +
                "Operation: Scanning Deposit Money Envelope. Rolling back deposit");
    }

    @Test
    void shouldCheckBalanceTest() {
        Atm atm = new Atm(workingHardwareAccount0, remoteService);
        String result = null;
        try {
            atm.login();
            result = atm.checkBalance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        assertEquals("The account balance is: R$123,00", result);
    }
}

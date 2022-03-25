package br.com.rodrigodonizettio.week4.gamescore;

import br.com.rodrigodonizettio.week3.bank.Atm;
import br.com.rodrigodonizettio.week3.bank.hardware.exception.HardwareException;
import br.com.rodrigodonizettio.week4.gamescore.exception.StorageServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class StorageTest {
    final String datasourcePath = "src/main/resources/gameScoreDb.json";
    Storage storage = new Storage(datasourcePath);

    Storage storageMock = Mockito.mock(Storage.class);

    @BeforeEach
    void beforeEachTest() throws IOException {
        resetDb();
    }

    @Test
    void shouldMockStorageRetrieveAllPlayersUsingMockitoFrameworkTest() throws StorageServiceException, IOException {
        when(storageMock.findAll()).thenReturn(resetDb());
        List<Player> allPlayers = storageMock.findAll();

        assertEquals(4, allPlayers.size());
    }

    @Test
    void shouldRetrieveAllPlayersFromDbTest() throws StorageServiceException {
        List<Player> allPlayers = storage.findAll();

        assertEquals(4, allPlayers.size());
    }

    @Test
    void shouldMockStorageRetrievePlayerYScorePointsUsingMockitoFrameworkTest() throws StorageServiceException {
        String playerName = "guerra";
        String pointType = "Y";

        Map<String, Integer> player1ScoreMap = new HashMap<>();
        player1ScoreMap.put("B", 1);
        player1ScoreMap.put("Y", 1);
        player1ScoreMap.put("X", 1);
        player1ScoreMap.put("A", 1);
        Player player1 = new Player(playerName, player1ScoreMap);

        when(storageMock.findPlayerByName(playerName)).thenReturn(player1);
        Player player = storageMock.findPlayerByName(playerName);

        assertEquals(1, player.getScoreMap().get(pointType));
    }

    @Test
    void shouldRetrievePlayerYScorePointsTest() throws StorageServiceException {
        String playerName = "guerra";
        String pointType = "Y";

        storage.findPlayerByName(playerName);
        Player player = storage.findPlayerByName(playerName);

        assertEquals(1, player.getScoreMap().get(pointType));
    }

    @Test
    void shouldMockStorageUpdatePlayer10BScorePointsUsingMockitoFrameworkTest() throws StorageServiceException, IOException {
        String playerName = "doni";
        String pointType = "B";

        Map<String, Integer> player2ScoreMap = new HashMap<>();
        player2ScoreMap.put("B", 10);
        player2ScoreMap.put("Y", 2);
        player2ScoreMap.put("X", 2);
        player2ScoreMap.put("A", 2);
        Player player2 = new Player(playerName, player2ScoreMap);

        when(storageMock.update(playerName, pointType, 10)).thenReturn(player2);
        Player updatedPlayer = storageMock.update(playerName, pointType, 10);

        assertEquals(10, updatedPlayer.getScoreMap().get(pointType));
    }

    @Test
    void shouldUpdatePlayer10BScorePointsTest() throws StorageServiceException, IOException {
        String playerName = "doni";
        String pointType = "B";

        storage.update(playerName, pointType, 10);
        Player updatedPlayer = storage.findPlayerByName(playerName);

        assertEquals(10, updatedPlayer.getScoreMap().get(pointType));
    }

    @Test
    void shouldMockStorageRetrieveEveryPlayerWithAnyValidScoreUsingMockitoFrameworkTest() throws StorageServiceException, IOException {
        List<Player> validPlayers = new ArrayList<>(resetDb());
        validPlayers.remove(2);

        when(storageMock.findAllValid()).thenReturn(validPlayers);
        List<Player> allValidPlayers = storageMock.findAllValid();

        assertEquals(3, allValidPlayers.size());
    }

    @Test
    void shouldRetrieveEveryPlayerWithAnyValidScorePointTest() throws StorageServiceException {
        List<Player> allValidPlayers = storage.findAllValid();

        assertEquals(3, allValidPlayers.size());
    }

    @Test
    void shouldMockStorageRetrieveEveryValidScorePointBySpecificUserUsingMockitoFrameworkTest() throws StorageServiceException {
        Map<String, Integer> player4ScoreMap = new HashMap<>();
        player4ScoreMap.put("B", 3);
        Player player4 = new Player("tti", player4ScoreMap);

        when(storageMock.findAllValidScorePointsByPlayerName("tti")).thenReturn(player4.getScoreMap());
        Map<String, Integer> validPlayerScorePoints = storageMock.findAllValidScorePointsByPlayerName("tti");

        assertEquals(1, validPlayerScorePoints.size());
        assertEquals(3, validPlayerScorePoints.get("B"));
    }

    @Test
    void shouldRetrieveEveryValidScorePointBySpecificUserTest() throws StorageServiceException {
        Map<String, Integer> validPlayerScorePoints = storage.findAllValidScorePointsByPlayerName("tti");

        assertEquals(1, validPlayerScorePoints.size());
        assertEquals(3, validPlayerScorePoints.get("B"));
    }

    @Test
    void shouldRetrieveAllValidByPointTypeDescendingTest() throws StorageServiceException {
        String pointType = "B";
        List<Player> allValidPlayersByPointTypeSortedDesc = storage.findAllValidByPointTypeDesc(pointType);

        Integer playerIndex0Score = allValidPlayersByPointTypeSortedDesc.get(0).getScoreMap().get(pointType);
        Integer playerIndex1Score = allValidPlayersByPointTypeSortedDesc.get(1).getScoreMap().get(pointType);
        Integer playerIndex2Score = allValidPlayersByPointTypeSortedDesc.get(2).getScoreMap().get(pointType);

        assertTrue(playerIndex0Score > playerIndex1Score);
        assertTrue(playerIndex0Score > playerIndex2Score);
        assertTrue(playerIndex1Score > playerIndex2Score);
    }

    @Test
    void shouldThrowStorageServiceExceptionDuringFindAllPlayersTest() throws StorageServiceException {
        String failedOperation = "Retrieve Users. IOException";
        when(storageMock.findAll()).thenThrow(new StorageServiceException(failedOperation));

        Throwable exception = assertThrows(StorageServiceException.class, storageMock::findAll);
        assertEquals(exception.getMessage(), "Something went wrong during Storage Service operation. " +
                "Operation: Retrieve Users. IOException");
    }

    private List<Player> resetDb() throws IOException {
        Map<String, Integer> player1ScoreMap = new HashMap<>();
        player1ScoreMap.put("B", 1);
        player1ScoreMap.put("Y", 1);
        player1ScoreMap.put("X", 1);
        player1ScoreMap.put("A", 1);
        Player player1 = new Player("guerra", player1ScoreMap);

        Map<String, Integer> player2ScoreMap = new HashMap<>();
        player2ScoreMap.put("B", 2);
        player2ScoreMap.put("Y", 2);
        player2ScoreMap.put("X", 2);
        player2ScoreMap.put("A", 2);
        Player player2 = new Player("doni", player2ScoreMap);

        Map<String, Integer> player3ScoreMap = new HashMap<>();
        player3ScoreMap.put("B", 0);
        player3ScoreMap.put("Y", 0);
        player3ScoreMap.put("X", 0);
        player3ScoreMap.put("A", 0);
        Player player3 = new Player("ze", player3ScoreMap);

        Map<String, Integer> player4ScoreMap = new HashMap<>();
        player4ScoreMap.put("B", 3);
        player4ScoreMap.put("Y", 0);
        player4ScoreMap.put("X", 0);
        player4ScoreMap.put("A", 0);
        Player player4 = new Player("tti", player4ScoreMap);

        List<Player> defaultDb = List.of(player1, player2, player3, player4);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(datasourcePath).toFile(), defaultDb);

        return defaultDb;
    }
}

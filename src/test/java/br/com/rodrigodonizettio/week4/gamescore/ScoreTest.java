package br.com.rodrigodonizettio.week4.gamescore;

import br.com.rodrigodonizettio.week4.gamescore.exception.StorageServiceException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ScoreTest {
    Storage storageMock = Mockito.mock(Storage.class);
    Score scoreUsingStorageMock = new Score(storageMock);

    final String datasourcePath = "src/main/resources/gameScoreDb.json";
    Storage storage = new Storage(datasourcePath);
    Score score = new Score(storage);

    @Test
    void shouldUpdatePlayerPointsTest() throws StorageServiceException, IOException {
        String playerName = "doni";
        String pointType = "B";

        when(storageMock.findPlayerByName(playerName)).thenReturn(getPlayerDoni());
        Player playerDoni = scoreUsingStorageMock.findPlayerByName(playerName);

        assertEquals(2, playerDoni.getScoreMap().get(pointType));

        when(storageMock.update(playerName, pointType, 10)).thenReturn(getUpdatedPlayerDoni());
        playerDoni = scoreUsingStorageMock.update(playerName, pointType, 10);

        assertEquals(10, playerDoni.getScoreMap().get(pointType));
    }

    @Test
    void shouldRetrieveValidPlayerPointsTest() throws StorageServiceException {
        String playerName = "guerra";
        when(storageMock.findAllValidScorePointsByPlayerName(playerName)).thenReturn(getPlayerGuerraValidScorePoints());
        Map<String, Integer> playerGuerraScorePoints = scoreUsingStorageMock.findAllValidScorePointsByPlayerName(playerName);

        assertEquals(2, playerGuerraScorePoints.size());
        assertTrue(playerGuerraScorePoints.containsKey("A"));
        assertTrue(playerGuerraScorePoints.containsKey("B"));
        assertTrue(playerGuerraScorePoints.containsValue(100));
    }

    @Test
    void shouldRetrieveDescSortedValidPlayerPointsByPointTypeTest() throws StorageServiceException {
        String pointType = "B";
        when(storageMock.findAllValidByPointTypeDesc(pointType)).thenReturn(getValidPlayerListByPointTypedDesc());
        List<Player> allValidPlayersByPointTypeSortedDesc = scoreUsingStorageMock.findAllValidScorePointsDesc("B");

        Integer playerIndex0Score = allValidPlayersByPointTypeSortedDesc.get(0).getScoreMap().get(pointType);
        Integer playerIndex1Score = allValidPlayersByPointTypeSortedDesc.get(1).getScoreMap().get(pointType);
        Integer playerIndex2Score = allValidPlayersByPointTypeSortedDesc.get(2).getScoreMap().get(pointType);

        assertTrue(playerIndex0Score > playerIndex1Score);
        assertTrue(playerIndex0Score > playerIndex2Score);
        assertTrue(playerIndex1Score > playerIndex2Score);
    }

    @Test
    @Tag("integration")
    void shouldRetrieveAllPlayersFromDbTest() throws StorageServiceException {
        List<Player> allPlayersInDb = score.findAll();

        assertEquals(4, allPlayersInDb.size());
    }

    private Player getPlayerDoni() {
        Map<String, Integer> player2ScoreMap = new HashMap<>();
        player2ScoreMap.put("B", 2);
        player2ScoreMap.put("Y", 2);
        player2ScoreMap.put("X", 2);
        player2ScoreMap.put("A", 2);
        return new Player("doni", player2ScoreMap);
    }

    private Player getUpdatedPlayerDoni() {
        Map<String, Integer> player2ScoreMap = new HashMap<>();
        player2ScoreMap.put("B", 10);
        player2ScoreMap.put("Y", 2);
        player2ScoreMap.put("X", 2);
        player2ScoreMap.put("A", 2);
        return new Player("doni", player2ScoreMap);
    }

    private Map<String, Integer> getPlayerGuerraValidScorePoints() {
        Map<String, Integer> player1ScoreMap = new HashMap<>();
        player1ScoreMap.put("B", 100);
        player1ScoreMap.put("A", 100);
        return new Player("guerra", player1ScoreMap).getScoreMap();
    }

    private List<Player> getValidPlayerListByPointTypedDesc() {
        Map<String, Integer> player4ScoreMap = new HashMap<>();
        player4ScoreMap.put("B", 3);
        Player player4 = new Player("tti", player4ScoreMap);

        Map<String, Integer> player1ScoreMap = new HashMap<>();
        player1ScoreMap.put("B", 1);
        Player player1 = new Player("guerra", player1ScoreMap);

        Map<String, Integer> player2ScoreMap = new HashMap<>();
        player2ScoreMap.put("B", 2);
        Player player2 = new Player("doni", player2ScoreMap);

        return List.of(player4, player2, player1);
    }
}

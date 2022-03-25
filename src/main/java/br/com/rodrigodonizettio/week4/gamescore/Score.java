package br.com.rodrigodonizettio.week4.gamescore;

import br.com.rodrigodonizettio.week4.gamescore.exception.StorageServiceException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Score {
    private Storage storage;

    public Score(Storage storage) {
        this.storage = storage;
    }

    public List<Player> findAll() throws StorageServiceException {
        return storage.findAll();
    }

    public Player findPlayerByName(String playerName) throws StorageServiceException {
        return storage.findPlayerByName(playerName);
    }

    public Player update(String playerName, String pointType, Integer newScore) throws StorageServiceException, IOException {
        return storage.update(playerName, pointType, newScore);
    }

    public List<Player> findAllValid() throws StorageServiceException {
        return storage.findAllValid();
    }

    public Map<String, Integer> findAllValidScorePointsByPlayerName(String playerName) throws StorageServiceException {
        return storage.findAllValidScorePointsByPlayerName(playerName);
    }

    public List<Player> findAllValidScorePointsDesc(String pointType) throws StorageServiceException {
        return storage.findAllValidByPointTypeDesc(pointType);
    }
}

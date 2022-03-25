package br.com.rodrigodonizettio.week4.gamescore;

import br.com.rodrigodonizettio.week4.gamescore.exception.StorageServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Storage {
    private final String datasourcePath;
    private List<Player> gameScoreDb;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Storage(String datasourcePath) {
        this.datasourcePath = datasourcePath;
    }

    public List<Player> findAll() throws StorageServiceException {
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Player.class);
            gameScoreDb = objectMapper.readValue(Paths.get(datasourcePath).toFile(), javaType);

            return gameScoreDb;
        } catch (IOException ioe) {
            throw new StorageServiceException("Retrieve Users. IOException");
        }
    }

    public Player findPlayerByName(String playerName) throws StorageServiceException {
        gameScoreDb = findAll();

        Optional<Player> optionalPlayer = gameScoreDb.stream()
                .filter(v -> playerName.equals(v.getName()))
                .findFirst();

        if(optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            throw new StorageServiceException(String.format("Retrieve User: %s", playerName));
        }
    }

    public Player update(String playerName, String pointType, Integer newScore) throws StorageServiceException, IOException {
        Player player = findPlayerByName(playerName);

        Map<String, Integer> scoreMap = player.getScoreMap();
        scoreMap.replace(pointType, newScore);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(datasourcePath).toFile(), gameScoreDb);

        return player;
    }

    public List<Player> findAllValid() throws StorageServiceException {
        gameScoreDb = findAll();

        return gameScoreDb.stream()
                .filter(v -> v.getScoreMap().get("B") != 0 ||
                        v.getScoreMap().get("Y") != 0 ||
                        v.getScoreMap().get("X") != 0 ||
                        v.getScoreMap().get("A") != 0)
                .collect(Collectors.toList());
    }

    public List<Player> findAllValidByPointTypeDesc(String pointType) throws StorageServiceException {
        List<Player> allValid = findAllValid();
        removeUndesiredScoreByPointType(allValid, pointType);
        return sortDescendingByScorePoint(allValid, pointType);
    }

    private List<Player> sortDescendingByScorePoint(List<Player> allValid, String pointType) {
        return allValid.stream()
                .sorted((o1, o2) -> {
                    if(o1.getScoreMap().get(pointType) == o2.getScoreMap().get(pointType))
                        return o1.getName().compareTo(o2.getName());
                    else if(o1.getScoreMap().get(pointType) > o2.getScoreMap().get(pointType))
                        return -1;
                    else return 1;
                })
                .collect(Collectors.toList());
    }

    private void removeUndesiredScoreByPointType(List<Player> allValid, String pointType) {
        allValid.forEach(v -> v.getScoreMap().entrySet().removeIf(e -> !pointType.equals(e.getKey())));
    }

    public Map<String, Integer> findAllValidScorePointsByPlayerName(String playerName) throws StorageServiceException {
        Map<String, Integer> validScoreMap = new HashMap<>();

        Player player = findPlayerByName(playerName);

        player.getScoreMap().forEach((k, v) -> {
            if(v != 0) {
                validScoreMap.put(k, v);
            }
        });
        return validScoreMap;
    }
}

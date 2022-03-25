package br.com.rodrigodonizettio.week4.gamescore;

import java.util.Map;

public class Player {
    private String name;
    private Map<String, Integer> scoreMap;

    public Player() { }

    public Player(String name, Map<String, Integer> scoreMap) {
        this.name = name;
        this.scoreMap = scoreMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }
}

package iva.MODELS;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game {
    //properties
    private String dateOfTheGame;
    private String gameInfo;
    private double playerFantasyPoint;

    //constructor

    public Game(String dateOfTheGame, String gameInfo, double playerFantasyPoint) {
        this.dateOfTheGame = dateOfTheGame;
        this.gameInfo = gameInfo;
        this.playerFantasyPoint = playerFantasyPoint;
    }


    //Methods

    @Override
    public String toString() {
        return dateOfTheGame+", "+ gameInfo + ", "+playerFantasyPoint+"\n";
    }

    //getters and setters
    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public double getPlayerFantasyPoint() {
        return playerFantasyPoint;
    }

    public void setPlayerFantasyPoint(double playerFantasyPoint) {
        this.playerFantasyPoint = playerFantasyPoint;
    }

    public String getDateOfTheGame() {
        return dateOfTheGame;
    }

    public void setDateOfTheGame(String dateOfTheGame) {
        this.dateOfTheGame = dateOfTheGame;
    }
}

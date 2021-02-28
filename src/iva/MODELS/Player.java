package iva.MODELS;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Player {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private SimpleStringProperty playerName;
    private SimpleStringProperty teamName; // team player played for
    private SimpleStringProperty matchUP;
    private LocalDate dateOfTheGame;
    private SimpleStringProperty teamWinOrLoose;
    private SimpleStringProperty MIN; // minute played
    private int PTS = 0; // podoubles
    private int FGM; // field goal made
    private int FGA; // field goal attended
    private double FGPercent; // field goal percentage
    private int ThreePM; // 3 point made
    private int ThreePA; // 3 point attended
    private double ThreePercent; // 3 point percentage
    private int FTM; // FREE throws point made
    private int FTA; // FREE throws point attended
    private double FTPercent; // FREE throws percentage
    private int OREB; // offensive rebounds
    private int DREB; // defensive rebounds
    private int REBTotal = 0; // totals rebounds
    private int AST = 0; // assist
    private int STL = 0; // steals
    private int BLK = 0; // block
    private int TO = 0; // turn over
    private int PF; // personal foul
    private int plusMinus; // plusMinus (+/-)
    private double FAN; // fantasy podouble

    private ObservableList<Game> allPlayerFanPTS = FXCollections.observableArrayList();

    //constructor
    public Player() { }

    //Methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return  Objects.equals(playerName, player.playerName);
    }



    //getters and setters

    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = new SimpleStringProperty(playerName);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = new SimpleStringProperty(teamName);
    }

    public String getMatchUP() {
        return matchUP.get();
    }

    public SimpleStringProperty matchUPProperty() {
        return matchUP;
    }

    public void setMatchUP(String matchUP) {
        this.matchUP= new SimpleStringProperty(matchUP);
    }

    public LocalDate getDateOfTheGame() {
        return dateOfTheGame;
    }

    public void setDateOfTheGame(String dateOfTheGame) {
        this.dateOfTheGame = LocalDate.parse(dateOfTheGame,formatter);
    }

    public String getTeamWinOrLoose() {
        return teamWinOrLoose.get();
    }

    public SimpleStringProperty teamWinOrLooseProperty() {
        return teamWinOrLoose;
    }

    public void setTeamWinOrLoose(String teamWinOrLoose) {
        this.teamWinOrLoose = new SimpleStringProperty(teamWinOrLoose);
    }

    public String getMIN() {
        return MIN.get();
    }

    public SimpleStringProperty MINProperty() {
        return MIN;
    }

    public void setMIN(String MIN) {
        this.MIN = new SimpleStringProperty(MIN);
    }

    public int getPTS() {
        return PTS;
    }

    public void setPTS(int PTS) {
        this.PTS = PTS;
    }

    public int getFGM() {
        return FGM;
    }

    public void setFGM(int FGM) {
        this.FGM = FGM;
    }

    public int getFGA() {
        return FGA;
    }

    public void setFGA(int FGA) {
        this.FGA = FGA;
    }

    public double getFGPercent() {
        return FGPercent;
    }

    public void setFGPercent(double FGPercent) {
        this.FGPercent = FGPercent;
    }

    public int getThreePM() {
        return ThreePM;
    }

    public void setThreePM(int threePM) {
        ThreePM = threePM;
    }

    public int getThreePA() {
        return ThreePA;
    }

    public void setThreePA(int threePA) {
        ThreePA = threePA;
    }

    public double getThreePercent() {
        return ThreePercent;
    }

    public void setThreePercent(double threePercent) {
        ThreePercent = threePercent;
    }

    public int getFTM() {
        return FTM;
    }

    public void setFTM(int FTM) {
        this.FTM = FTM;
    }

    public int getFTA() {
        return FTA;
    }

    public void setFTA(int FTA) {
        this.FTA = FTA;
    }

    public double getFTPercent() {
        return FTPercent;
    }

    public void setFTPercent(double FTPercent) {
        this.FTPercent = FTPercent;
    }

    public int getOREB() {
        return OREB;
    }

    public void setOREB(int OREB) {
        this.OREB = OREB;
    }

    public int getDREB() {
        return DREB;
    }

    public void setDREB(int DREB) {
        this.DREB = DREB;
    }

    public int getREBTotal() {
        return REBTotal;
    }

    public void setREBTotal(int REBTotal) {
        this.REBTotal = REBTotal;
    }

    public int getAST() {
        return AST;
    }

    public void setAST(int AST) {
        this.AST = AST;
    }

    public int getSTL() {
        return STL;
    }

    public void setSTL(int STL) {
        this.STL = STL;
    }

    public int getBLK() {
        return BLK;
    }

    public void setBLK(int BLK) {
        this.BLK = BLK;
    }

    public int getTO() {
        return TO;
    }

    public void setTO(int TO) {
        this.TO = TO;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF) {
        this.PF = PF;
    }

    public int getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(int plusMinus) {
        this.plusMinus = plusMinus;
    }

    public double getFAN() {
        return FAN;
    }

    public void setFAN(double FAN) {
        this.FAN = FAN;
    }

    public ObservableList<Game> getAllPlayerFanPTS() {
        return allPlayerFanPTS;
    }

    public void setAllPlayerFanPTS(ObservableList<Game> allPlayerFanPTS) {
        this.allPlayerFanPTS = allPlayerFanPTS;
    }
}

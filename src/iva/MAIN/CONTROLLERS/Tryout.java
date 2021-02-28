package iva.MAIN.CONTROLLERS;

import iva.DAO.PlayerDAO;
import iva.DbUtility.DbConnection;
import iva.MODELS.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class Tryout {
    public static void main(String[] args) {
        PlayerDAO playerDAO = DbConnection.getPlayerDAO();
        ObservableList<Player> playerObservableList = playerDAO.getPlayerStats("BKN");
        for(Player p: playerObservableList){
            System.out.println(p.getPlayerName() +"-> \n"+p.getAllPlayerFanPTS());
        }
    }
}

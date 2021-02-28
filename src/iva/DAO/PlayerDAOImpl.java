package iva.DAO;

import iva.DbUtility.DbConnection;
import iva.MODELS.Game;
import iva.MODELS.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


public class PlayerDAOImpl implements PlayerDAO{
    Connection connection = null;	// Our connection to the database
    PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
    ResultSet rs = null;
    /*------------------------------------------------------------------------------------------------*/

    @Override
    public boolean addListOfPlayers(ObservableList<Player> playersStatsList) {
        int count = 0;
        System.out.println("-------------==--------------- playersStatsList size is " +playersStatsList.size());

        boolean checkIfPlayersWasAdded;

            for (Player p : playersStatsList){
                checkIfPlayersWasAdded = addPlayerStat(p);
                System.out.println("hello from addListOfPlayers ading player is "+checkIfPlayersWasAdded );
                if (checkIfPlayersWasAdded) count++;
            }

        return count == playersStatsList.size();
    }

    private boolean addPlayerStat(Player player) {
        try {
            connection = DbConnection.establishConnection(); // establish a connection

            String playerSQL = "insert into fantasyProjectDB.player(playerName, homeTeam, matchUP, dateOfTheGame, teamWinOrLoose, MINI, PTS, FGM, FGA, FGPercent, ThreePM, ThreePA, ThreePercent, FTM, FTA, FTPercent, OREB, DREB, REBTotal, AST, STL, BLK, TurnO, PF, plusMinus, FAN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            stmt = connection.prepareStatement(playerSQL);

            //set all ? before we execute
            stmt.setString(1,player.getPlayerName());
            stmt.setString(2,player.getTeamName());
            stmt.setString(3,player.getMatchUP());
            stmt.setObject(4,player.getDateOfTheGame());
            stmt.setString(5,player.getTeamWinOrLoose());
            stmt.setString(6,player.getMIN());
            stmt.setInt(7,player.getPTS());
            stmt.setInt(8,player.getFGM());
            stmt.setInt(9,player.getFGA());
            stmt.setDouble(10,player.getFGPercent());
            stmt.setInt(11,player.getThreePM());
            stmt.setInt(12,player.getThreePA());
            stmt.setDouble(13,player.getThreePercent());
            stmt.setInt(14,player.getFTM());
            stmt.setInt(15,player.getFTA());
            stmt.setDouble(16,player.getFTPercent());
            stmt.setInt(17,player.getOREB());
            stmt.setInt(18,player.getDREB());
            stmt.setDouble(19,player.getREBTotal());
            stmt.setInt(20,player.getAST());
            stmt.setInt(21,player.getSTL());
            stmt.setInt(22,player.getBLK());
            stmt.setInt(23,player.getTO());
            stmt.setInt(24,player.getPF());
            stmt.setInt(25,player.getPlusMinus());
            stmt.setDouble(26,player.getFAN());

            return stmt.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DbConnection.closeResources(connection,stmt);
        }
    }

    public ObservableList<Player> getPlayerStats(String teamName){
        ArrayList<Player> players = null;
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList();
        try {
            connection = DbConnection.establishConnection(); // establish a connection
            String sql = "select playerName from fantasyProjectDB.player where homeTeam = ? order by dateOfTheGame  desc;"; //get all players name for a specify team
            stmt = connection.prepareStatement(sql);

            stmt.setString(1,teamName);
            rs = stmt.executeQuery();

//            System.out.println("result set is "+rs.next());
            while (rs.next()){
                Player player = new Player();
                String playerName = rs.getString("playerName");
                player.setPlayerName(playerName);
                playerObservableList.add(player);
            }
            players = playerObservableList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Player::getPlayerName))), ArrayList::new));

            for (Player p : players){
                ObservableList<Game> gameInfoList = getGetAllPlayerFanPTS(p);
                p.setAllPlayerFanPTS(gameInfoList);
//                System.out.println(p.getPlayerName() +"-> game info size is "+gameInfoList.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(connection,stmt);
        }
        playerObservableList = FXCollections.observableArrayList(players);

        return playerObservableList;
    }

    private ObservableList<Game> getGetAllPlayerFanPTS(Player p) {
        ObservableList<Game> gameInfoList = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        try {
            connection = DbConnection.establishConnection(); // establish a connection
            String sql = "select dateOfTheGame, matchUP, teamWinOrLoose, FAN from fantasyProjectDB.player Where playerName = ? order by dateOfTheGame  desc;"; //get all players name for a specify team
            stmt = connection.prepareStatement(sql);

            stmt.setString(1,p.getPlayerName());
            rs = stmt.executeQuery();

//            System.out.println("result set is "+rs.next());
            while (rs.next()){
                String dateOfGame = rs.getTimestamp("dateOfTheGame").toLocalDateTime().toLocalDate().format(formatter);
                String gameInfo = rs.getString("matchUP")+", ("+rs.getString("teamWinOrLoose")+")";
                double fanPTS = rs.getDouble("FAN");
                Game g = new Game(dateOfGame, gameInfo,fanPTS);
                gameInfoList.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeResources(connection,stmt);
        }
        return gameInfoList;
    }
}

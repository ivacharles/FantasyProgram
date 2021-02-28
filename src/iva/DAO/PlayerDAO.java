package iva.DAO;

import iva.MODELS.Player;
import javafx.collections.ObservableList;

public interface PlayerDAO {
    boolean addListOfPlayers(ObservableList<Player> playersStatsList);
    ObservableList<Player> getPlayerStats( String teamName);
}

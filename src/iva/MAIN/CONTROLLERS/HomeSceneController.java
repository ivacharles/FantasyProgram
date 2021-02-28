package iva.MAIN.CONTROLLERS;


import iva.DAO.PlayerDAO;
import iva.DbUtility.DbConnection;
import iva.MODELS.Game;
import iva.MODELS.Player;
import javafx.beans.binding.Bindings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class HomeSceneController implements Initializable {
    //"ATL	- Atlanta Hawks", "BOS	- Boston Celtics", "CHA	- Charlotte Hornets", "CHI	- Chicago Bulls", "CLE	- Cleveland Cavaliers", "DAL	- Dallas Mavericks", "DEN	- Denver Nuggets", "DET	- Detroit Pistons", "GSW	- Golden State Warriors", "HOU    - Houston Rockets", "IND	- Indiana Pacers", "LAC    - Los Angeles Clippers", "LAL	- Los Angeles Lakers", "MEM	- Memphis Grizzlies", "MIA	- Miami Heat", "MIL	- Milwaukee Bucks", "MIN	- Minnesota Timberwolves", "NOH	- New Orleans Pelicans", "NYK	- New York Knicks", "BKN	- Brooklyn Nets", "OKC	- Oklahoma City Thunder", "ORL	- Orlando Magic", "PHI	- Philadelphia 76ers", "PHO	- Phoenix Suns", "POR	- Portland Trail Blazers", "SAC	- Sacramento Kings", "TOR	- Toronto Raptors", "UTH	- Utah Jazz", "WAS	- Washington Wizards"

    private final String[] teamNames = {"ATL", "BOS", "CHA", "CHI", "CLE", "DAL", "DEN", "DET", "GSW", "HOU", "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN", "NOP", "NYK", "BKN", "OKC", "ORL", "PHI", "PHO", "POR", "SAC","SAS", "TOR", "UTA", "WAS",};

    private final int maxNumberOfTeam2Show = 10;
    @FXML
    private Label wasGameStatsSavedProperly;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableView<Player> playerTableView;
    @FXML
    private ListView<String> listView;

    @FXML
    private void OnButtonPressedGetData(ActionEvent actionEvent) throws IOException {
        PlayerDAO playerDAO = DbConnection.getPlayerDAO();

        File submittedFilePath = getFileAbsPathFromStage(actionEvent); // get file path from the stage

        ObservableList<Player> playersStatsList = getPlayerStats4GameFromFilePath(submittedFilePath);
        System.out.println("Hello here from OnButtonPressedGetData METHOD ");


        if (playerDAO.addListOfPlayers(playersStatsList)) {
            wasGameStatsSavedProperly.setText("Stats was saved.");
        } else {
            wasGameStatsSavedProperly.setText("Something went wrong");
        }
    }

    @FXML
    private void OnGOButtonPushed(ActionEvent event) throws IOException {
        ObservableList<String> listOfSelectedListView = listView.getSelectionModel().getSelectedItems();
            initData(listOfSelectedListView);
    }

    private File getFileAbsPathFromStage(javafx.event.ActionEvent actionEvent) {
        //get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        File filePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.csv"));
        File selectFile = fileChooser.showOpenDialog(window);
        if (selectFile != null) {
            filePath = selectFile.getAbsoluteFile();
            System.out.println("Hello here form getFileAbsPathFromStage METHOD, the file name is " + filePath);
        }
        return filePath;
    }

    private ObservableList<Player> getPlayerStats4GameFromFilePath(File submittedFile) throws IOException {
        ObservableList<Player> playerList = FXCollections.observableArrayList();
        Player player;
        String line = "";
        try (Scanner scanner = new Scanner(submittedFile)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                player = getRecordFromLine(line);
                playerList.add(player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(teamNames);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wasGameStatsSavedProperly.setText("");
    }

    private static Player getRecordFromLine(String line) {
        Player player = new Player();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                player.setPlayerName(rowScanner.next());
                player.setTeamName(rowScanner.next());
                player.setMatchUP(rowScanner.next());
                player.setDateOfTheGame(rowScanner.next());
                player.setTeamWinOrLoose(rowScanner.next());
                player.setMIN(rowScanner.next());
                player.setPTS(Integer.parseInt(rowScanner.next()));

                player.setFGM(Integer.parseInt(rowScanner.next()));
                player.setFGA(Integer.parseInt(rowScanner.next()));
                player.setFGPercent(Double.parseDouble(rowScanner.next()));

                player.setThreePM(Integer.parseInt(rowScanner.next()));
                player.setThreePA(Integer.parseInt(rowScanner.next()));
                player.setThreePercent(Double.parseDouble(rowScanner.next()));


                player.setFTM(Integer.parseInt(rowScanner.next()));
                player.setFTA(Integer.parseInt(rowScanner.next()));
                player.setFTPercent(Double.parseDouble(rowScanner.next()));

                player.setOREB(Integer.parseInt(rowScanner.next()));
                player.setDREB(Integer.parseInt(rowScanner.next()));
                player.setREBTotal(Integer.parseInt(rowScanner.next()));

                player.setAST(Integer.parseInt(rowScanner.next()));
                player.setSTL(Integer.parseInt(rowScanner.next()));
                player.setFTPercent(Double.parseDouble(rowScanner.next()));
                player.setBLK(Integer.parseInt(rowScanner.next()));
                player.setTO(Integer.parseInt(rowScanner.next()));
                player.setPF(Integer.parseInt(rowScanner.next()));
                player.setFAN(Double.parseDouble(rowScanner.next()));

            }
            return player;
        }
    }

    public void initData(ObservableList<String> listOfSelectedTeamNames) throws IOException { //get 4 teams chosen
        ObservableList<Player> players = null;
        PlayerDAO playerDAO= DbConnection.getPlayerDAO();
        playerTableView = new TableView<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");  //format the displayed date
//        System.out.println("Hello here from INIT DATA, # of team selected is "+listOfSelectedTeamNames.size());

        if(listOfSelectedTeamNames.size() > 0) {
            for (int i = 0; i < listOfSelectedTeamNames.size(); i++) {
                players = playerDAO.getPlayerStats(listOfSelectedTeamNames.get(i)); //get all games 4 current team name
                // create an associate the properties with the table columns
                TableColumn<Player, String> playerNameColumn = new TableColumn<>("Player name ");
                playerNameColumn.setMinWidth(148);
                playerNameColumn.setMaxWidth(148);
                playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerName"));
                playerTableView.getColumns().add(playerNameColumn);

                for(int j=0; j< maxNumberOfTeam2Show; j++){ // size of game 4 each player games = number of column to create
                    final int index = j;
                    TableColumn<Player, List<Game>> gameInfoHeaderColumn = new TableColumn<>("Game Info & FAN");

                    TableColumn<Player, String> gameDateColumn = new TableColumn<>("Date");
                    TableColumn<Player, String> gameInfoColumn = new TableColumn<>("Details");
                    TableColumn<Player, Double> playerFANColumn = new TableColumn<>("FAN");

                    playerTableView.getColumns().add(gameInfoHeaderColumn);


                    gameDateColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() ->cellData.getValue().getAllPlayerFanPTS().get(index).getDateOfTheGame()));
                    gameInfoColumn.setCellValueFactory(data -> Bindings.createObjectBinding(() -> data.getValue().getAllPlayerFanPTS().get(index).getGameInfo()));
                    playerFANColumn.setCellValueFactory(cData -> Bindings.createObjectBinding(() -> cData.getValue().getAllPlayerFanPTS().get(index).getPlayerFantasyPoint()));


                    gameInfoHeaderColumn.getColumns().addAll(gameDateColumn,gameInfoColumn,playerFANColumn);
                    playerTableView.getSortOrder().addAll(gameDateColumn,playerFANColumn);

//                    System.out.print("for the "+listOfSelectedTeamNames.get(i)+ ", there are "+players.size()+ " players. ->\n"+players.get(j).getPlayerName()+" and this is his stats \n"+players.get(j).getAllPlayerFanPTS());

                }
            }

            playerTableView.setItems(players);

            scrollPane.setContent(playerTableView);
        }
    }
}
//                    gameDateColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getAllPlayerFanPTS().stream()
//                            .map(Game::getGameInfo)
//                            .collect(Collectors.joining("\n"))));
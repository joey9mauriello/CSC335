// Jake Bode
// Joey Mauriello

package controller_view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.AccountController;
import model.Jukebox;
import model.JukeboxAccount;
import model.Song;

public class SongSelectionPane extends GridPane {
	
	// Controller for account info/login/logout
	private AccountController accountController;

	private TableView<Song> songTable;
	
	// Columns for song table
	private TableColumn<Song, String> titleColumn;
	private TableColumn<Song, String> artistColumn;
	private TableColumn<Song, String> timeColumn;
	
	private ObservableList<Song> songs = FXCollections.observableArrayList();
	
	private Jukebox jukebox;
	
	
	public SongSelectionPane(AccountController controller, Jukebox jukebox) {
		accountController = controller;
		this.jukebox = jukebox;
		setUpTable();
		setUpGUI();
		registerHandlers();
	}
	
	private void setUpTable() {
		songTable = new TableView<Song>();
		
		songTable.setPrefWidth(500.0);
		
		titleColumn = new TableColumn<Song, String>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
		titleColumn.prefWidthProperty().bind(songTable.widthProperty().multiply(0.5));
		
		artistColumn = new TableColumn<Song, String>("Artist");
		artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
		artistColumn.prefWidthProperty().bind(songTable.widthProperty().multiply(0.35));
		
		timeColumn = new TableColumn<Song, String>("Time");
		timeColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("time"));
		timeColumn.prefWidthProperty().bind(songTable.widthProperty().multiply(0.145));
		
		songTable.getColumns().add(titleColumn);
		songTable.getColumns().add(artistColumn);
		songTable.getColumns().add(timeColumn);
		songTable.setItems(songs);
		addSongs();
	}

	private void addSongs() {
		songs.add(new Song("Pokemon Capture", "Pikachu", "0:05", "songfiles\\Capture.mp3"));
		songs.add(new Song("Danse Macabre", "Kevin MacLeod", "0:34", "songfiles\\DanseMacabreViolinHook.mp3"));
		songs.add(new Song("Determined Tumbao", "FreePlay Music", "0:20", "songfiles\\DeterminedTumbao.mp3"));
		songs.add(new Song("Loping Sting", "Kevin MacLeod", "0:05", "songfiles\\LopingSting.mp3"));
		songs.add(new Song("Swing Cheese", "FreePlay Music", "0:15", "songfiles\\Capture.mp3"));
		songs.add(new Song("The Curtain Rises", "Kevin MacLeod", "0:28", "songfiles\\TheCurtainRises.mp3"));
		songs.add(new Song("Untameable Fire", "Pierre Langer", "4:42", "songfiles\\UntameableFire.mp3"));
	}


	private void setUpGUI() {
		this.add(songTable, 0, 0);
	}

	private void registerHandlers() {
		songTable.setRowFactory(tv -> {
			TableRow<Song> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !(row.isEmpty())) {
					if (!accountController.loggedIn()) {
						Alert notLoggedIn = new Alert(AlertType.INFORMATION);
						notLoggedIn.setTitle("Invalid Request");
						notLoggedIn.setHeaderText("You must be logged in to add songs to the queue.");
						notLoggedIn.setContentText("Please login or create an account to gain access to queue three songs per day to the playlist.");
						notLoggedIn.showAndWait();
						return;
					}
					Song song = songTable.getSelectionModel().getSelectedItem();
					if (checkPlayable()) {
						jukebox.queueSong(song);
						jukebox.update();
					}
				}
			});
			return row;
		});
	}
	
	private boolean checkPlayable() {
		JukeboxAccount loggedInUser = accountController.getCurrUser();
		if (!accountController.loggedIn()) {
			Alert notLoggedIn = new Alert(AlertType.INFORMATION);
			notLoggedIn.setTitle("Invalid Request");
			notLoggedIn.setHeaderText("You must be logged in to add songs to the queue.");
			notLoggedIn.setContentText("Please login or create an account to gain access to queue three songs per day to the playlist.");
			notLoggedIn.showAndWait();
		}
		else if (!loggedInUser.nextDay() && !loggedInUser.hasPlays()) {
			Alert notEnoughPlays = new Alert(AlertType.INFORMATION);
			notEnoughPlays.setTitle("Invalid Request");
			notEnoughPlays.setHeaderText("You have used up all of your plays for today.");
			notEnoughPlays.setContentText("Come back tomorrow to add songs to the queue!");
			notEnoughPlays.showAndWait();
		} else {
			loggedInUser.reducePlays();
			return true;
		}
		return false;
	}
}

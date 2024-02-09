// Jake Bode
// Joey Mauriello

package controller_view;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import model.AccountController;
import model.Jukebox;
import model.ManageSerializedData;

public class JukeboxGUI extends Application {
	
	private static AccountController accountController;
	private static LoginCreateAccountPane loginPane;
	
	private SongSelectionPane selectionPane;
	
	private static PlayListPane playlistPane;
	
	private BorderPane everything;
	
	private Jukebox jukebox;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		accountController = ManageSerializedData.loadAccountData();
		
		Alert onOpen = new Alert(AlertType.CONFIRMATION);
		onOpen.setTitle("Start Up Option");
    	onOpen.setHeaderText("Read saved data?");
    	onOpen.setContentText("Press OK to continue with the saved jukebox playlist and press"
    			+ " Cancel to begin with a clean slate. NOTE: This is not reversable.");
    	Optional<ButtonType> result = onOpen.showAndWait();
    	if (result.get() == ButtonType.OK) {
    		jukebox = new Jukebox(ManageSerializedData.loadPlaylist());
    	} else {
    		jukebox = new Jukebox();
    	}
    	
		layoutGUI();
		
		Scene scene = new Scene(everything, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(event -> {
			accountController.logout();
			ManageSerializedData.store(accountController);
			ManageSerializedData.store(jukebox.getPlaylist().getQueue());
        });
	}
	
	public static void update() {
		loginPane.update();
		playlistPane.update();
	}

	private void layoutGUI() {
		everything = new BorderPane();
		
		loginPane = new LoginCreateAccountPane(accountController);
		selectionPane = new SongSelectionPane(accountController, jukebox);
		playlistPane = new PlayListPane(jukebox);
		
		selectionPane.setStyle("-fx-border-style: solid; -fx-border-color: black;");
		loginPane.setMaxWidth(450.0);
		playlistPane.setMinWidth(250.0);
		playlistPane.setStyle("-fx-border-style: solid; -fx-border-color: black;");
		
		everything.setCenter(selectionPane);
		everything.setBottom(loginPane);
		everything.setRight(playlistPane);
		
		jukebox.update();
	}

}

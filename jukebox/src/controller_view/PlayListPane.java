// Jake Bode
// Joey Mauriello

package controller_view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Jukebox;

public class PlayListPane extends GridPane{
	
	private Jukebox jukebox;
	
	private ArrayList<String> playlist;
	private ObservableList<String> obsList;
	private ListView<String> listView;

	public PlayListPane(Jukebox jukebox) {
		this.jukebox = jukebox;
		
		playlist = jukebox.getPlaylist().getList();
		obsList = FXCollections.observableArrayList(playlist);
		listView = new ListView<String>(obsList);
		
		listView.setMouseTransparent(true);
		listView.setFocusTraversable(false);

		listView.setMaxHeight(400.0);
		listView.setMaxWidth(250.0);
		listView.setMinHeight(350.0);
		listView.setMinWidth(200.0);
		this.add(listView, 0, 0);
	}
	
	public void update() {
		playlist = jukebox.getPlaylist().getList();
		obsList = FXCollections.observableArrayList(playlist);
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
	}
	
}

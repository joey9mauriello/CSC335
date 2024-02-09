// Joey Mauriello
// Jake Bode

package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import controller_view.JukeboxGUI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayList {

	private ArrayList<Song> queue;
	private boolean currentlyPlaying;
	
	private MediaPlayer mediaPlayer;
	
	public PlayList() {
		queue = new ArrayList<Song>();
		currentlyPlaying = false;
	}
	
	public PlayList(ArrayList<Song> queue) {
		this.queue = queue;
		currentlyPlaying = false;
	}

	public void queueUpNextSong(Song songToAdd) {
		queue.add(songToAdd);
	}
	
	public void dequeue() {
		queue.remove(0);
	}

	public void playNext() {
		if (queue.isEmpty()) {
			currentlyPlaying = false;
		} else {
			playSong(queue.get(0));
			currentlyPlaying = true;
		}
	}
	
	public void playSong(Song s) {
		File file = new File(s.getFileName());
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Waiter());
	}
	
	private class Waiter implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dequeue();
			playNext();
			JukeboxGUI.update();
		}
	}
	
	public boolean isEmpty() {
		return queue.size() == 0;
	}
	
	public ArrayList<String> getList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Song s: queue) {
			list.add(s.getTitle() + ", By: " + s.getArtist());
		}
		return list;
	}
	
	public ArrayList<Song> getQueue() {
		return queue;
	}
 	
	public boolean isCurrentlyPlaying() {
		return currentlyPlaying;
	}

}

// Joey Mauriello
// Jake Bode

package model;

import java.util.ArrayList;

import controller_view.JukeboxGUI;

public class Jukebox {
	
	public PlayList playlist;
	
	public Jukebox() {
		playlist = new PlayList();
	}
	
	public Jukebox(ArrayList<Song> queue) {
		playlist = new PlayList(queue);
	}
	
	public void playNext() {
		this.playlist.playNext();
	}
	
	public void queueSong(Song song) {
		this.playlist.queueUpNextSong(song);
	}
	
	public void update() {
		JukeboxGUI.update();
		if (!playlist.isCurrentlyPlaying()) {
			playlist.playNext();
		}
	}
	
	public PlayList getPlaylist() {
		return playlist;
	}

}

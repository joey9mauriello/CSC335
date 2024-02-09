// Jake Bode
// Joey Mauriello

package model;

import java.io.Serializable;

public class Song implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String artist;
	private String playTime;
	private String fileName;

	public Song(String title, String artist, String playTime, String fileName) {
		this.title = title;
		this.artist = artist;
		this.playTime = playTime;
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getTime() {
		return playTime;
	}

	public String getFileName() {
		return fileName;
	}
	
	@Override
	public String toString() {
		return title;
	}

}

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		MediaFile mediaFile = new MediaFile();

		Song song = new Song("hello", "asdf", 10.0, 6, 2010);
		Song song1 = new Song("It's me", "df", 3.0, 10, 2000);

		//ArrayList<String> songs = mediaFile.readInSongInfo("mySongs.txt");
		ArrayList<Song> songs = new ArrayList<Song>();
		
		songs.add(song);
		songs.add(song1);

		for (final Song s : songs) {
			System.out.print(s + "\n");
		}


		MediaFile.writeSongListToFile(songs,"songList");

//		for (final String s : songs) {
//			System.out.print(s + "\n") ;
//		}
	}

}

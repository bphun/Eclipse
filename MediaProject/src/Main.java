
public class Main {

	public static void main(String[] args) {
		MediaFile mediaFile = new MediaFile();

		Song song = new Song("Dust in The Wind", "Kansas", "Human", 9.41, 2);
		Song song1 = new Song("asfd", "vas", "dse", 9.41, 3);
		
		mediaFile.writeString("Title: " + song.getTitle() + " | " + "Album: " + song.getAlbum() + " | " + "Artist: " + song.getArtist() + " | " + "Duration: " + song.getDuration() + " | " + "Rating: " + song.getRating());
		mediaFile.writeString("Title: " + song1.getTitle() + " | " + "Album: " + song1.getAlbum() + " | " + "Artist: " + song1.getArtist() + " | " + "Duration: " + song1.getDuration() + " | " + "Rating: " + song1.getRating());

		mediaFile.saveAndClose();	
	}
	
}

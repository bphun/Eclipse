
public class FavoritesMediaLib {

	public static void main(String[] args) {
		
		MediaFile mediaFile = new MediaFile();
		
		while (true) {
			String songInfo = mediaFile.readString();
			if (songInfo != null) {
								
				String[] regex = songInfo.split("\\|");
				String favoriteSongString = "";

				System.out.print("Favorite songs" + "\n" + "-----------------" + "\n");
				for (final String n : regex) {
					if (n.contains("Title: ")) {
						favoriteSongString += n;
					} 
					if (n.contains("Rating: ")) {
						favoriteSongString += "(" + n.substring(9) + ")" + "\n";
					}
					System.out.print(favoriteSongString);
					favoriteSongString = "";
				}			
			} else if (songInfo == null){
				break;
			}
		}
	}
	
}

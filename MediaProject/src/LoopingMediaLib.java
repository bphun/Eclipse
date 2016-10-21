public class LoopingMediaLib {

	public static void main(String[] args) {
		
		MediaFile mediaFile = new MediaFile();
		
		while (true) {
			String songInfo = mediaFile.readString();
			if (songInfo != null) {		
				String[] regex = songInfo.split("\\|");
				
				System.out.print("Songs" + "\n" + "-----------------" + "\n");

				String title = "";
				String album = "";
				String artist = "";
				double duration = 0.0;
				int rating = 0;
				
				for (final String n : regex) {
					if (n.contains("Title: ")) {
						title = n.substring(7);
					} else if (n.contains("Album: ")) {
						album = n.substring(8);
					} else if (n.contains("Artist: ")) {
						artist = n.substring(9);
					} else if (n.contains("Duration: ")) {
						duration = Double.parseDouble(n.substring(10));
					} else if (n.contains("Rating: ")) {
						rating = Integer.parseInt(n.substring(9));
					}
//					System.out.println(n);
				}	
				System.out.println(title);
				System.out.println(album);
				System.out.println(artist);
				System.out.println(duration);
				System.out.println(rating);
			} else if (songInfo == null){
				break;
			}
		}
		
	}
	
}

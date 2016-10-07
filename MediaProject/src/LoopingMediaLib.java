public class LoopingMediaLib {

	public static void main(String[] args) {
		
		MediaFile mediaFile = new MediaFile();
		
		while (true) {
			String songInfo = mediaFile.readString();
			if (songInfo != null) {		
				String[] regex = songInfo.split("\\|");
				
				System.out.print("Songs" + "\n" + "-----------------" + "\n");

				for (final String n : regex) {
					System.out.println(n);
				}			
			} else if (songInfo == null){
				break;
			}
		}
		
	}
	
}

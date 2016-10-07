Write the following methods:

Song.parseSong(String s)  
This method creates a Song out of the input String s

MediaFile.writeSongListToFile(List<Song> list, String fileName)
This method writes to a file with name of fileName all of the Songs in 
the specified list.  It writes the Songs using each toString() method

public void printSongs(List<Song> songs)
This method simply prints each song from the list to the console in a nice
looking fashion.  DO NOT simply print the list

public void mixUpSongs(List<Song> songs)
This method randomizes the list of songs.  I want you to come up with a 
way to do this.  Do NOT use a pre-built method.

public boolean saveSongsFromListToFile(List<Song> songs)
This method asks the MediaFile class to save the list of songs to an appropriate
file name
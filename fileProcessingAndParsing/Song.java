
/**
 * Write a description of class Song here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Song
{
	// instance variables - replace the example below with your own
	private String title;  // String will have no leading white space characters - thus trim() will have been called
	private double duration;// number of minutes as a double.  4.25 equates to 4 min and 15 sec
	private String creator;// name of the producer
	private int rating;// rating from 1 - 10
	private int yearOfProduction;// obvious...


	public Song(String title, String cre, double dur, int rate, int year)
	{
		this.title = title;
		this.duration = dur;
		this.creator = cre;
		this.rating = rate;
		this.yearOfProduction = year;
	}

	public Song(String title, double dur)
	{
		// better line here is below
		// this(title, null, dur, -1, -1);// which calls the constructor above

		// initialise instance variables
		this.title = title;
		this.duration = dur;


	}

	public Song(String title, int rat)
	{
		this(title, null, -1, rat, -1);// pass in obvious impossible values for other data
	}

	public double  getDuration(  )
	{
		// put your code here
		return duration;
	}

	public String getTitle( )
	{
		return title;
	}
	public void setTitle(String a){
		title = a;
	}

	@Override
	public String toString(){
		String s = "Song - ";
		s += "Title: " + this.title + " by " + creator + ", time: " + getDuration() + ", year: " + yearOfProduction + ", rating: " + rating;
		return s;

	}
	/*
	 * The method below is static, so an invocation of this method will look like the following:
	 * Song s = Song.parseSong(songString);  
	 * Assume that the songString will be of the form produced by the toString method
	 * @param s String that represents the Song to be created by parsing.  This will be of the form 
	 *        that is returned by the toString method
	 *      
	 * @return A new Song created from the specified String s
	 * 
	 */
	public static Song parseSong(String s) {
		if ((s == null) || (s == "")) { return null; }		

		String title = s.substring(s.indexOf("Title: ") + 7, s.indexOf(" by "));
		String creator = s.substring(s.indexOf("by "), s.indexOf(", year: ") - 12);
		double duration = Double.parseDouble(s.substring(s.indexOf("time: ") + 6, s.indexOf(", year: ")));
		int year = Integer.parseInt(s.substring(s.indexOf(", year: ") + 8, s.indexOf(", rating: ")));
		int rating = Integer.parseInt(s.substring(s.indexOf(", rating: ") + 10));
		
		return new Song(title, creator, duration, rating, year);
	}


}

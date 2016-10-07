
/**
 * Write a description of class Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book
{
    // instance variables - replace the example below with your own
    private String title;  // String will have no leading white space characters - thus trim() will have been called
    private int  pages;// 
    private String author;// name of the author
    private int rating;// rating from 1 - 10
    private int yearOfProduction;// obvious...

    
    public Book(String title, String au, int pages, int rate, int year)
    {
        this.title = title;
        this.pages = pages;
        this.author = au;
        this.rating = rate;
        this.yearOfProduction = year;
    }
    
    public Book(String title, int pgs)
    {
        // better line here is below
         this(title, null, pgs, -1, -1);// which calls the constructor above
        
    }
    // WHY DID I HAVE TO COMMENT OUT THE CONSTRUCTOR BELOW????????????
    /*
    public Book(String title, int rat)
    {
        this(title, null, -1, rat, -1);// pass in obvious impossible values for other data
    }
     */
    public int  getPages(  )
    {
        // put your code here
        return pages;
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
        String s = "Book - ";
        s+="Title: "+this.title+" by "+author+" pages: "+pages+" year: "+yearOfProduction+ " rating: "+rating;
        return s;
        
    }
    /*
     * The method below is static, so an invocation of this method will look like the following:
     * Book s = Book.parseBook(BookString);  
     * Assume that the BookString will be of the form produced by the toString method
     */
    public static Book parseBook(String s){
        Book Book = null;
        // parse the String and make a new Book.  Then, return the new Book.
        
        
        return Book;
    }

    
}

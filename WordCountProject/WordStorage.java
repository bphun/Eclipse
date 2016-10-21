
/**
 * This stores a bunch of WordCounts in an array.  It has the ability to add, remove, get and sort. The sort 
 * function takes in a parameter that descirbes 
 * 
 */
public class WordStorage
{
    // instance variables - replace the example below with your own
    private WordCount[] myWordCountArr = new WordCount[1000];
    private int size =0;
    /**
     * Constructor for objects of class WordStorage
     */
    public WordStorage()
    {

    }

    @Override 
    public String toString(){
       String s = "WordStorage: ";
       for(int i = 0; i<size()-1; i++){
           s+= get(i)+", ";
        }
        if(size()>0)
            s+=get(size()-1);
        return s;
        
    }

    public WordCount get(int index){
        return myWordCountArr[index];// unsafe, change when i have time
    }

    public void add( WordCount wc ){
        // find if already there
        int x = indexOf(wc);
        if( x != -1){// already there
            get(x).addCount();
        }
        else{
            checkCapacity();
            insert(wc);
        }

    }

    public void add(String s){
        add(new WordCount(s));
    }

    private void insert(WordCount wc){
        int x = size();
        while( x >0 &&  wc.compareTo( get(x-1))< 0){
            myWordCountArr[x]=myWordCountArr[x-1];
            x--;
        }
        myWordCountArr[x]=wc;
        size++;
    }

    public int indexOf(String s){
        return indexOf(new WordCount(s));
    }

    public int indexOf(WordCount wc){
        for(int i = 0; i<size(); i++){
            if( get(i).compareTo(wc)==0){
                return i;
            }
            else if(get(i).compareTo(wc) > 0){
                return -1;
            }
        }

        return -1;
    }

    public int size(){
        return size;
    }

    private void checkCapacity(){// increase the size of the array, if necessary
        WordCount[] temp = new WordCount[size()*2+1];

        for(int i = 0; i<size(); i++)
            temp[i]=myWordCountArr[i];
        myWordCountArr=temp;
    }   
    
}

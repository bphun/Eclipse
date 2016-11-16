

public class Main {

	public static void main(String[] args) {
		WordStorage_HashMap ws = new WordStorage_HashMap();
		
		ws.add("Hello", 20);
		System.out.println(ws.get("Hello"));
	}
	
}

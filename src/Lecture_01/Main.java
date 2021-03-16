package Lecture_01;

public class Main {
	public static void main(String[] args) {
		Eieruhr firstTimer = new Eieruhr("Nudeln", 20);
		Eieruhr secondTimer = new Eieruhr("Gemüse", 10);
		
		firstTimer.start();
		secondTimer.start();
		
		Complex function = new Complex();
		int[] results = Dispatcher.execute(function, 100);
		
		for(int i = 0; i < results.length; i++) {
			System.out.println("index: " + i + " with value: " + results[i]);
		}
	}
}

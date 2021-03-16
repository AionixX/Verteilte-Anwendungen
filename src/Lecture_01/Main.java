package Lecture_01;

public class Main {
	public static void main(String[] args) {
		Eieruhr firstTimer = new Eieruhr("Nudeln", 20);
		Eieruhr secondTimer = new Eieruhr("Gemüse", 10);
		
		firstTimer.start();
		secondTimer.start();
	}
}

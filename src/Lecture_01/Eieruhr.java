package Lecture_01;

public class Eieruhr extends Thread {
	
	private String name;
	private int timeLeft;

	public Eieruhr(String name, int time) {
		this.name = name;
		this.timeLeft = time;
	}
	
	public void run() {
		System.out.println("Starte timer für " + timeLeft + "Sekunden mit dem Namen " + name);
		
		while(timeLeft > 0) {
			sleep(1);
			timeLeft -= 1;
			printTime();
		}
		
		System.out.println("Der timer mit dem Namen " + name + " ist abgelaufen.");
	}
	
	private void printTime() {
		System.out.println(name + ": " + timeLeft + " Sekunden übrig.");
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(1000 * time);
		}
		catch (InterruptedException error) {
		}
	}

}

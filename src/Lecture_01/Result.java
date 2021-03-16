package Lecture_01;

public class Result extends Thread implements F {
	private int[] results;
	private int counter;
	
	public void run() {
		
	}
	
	public Result(int index) {
		results = new int[index - 1];
		counter = index - 1;
	}
	
	public void AddResult(int index, int result) {
		results[index] = result;
		
		counter++;
		if(counter == results.length) {			
			notify();
		}
	}
	
	public synchronized int[] GetResult() {
		try {
			wait();
		} catch(InterruptedException e) {
			
		}
		return results;
	}
	
	public int f(int x) {
		return x^2;
	}
}

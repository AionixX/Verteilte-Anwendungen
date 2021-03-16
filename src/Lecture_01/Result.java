package Lecture_01;

public class Result {
	private int[] results;
	private int counter;
	
	public Result(int lenght) {
		results = new int[lenght];
		counter = 0;
	}
	
	public synchronized void AddResult(int index, int result) {
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
}

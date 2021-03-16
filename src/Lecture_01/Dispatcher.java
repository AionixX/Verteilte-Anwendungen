package Lecture_01;

public class Dispatcher extends Thread{
	
	private F function;
	private Result result;
	private int x;

	public Dispatcher(F function, Result result, int x) {
		this.function = function;
		this.result = result;
		this.x = x;
	}
	
	public static int[] execute(F f, int n) {
		
		Result result = new Result(n);
		
		for(int i = 0; i < n; i++) {
			Dispatcher dispatcher = new Dispatcher(f, result, i);
			dispatcher.start();
		}
		
		return result.GetResult();
	}
	
	
	public void run() {
		result.AddResult(x, function.f(x));
	}
}

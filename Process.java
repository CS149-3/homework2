
public class Process {
	private String name;
	private int priority;
	private float arrivalNum;
	private float runTime;
	private float timeLeft;
	private float startTime;
	private float endTime;
	
	public Process(String name, int priority, float arrival_num, float run_time){
		this.name = name;
		if(priority < 0 || priority > 5){
			throw new RuntimeException("Invalid priority value");
		}
		
		this.priority = priority;
		
		if(arrival_num <= 0 || arrival_num > 100){
			throw new RuntimeException("Invalid arrival time");
		}
		
		this.arrivalNum = arrival_num;
		
		if(run_time <= 0.1 || run_time > 10){
			throw new RuntimeException("Invalid run time");
		}
		
		this.runTime = run_time;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public void start(float startQuanta){
		startTime = startQuanta;
	}
	
	public void decrement(float ranTime, float currentQuanta){
		this.timeLeft -= ranTime;
		if(timeLeft <= 0){
			stop(currentQuanta);
		}
	}
	
	public void stop(float endQuanta){
		endTime = endQuanta;
	}
	
	//Need to address response field and determine what to pass. 
	public void outputResults(Result output){
		output.outputResults((endTime - startTime), (endTime - startTime - runTime), 0);
	}
	
	public String toString(){
		String returnable = "Process " + name + "/n";
		returnable += "/tPriority: " + priority + "/n";
		returnable += "/tArrival Number: " + arrivalNum + "/n";
		returnable += "/tExpected Run Time: " + runTime + "/n";
		//Optional stats to display
		returnable += "/tStart Time: " + startTime + "/n";
		returnable += "/tEnd Time: " + endTime + "/n";
		return returnable;
	}
	
	
}

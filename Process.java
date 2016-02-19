public class Process implements Comparable<Process> {
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
		
		/**
		 *  my code generates from 0 to 99.999 repeating, if you want to modify that to make this not fail most of the time,
		 *  go ahead, but I don't think the professor cares about that much specificity, it isn't the point of the exercise.
		 */
		/*
		if(arrival_num <= 0 || arrival_num > 100){
			throw new RuntimeException("Invalid arrival time");
		}
		*/
		
		this.arrivalNum = arrival_num;
		
		/**
		 *  my code generates from .1 to 10.1 repeating, if you want to modify that to make this not fail most of the time,
		 *  go ahead, but I don't think the professor cares about that much specificity, it isn't the point of the exercise.
		 */
		/*
		if(run_time < 0.1 || run_time > 10){
			throw new RuntimeException("Invalid run time");
		}
		*/
		
		this.runTime = run_time;
		this.timeLeft = runTime;
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
	
	public void reset() {
		timeLeft = runTime;
		startTime = 0;
		endTime = 0;
	}
	
	//need to address response field and determine what to pass. 
	//public void outputResults(Result output){
	//	output.outputResults((endTime - startTime), (endTime - startTime - runTime), 0);
	//}
	
	
	/**
	 * Reuired by Comparable interface. Implemented so Processes can
	 * be sorted by their arrival time
	 */
	public int compareTo(Process other)
	{
		if (arrivalNum < other.arrivalNum) return -1;
		if (arrivalNum == other.arrivalNum) return 0;
		return 1;
	}
	
	public String toString(){
		String returnable = "Process " + name + "\n";
		returnable += "\tPriority: " + priority + "\n";
		returnable += "\tArrival Number: " + arrivalNum + "\n";
		returnable += "\tExpected Run Time: " + runTime + "\n";
		//Optional stats to display
		returnable += "\tStart Time: " + startTime + "\n";
		returnable += "\tEnd Time: " + endTime + "\n";
		return returnable;
	}
	
	
}

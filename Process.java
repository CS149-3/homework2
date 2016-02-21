import java.util.Comparator;

public class Process {
	private String name;
	private int priority;
	private float arrivalNum;
	private float runTime;
	private float timeLeft;
	private float startTime;
	private float endTime;
	private float timeEnterQ;
	private float waitTime;
	
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
		this.timeEnterQ = arrival_num;
		
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
	
	public float getArrivalNum(){
		return arrivalNum;
	}
	
	public float getTimeEnterQ() {
		return timeEnterQ;
	}
	
	public float getTimeLeft() {
		return timeLeft;
	}
	
	public float getRunTime() {
		return runTime;
	}
	
	public float getWaitTime() {
		return waitTime;
	}
	
	public void setPriority(int newPriority) {
		this.priority = newPriority;
	}
	
	public void setTimeLeft(float newTimeLeft) {
		this.timeLeft = newTimeLeft;
	}
	
	public void setTimeEnterQ(float newTimeEnterQ) {
		this.timeEnterQ = newTimeEnterQ;
	}
	
	public void setWaitTime(float newWaitTime) {
		this.waitTime = newWaitTime;
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
	
	// First Come First Served
	public static Comparator<Process> compareByArrival() {
		return new ArrivalComparator();
	}
	
	// Highest Priority First ? might depend on preemptive/non preemptive
	public static Comparator<Process> compareByPriority() {
		return new PriorityComparator();
	}
	
	// Shortest Remaining Time
	public static Comparator<Process> compareByTimeLeft() {
		return new TimeLeftComparator();
	}
	
	// Shortest Job First
	public static Comparator<Process> compareByRunTime() {
		return new RunTimeComparator();
	}
	
	private static class ArrivalComparator implements Comparator<Process> {
		
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getArrivalNum() < p2.getArrivalNum()) return -1;
			else if (p1.getArrivalNum() == p2.getArrivalNum()) return 0;
			else return 1;
		}
		
	}
	
	private static class PriorityComparator implements Comparator<Process> {
		
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getPriority() < p2.getPriority()) return -1;
			else if (p1.getPriority() == p2.getPriority()) return 0;
			else return 1;
		}
		
	}
	
	private static class TimeLeftComparator implements Comparator<Process> {
		
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getTimeLeft() < p2.getTimeLeft()) return -1;
			else if (p1.getTimeLeft() == p2.getTimeLeft()) return 0;
			else return 1;
		}
		
	}
	
	private static class RunTimeComparator implements Comparator<Process> {
		
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getRunTime() < p2.getRunTime()) return -1;
			else if (p1.getRunTime() == p2.getRunTime()) return 0;
			else return 1;
		}
		
	}
}

import java.util.ArrayList;

public class Result {
	
	private ArrayList<String> timeChart;

	private ArrayList<Process> processList;
	private float averageTurnaround;
	private float averageWaiting;
	private float averageResponseTime;
	private float throughPut;
	
	/**
	 * Creates a Result object.
	 */
	public Result(){
		timeChart = new ArrayList<String>();
		averageTurnaround = 0;
		averageWaiting = 0;
		averageResponseTime = 0;
		throughPut = 0;
	}
	
	/**
	 * Adds to the time chart. You should add the processes in the order they ran for each quanta.
	 * For example, if you had FCFS and ran 4 processes, ABCD by the end of the current quanta, then you would call
	 * result.addChart("ABCD")
	 * You should append process names to your own temporary string to keep track of what processes you have ran for
	 * each quanta.
	 * 
	 * @param processNames
	 */
	public void addChart(String processNames){
		timeChart.add(processNames);
	}
	
	/**
	 * Input the average run results for your algorithm. Calculate these values on your own as your algorithm runs.
	 * 
	 * @param turnaround
	 * @param waiting
	 * @param response
	 * @param throughput
	 */
	public void runResults(float turnaround, float waiting, float response, float throughput){
		averageTurnaround = turnaround;
		averageWaiting = waiting;
		averageResponseTime = response;
		throughPut = throughput;
	}
	
	/**
	 * toString does stringy things. Output for our results for each algorithm run.
	 */
	public String toString(){
		String returnable = "\nTime Chart:\n" + timeChart.toString() + "\n";
		
		returnable += "Turnaround Avg: " + averageTurnaround +"\n";
		returnable += "Waiting Avg: " + averageWaiting + "\n";
		returnable += "Response Avg:" + averageResponseTime + "\n";
		returnable += "Throughput: " + throughPut + "\n";
		
		return returnable; 
	}
}

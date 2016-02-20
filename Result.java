import java.util.ArrayList;

public class Result {
	
	private ArrayList<String> timeChart;
	private ArrayList<Process> processList;
	private float averageTurnaround;
	private float averageWaiting;
	private float averageResponseTime;
	
	
	public Result(){
		timeChart = new ArrayList<String>();
		processList = new ArrayList<Process>();
		averageTurnaround = 0;
		averageWaiting = 0;
		averageResponseTime = 0;
	}
	
	public void addChart(String processes){
		timeChart.add(processes);
	}
	
	public void addProcess(Process process){
		processList.add(process);
	}
	
	public void runResults(float turnaround, float waiting, float response){
		averageTurnaround = turnaround;
		averageWaiting = waiting;
		averageResponseTime = response;
	}
	
	public String toString(){
		String returnable = "Process List: ";
		
		for(int i = 0; i < processList.size(); i++){
			returnable += processList.get(i);
		}
		returnable += "\nTime Chart:\n" + timeChart.toString() + "\n";
		returnable += "Turnaround Avg: " + averageTurnaround +"\n";
		returnable += "Waiting Avg: " + averageWaiting + "\n";
		returnable += "Response Avg:" + averageResponseTime + "\n";
		
		return returnable; 
	}
}

package homework2;

import java.util.ArrayList;

public class Result {
	
	private ArrayList<String> timeChart;
	private ArrayList<String> processList;
	private float turnaroundTotal;
	private float waitingTotal;
	private float responseTotal;
	
	
	public Result(){
		timeChart = new ArrayList<String>();
		processList = new ArrayList<String>();
		turnaroundTotal = 0;
		waitingTotal = 0;
		responseTotal = 0;
	}
	
	public void addChart(String processes){
		timeChart.add(processes);
	}
	
	public void addProcess(String process){
		processList.add(process);
	}
	
	public void outputResults(float turnaround, float waiting, float response){
		turnaroundTotal += turnaround;
		waitingTotal += waiting;
		responseTotal += response;
	}
	
	public String toString(){
		String returnable = "Process List: /n";
		
		for(int i = 0; i < processList.size(); i++){
			returnable += processList.get(i) + "/n";
		}
		
		returnable += "Turnaround Avg: " + (turnaroundTotal / processList.size()) +"\n";
		returnable += "Waiting Avg: " + (waitingTotal / processList.size()) + "\n";
		returnable += "response Avg:" + (responseTotal / processList.size()) + "\n";
		return returnable; 
	}
}

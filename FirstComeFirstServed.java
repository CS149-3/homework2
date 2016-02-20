import java.util.ArrayList;

public class FirstComeFirstServed implements Executable{
	Result result = new Result();
	float turnAroundTotal=0;
	float averageTurnAround=0;
	float waitingTotal=0;
	float averageWaiting=0;
	float responseTimeTotal=0;
	float averageResponseTime=0;
	float numberProcessesCompleted=0;
	float throughPut=0;
	public Result execute(ArrayList<Process> processes) {
		processes.sort(Process.compareByArrival());
		for(int i=0;i<processes.size();i++){
			result.addProcess(processes.get(i));
			result.addChart(processes.get(i).getName());
			turnAroundTotal=turnAroundTotal+turnAroundTotal+processes.get(i).getRunTime();
			averageTurnAround=turnAroundTotal/(i+1);
			if(i!=0 && i!=processes.size()-1){
				waitingTotal=waitingTotal+waitingTotal+processes.get(i).getRunTime();
				averageWaiting=waitingTotal/(i+1);
			}
			responseTimeTotal=responseTimeTotal+processes.get(i).getRunTime();
			averageResponseTime=responseTimeTotal/(i+1);
			if(responseTimeTotal<=100){
				numberProcessesCompleted++;
			}
		}
		throughPut=numberProcessesCompleted/100;
		result.runResults(averageTurnAround, averageWaiting, averageResponseTime, throughPut);	
		return result;
	}
}

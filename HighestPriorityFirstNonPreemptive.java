import java.util.ArrayDeque;
import java.util.ArrayList;


public class HighestPriorityFirstNonPreemptive implements Executable {
	
	Result result = new Result();
	float quanta = 0;
	float turnaroundTime = 0;
	float waitingTime = 0;
	float responseTime = 0;
	ArrayList<ArrayDeque<Process>> priority = new ArrayList<ArrayDeque<Process>>();
	
	public Result execute(ArrayList<Process> processes) {
		priority.add(new ArrayDeque<Process>());
		priority.add(new ArrayDeque<Process>());
		priority.add(new ArrayDeque<Process>());
		priority.add(new ArrayDeque<Process>());
	
		processes.sort(Process.compareByArrival());
		boolean running = true;
		int nextProcess = 0;
		
		while(running){
			if(processes.get(nextProcess).getArrivalNum() <= quanta){
				while(processes.get(nextProcess).getArrivalNum() <= quanta){
					addProcess(processes.get(nextProcess));
					if(nextProcess < (processes.size() - 1))
						nextProcess++;
				}
			}else{
				quanta += 1;
			}
			
			
			Process runningP= nextProcess();
			evaluate(runningP, "");
			
			if(quanta > 100 || processes.size() == nextProcess){
				running = false;
			}
		}
		result.runResults(turnaroundTime / processes.size(), waitingTime, responseTime/processes.size());
		return result;
	}
	
	private void addProcess(Process process){
		switch (process.getPriority()){
		case 1: priority.get(0).push(process);
			break;
		case 2: priority.get(1).push(process);
			break;
		case 3: priority.get(2).push(process);
			break;
		case 4: priority.get(3).push(process);
			break;
		default: throw new RuntimeException("Invalid Priority");
		}
	}
	
	private Process nextProcess(){
		if(!priority.get(0).isEmpty()){
			return priority.get(0).pop();
		}else if(!priority.get(1).isEmpty()){
			return priority.get(1).pop();
		}else if(!priority.get(2).isEmpty()){
			return priority.get(2).pop();
		}else if (!priority.get(3).isEmpty()){
			return priority.get(3).pop();
		}else{
			return null;
		}
	}
	
	private void evaluate(Process process, String processString){
		if(process != null){
			if(((quanta % 1) + process.getRunTime()) >= 1.0f){
				process.start(quanta);
				quanta =(float)((int) quanta) + 1;
				process.setTimeLeft(1.0f - process.getRunTime());
				addProcess(process);
				result.addChart(processString.concat(process.getName()));
			}else{
				process.start(quanta);
				responseTime += (quanta - process.getArrivalNum());
				quanta += process.getRunTime();
				process.stop(quanta);
				turnaroundTime += (process.getEndTime() - process.getArrivalNum());
				Process newProcess = nextProcess();
				if(newProcess != null){
					evaluate(newProcess, processString.concat(process.getName()));
				}else{
					result.addChart(processString.concat(process.getName()));
				}
			}
		}
	}
}


import java.util.ArrayList;

public class HighestPriorityFirstPreemptive implements Executable {
	
	ArrayList<Process> q1 = new ArrayList<Process>();
	ArrayList<Process> q2 = new ArrayList<Process>();
	ArrayList<Process> q3 = new ArrayList<Process>();
	ArrayList<Process> q4 = new ArrayList<Process>();
	private static int quanta = 100; // for entire run
	private static float quantum = 1.0f; // time allowed per individual process
	private static float timeElapsed = 0.0f;
	private static int numPriorities = 4;
	
	
	@Override
	public Result execute(ArrayList<Process> processes) {
		
		Result overallResult = new Result();
		ArrayList <Result> results = new ArrayList<Result>();
		
		// all processes sorted by priority at start of the run
		processes.sort(Process.compareByPriority());
		
		for (Process p : processes) {
			classifyByPriority(p);
		}
		
		for (int i = 0; i < 4; i++) {
			results.add(new Result());
		}
		
		if (!q1.isEmpty() && timeElapsed < quanta)
			results.add(processQueue(q1));
		if (!q1.isEmpty() && timeElapsed < quanta)
			results.add(processQueue(q2));
		if (!q1.isEmpty() && timeElapsed < quanta)
			results.add(processQueue(q3));
		if (!q1.isEmpty() && timeElapsed < quanta)
			results.add(processQueue(q4));
		
		String overallTimeChart = "";
		for (int i = 0; i < numPriorities; i++) {
			overallTimeChart = "\npriorityQ" + i + "[" + 
				results.get(i).getTimeChart() + "]";
		}
		float overallSumWait = 0.0f;
		float overallSumTurnAround = 0.0f;
		float overallSumResponse = 0.0f;
		for (int i = 0; i < numPriorities; i++) {
			overallSumWait += results.get(i).getAvgWaiting();
			overallSumTurnAround += results.get(i).getAvgTurnAround();
			overallSumResponse += results.get(i).getAvgResponseTime();
		}
		
		overallResult.addChart(overallTimeChart);
		overallResult.runResults((overallSumTurnAround / numPriorities), 
			(overallSumWait / numPriorities), 
			(overallSumResponse / numPriorities));
		
		return overallResult;
	}
	
	public Result processQueue(ArrayList<Process> currentQ) {
		float nextStartTime = 0.0f;
		int count = 0;
		String completed = "";
		float sumWait = 0.0f;
		float sumTurnAround = 0.0f;
		float sumResponse = 0.0f;
		Process currentP = null;
		Process nextP = null;
		Result result = new Result();
		int i = 0;
		
		for (i = 0; i < quanta && !(currentQ.isEmpty()); i += quantum) {
			nextP = currentQ.remove(0);
			if (currentP != null) {
				float remaining = (currentP.getTimeLeft() - quantum);
				if (remaining > 0) {
					currentP.setTimeLeft(remaining);
					currentP.setTimeEnterQ(i);
					currentQ.add(currentP);
				} else {
					i -= (i - currentP.getTimeLeft());
					currentP.stop(i);
					completed.concat(currentP.getName());
					sumWait += currentP.getWaitTime();
					sumTurnAround += (i - currentP.getStartTime());
					count++;
				}
			}
			
			nextStartTime = nextP.getTimeEnterQ();
			if (nextStartTime <= i) {
				if (currentP == null)
					i -= (i - nextStartTime);
				float wait = (i - nextStartTime);
				currentP = nextP;
				if (currentP.getTimeLeft() == currentP.getRunTime()){
					currentP.start(i);
					sumResponse += wait;
					currentP.setWaitTime(wait);
				} else {
					currentP.setWaitTime((currentP.getWaitTime()) + wait);
				}
			}
		}
		
		result.addChart(completed);
		result.runResults((sumTurnAround / count), 
			(sumWait / count), (sumResponse / count));
		System.out.println(result);
		count = 0;
		timeElapsed += i;
		
		return result;
	}

	private void classifyByPriority(Process currentProcess) {
		int priority = currentProcess.getPriority();
		if (priority == 0)
			q1.add(currentProcess);
		else if (priority == 1)
			q2.add(currentProcess);
		else if (priority == 2)
			q3.add(currentProcess);
		else if (priority == 3)
			q4.add(currentProcess);
	}
}

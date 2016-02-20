import java.util.ArrayList;

public class FirstComeFirstServed implements Executable{
	
	public Result execute(ArrayList<Process> processes) {
		processes.sort(Process.compareByArrival());
		System.out.println(processes);
		return null;
	}
}

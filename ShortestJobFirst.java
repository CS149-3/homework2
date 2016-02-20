import java.util.ArrayList;
import java.util.Collections;

/*
* Non-Preemptive batch algorithm that assumes the run times are known
* in advance.
*/
public class ShortestJobFirst implements Executable
{

	@Override
	public Result execute(ArrayList<Process> processes) 
	{
		//Sort the list of Processes by estimated run time
		Collections.sort(processes, new Process());
		
		for(Process s : processes)
		{
			System.out.println(s.toString());
		}
		return null;
	}

}

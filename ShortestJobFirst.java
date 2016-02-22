package homework2;
import java.util.ArrayList;

//Non-Preemptive batch algorithm.Assumes run times are known in advance.

public class ShortestJobFirst implements Executable
{
	@Override
	public Result execute(ArrayList<Process> processes) 
	{
		//Sort the list of Processes by estimated run time
		processes.sort(Process.compareByRunTime());
		
		//Setup and initialization
		float i = 0; //time slice unit (quanta)
		float runTime = 0;
		int numProcesses = processes.size();
		int currentProcess = 0;
		Process p = processes.get(0); //get the first process
		p.start(i);
		
		do//Start the SJF algorithm
		{
			if(runTime >= p.getRunTime())
			{
				p.stop(i);
				currentProcess++;
				
				if(currentProcess < numProcesses)//there are more processes to start
				{
					p = processes.get(currentProcess);
					p.start(i); //set the start time of next process to the current quanta
					runTime = 0; //reset run time counter
				}
			}
			i += .1;
			runTime += .1;
			
		}while(processes.get(numProcesses - 1).getEndTime() <= 0);
		
	
		for(Process s : processes)
		{
			System.out.println(s.toString());
		}
		return null;
	}
}

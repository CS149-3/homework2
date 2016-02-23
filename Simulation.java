import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Simulation {
	
	public static ArrayList<Process> processes = new ArrayList<Process>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			// generate processes (processes should only be generated once per run!)
			processes.clear();
			generateProcesses(20);
			
			// 1. run First-come first served
			Result fcfs = new FirstComeFirstServed().execute(copyProcesses());
			
			// 2. run Shortest job first
			Result sjf = new ShortestJobFirst().execute(copyProcesses());
			
			// 3. run Shortest remaining time
			Result srt = new ShortestRemainingTime().execute(copyProcesses());
			
			// 4. run Round robin
			Result rr = new RoundRobin().execute(copyProcesses());
			
			// 5. run Highest priority first (preemptive)
			Result hpfp = new HighestPriorityFirstPreemptive().execute(copyProcesses());
			
			// 6. run Highest priority first (non-preemptive)
			Result hpfnp = new HighestPriorityFirstNonPreemptive().execute(copyProcesses());
			
			//print results (if we don't just change the above to execute in println's)
			System.out.println("\n Run " + (i + 1) + " \n");
			System.out.println(processes);
			
			System.out.println(fcfs);
			System.out.println(sjf);
			System.out.println(srt);
			System.out.println(rr);
			System.out.println(hpfp);
			System.out.println(hpfnp);
		}
	}

	private static void generateProcesses(int numProcesses) {
		if (numProcesses < 1 || numProcesses > 26) throw new RuntimeException("Process count must be between 1 and 26");
		
		Random random = new Random();
		
		for (int i = 0; i < numProcesses; i++) {
			float runTime = random.nextFloat() * 10;
			if(runTime < 9.9f) runTime += 0.1f;
			processes.add(new Process(Character.toString((char) ('A' + i)), random.nextInt(4) + 1, random.nextFloat() * 100, runTime));
		}
	}
	
	private static ArrayList<Process> copyProcesses() {
		ArrayList<Process> processesCopy = new ArrayList<Process>();
		
		for (Process process : processes) processesCopy.add(new Process(process));
		
		return processesCopy;
	}
}

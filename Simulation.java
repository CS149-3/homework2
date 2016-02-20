package homework2;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
	
	public static ArrayList<Process> processes = new ArrayList<Process>();
	
	public static void main(String[] args) {
		// generate processes (processes should only be generated once!)
		generateProcesses(20);
		
		// 1. run First-come first served
		Result fcfs = new FirstComeFirstServed().execute(processes);
		resetProcesses();
		
		// 2. run Shortest job first
		Result sjf = new ShortestJobFirst().execute(processes);
		resetProcesses();
		
		// 3. run Shortest remaining time
		Result smt = new ShortestRemainingTime().execute(processes);
		resetProcesses();
		
		// 4. run Round robin
		Result rr = new RoundRobin().execute(processes);
		resetProcesses();
		
		// 5. run Highest priority first (preemptive)
		Result hpfp = new HighestPriorityFirstPreemptive().execute(processes);
		resetProcesses();
		
		// 6. run Highest priority first (non-preemptive)
		Result hpfnp = new HighestPriorityFirstNonPreemptive().execute(processes);
		resetProcesses();
		
		//print results (if we don't just change the above to execute in println's)
		
	}

	private static void generateProcesses(int numProcesses) {
		if (numProcesses < 1 || numProcesses > 26) throw new RuntimeException("Process count must be between 1 and 26");
		
		Random random = new Random();
		
		for (int i = 0; i < numProcesses; i++) {
			processes.add(new Process(Character.toString((char) ('A' + i)), random.nextInt(4) + 1, random.nextFloat() * 100, random.nextFloat() * 10 + 0.1f));
		}
	}
	
	private static void resetProcesses() {
		for (Process process : processes) process.reset();
	}
}

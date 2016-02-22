import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShortestRemainingTime implements Executable {
	
	private Predicate<Process> notDone = new Predicate<Process>() {
		@Override
		public boolean test(Process p) {
			return p.getTimeLeft() > 0;
		}
	};

	private Predicate<Process> hasArrivedPredicateMaker(float quanta) {
		return new Predicate<Process>() {
			@Override
			public boolean test(Process t) {
				return t.getArrivalNum() <= quanta;
			}
		};
	}

	@Override
	public Result execute(ArrayList<Process> processes) {
		// Sort the list of Processes by estimated run time
		processes.sort(Process.compareByTimeLeft());

		/*
		 * Run each algorithm for 100 quanta (time slices) Each algorithm should
		 * run until the completion of the last process, even if it goes beyond
		 * 100 quanta. No process should get the CPU for the first time after
		 * time quanta 99.
		 */

		// statistics
		Result result = new Result();

		// initialization
		float i = 0; 
		Process p = null; 

		do {
			Predicate<Process> hasArrived = hasArrivedPredicateMaker(i);
			ArrayList<Process> temp;
			if (p != null) {
				p.decrement(0.1f, i);
				result.addChart(p.getName());
			}
			
			if ((temp = (ArrayList<Process>) processes.stream()
					.filter(hasArrived).filter(notDone)
					.collect(Collectors.toList())).size() > 0) { //If the ArrayList (containing all arrived and not done processes sorted by remaining time, now stored in temp) has size greater than zero
				p = temp.get(0); 	/*assign the first element in temp to p
				 					*(This should be the element with the shortest time remaining 
									* after filtering out processes that have already completed and
									* processes that have not yet "arrived" */
				p.start(i);			//Start p [ISSUE: need to stop processes from getting restarted]
				result.addChart(p.getName());
			}
			
			

			i += 0.1;
		} while (processes.stream().filter(notDone).collect(Collectors.toList()).size() != 0);

		return result;
	}

}

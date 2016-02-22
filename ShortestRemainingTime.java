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
			if (p != null) {
				p.decrement(0.1f, i);
			}
			ArrayList<Process> temp = (ArrayList<Process>) processes.stream()
					.filter(hasArrived).filter(notDone)
					.collect(Collectors.toList());
			if (temp.size() > 0) {
				p = temp.get(0);
				p.start(i);
			}

			i += 0.1;
		} while (processes.stream().filter(notDone).collect(Collectors.toList()).size() != 0);

		return result;
	}

}

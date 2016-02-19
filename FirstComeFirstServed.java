import java.util.ArrayList;


public class FirstComeFirstServed implements Executable, Comparable<Process> {

	@Override
	public Result execute(ArrayList<Process> processes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Reuired by Comparable interface. Implemented so Processes can
	 * be sorted by their arrival time
	 */
	public int compareTo(Process other)
	{
		if (arrivalNum < other.arrivalNum) return -1;
		if (arrivalNum == other.arrivalNum) return 0;
		return 1;
	}

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;


public class FirstComeFirstServed implements Executable{
	
	@Override
	public Result execute(ArrayList<Process> processes) {
		return null;
	}
	
	public void print(ArrayList<Process> processes){
		System.out.println("size: "+ processes.size());
		Collections.sort(processes);
		//sort(processes);
		for(int i=0;i<processes.size();i++){
			System.out.println(processes.get(i));
		}
	}	
}

import java.util.ArrayList;
import java.util.Comparator;


public class FirstComeFirstServed implements Executable, Comparator<Float>{
	
	@Override
	public Result execute(ArrayList<Process> processes) {
		sort(processes);
		return null;
	}
	
	public void print(ArrayList<Process> processes){
		sort(processes);
		for(int i=0;i<processes.size();i++){
			System.out.println(processes.get(i));
		}
	}
	
	public void sort(ArrayList<Process> processes){
		if(processes.size()<2){
			return;
		}
		int sizeSubArray=1;
		int startL;
		int startR;
		
		while(sizeSubArray<processes.size()){
			startL=0;
			startR=sizeSubArray;
			while(startR+sizeSubArray <= processes.size()){
				merge(processes, startL, startL+sizeSubArray, startR, startR+sizeSubArray);
				startL=startR+sizeSubArray;
				startR=startL+sizeSubArray;
			}
			if(startR<processes.size()){
				merge(processes, startL, startL+sizeSubArray, startR, processes.size());
			}
			sizeSubArray *=2;
		}
	}
	
	public void merge(ArrayList<Process> processes, int startL, int stopL, int startR,int stopR){
		ArrayList<Process> right = new ArrayList<Process>(stopR-startR+1);
		ArrayList<Process> left = new ArrayList<Process>(stopL-startL+1);
		
		for(int i=0, k=startR; i<right.size()-1; ++i, ++k){
			right.set(i, processes.get(k));
		}
		for(int i=0, k=startL; i<left.size()-1; ++i, ++k){
			left.set(i, processes.get(k));
		}
		
		for(int k=startL, m=0, n=0; k<stopR; ++k){
			if(compare(left.get(m).getArrivalNum(), right.get(n).getArrivalNum())<=0){
				processes.set(k, left.get(m));
				m++;
			}
			/*if(left.get(m).getArrivalNum()<=right.get(n).getArrivalNum()){
				processes.set(k, left.get(m));
				m++;
				Float.compare(left.get(m).getArrivalNum(), right.get(n).getArrivalNum())<=0
			}*/
			else{
				processes.set(k, right.get(n));
				n++;
			}
		}
	}
	
	public int compare(Float f1, Float f2){
		if(f1<f2){
			return -1;
		}
		if(f1>f2){
			return 1;
		}
		else{
			return 0;
		}
	}
	
}

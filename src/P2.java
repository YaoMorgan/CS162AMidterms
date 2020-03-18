import java.util.*;

public class P2 {
	private int timeElapsed, cpuBurst, smallestP, counter, currentProcess;
	private double throughput, cpuUtil, averageWaitingTime, averageTurnAround, averageResponseTime;
	public void run(int[][] process, int lines) {
		ArrayList<Integer> q = new ArrayList();
		int[] waitingTime = new int[lines];
		int[] turnAround = new int[lines];
		int[] responseTime = new int[lines];
		int[] remainingBurst=new int[lines];
		int[] startTime =new int[lines];
		boolean[] responded = new boolean[lines];
		
		//setup
		timeElapsed=0;
		for(int i=0; i<lines; i++) {
			remainingBurst[i]=process[i][1];
			responded[i]=false;
		}
		
		//actual algorithm
		while(counter<lines) {
			currentProcess = smallestP;
			if(q.isEmpty()==true) {
				if(timeElapsed!=0) {
					timeElapsed++;
					currentProcess = smallestP;
				}
			}
			else {
//				if(responded[currentProcess]==false) {
//					responseTime[currentProcess]=timeElapsed-process[currentProcess][0];
//					startTime[currentProcess]=timeElapsed;
//					responded[currentProcess]=true;
//				}
				if(remainingBurst[currentProcess]>0) {
					remainingBurst[currentProcess]--;
					//System.out.println(remainingBurst[currentProcess] + " of " + process[currentProcess][3]);
					timeElapsed++;
					cpuBurst++;
				}
				//process ends
				else if(remainingBurst[currentProcess]==0) {
					counter++;
					System.out.print(startTime[currentProcess]+ " ");
					System.out.print(process[currentProcess][3] + " ");
					System.out.println(cpuBurst-startTime[currentProcess]+ "X");
					q.remove(smallestP);
					//reset P value
					smallestP = Integer.MAX_VALUE;
				}
			}
			//adds into queue if process isnt already there
			for(int i=0; i<lines; i++) {
				if(process[i][0]==timeElapsed) {
					if(q.contains(i)==false  && remainingBurst[i]>0) {
						q.add(i);
						System.out.println("Added: " + process[i][3]+ " at " + timeElapsed);
					}
				}
			}
			//looks for the process in the queue with the lowest nice level
			for(int i=0; i< q.size(); i++) {
				if (process[i][2] < smallestP) {
					smallestP = i;
					//System.out.println("SP: " + process[i][2]);
				}
			}
			//System.out.println("SP: " + smallestP);
			//System.out.println(timeElapsed);
		}
	}
}

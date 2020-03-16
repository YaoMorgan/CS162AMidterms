import java.util.*;

public class RR {
	private int timeElapsed, cpuBurst;
	private int[]  waitingTime, turnAround, responseTime;
	private double throughput, cpuUtil, averageWaitingTime, averageTurnAround, averageResponseTime;
	public void run(int[][] process, int lines, int rrTime) {
		ArrayList<Integer> q = new ArrayList<>();
		int[] waitingTime = new int[lines];
		int[] turnAround = new int[lines];
		int[] responseTime = new int[lines];
		int[] remainingBurst=new int[lines];
		boolean[] responded = new boolean[lines];
		timeElapsed=0;
		
		//setup
		for(int i=0; i<lines; i++) {
			remainingBurst[i]=process[i][1];
			responded[i]=false;
		}
		
		while(true) {
			boolean done=true;
			for(int j=0; j<lines; j++) {
				if(remainingBurst[j]==process[j][1] && process[j][0]<=timeElapsed) {
					q.add(0,j);
				}
			}
			if(q.isEmpty()==false) {
				int currentProcess=q.get(0);
				if(remainingBurst[currentProcess]>0 && process[currentProcess][0]<=timeElapsed) {
					done=false;
					if(remainingBurst[currentProcess]>rrTime) {
						if(responded[currentProcess]==false) {
							responseTime[q.get(0)]=timeElapsed-process[currentProcess][0];
							responded[currentProcess]=true;
						}
						System.out.print(timeElapsed + " ");
						
						//add the given time and reduce remaining
						timeElapsed+=rrTime;
						remainingBurst[currentProcess]-=rrTime;
						
						//System.out.printf("Process%d switched at %dns\n",process[i][3],timeElapsed);
						
						System.out.print(process[currentProcess][3] + " ");
						System.out.println(rrTime);
						q.add(currentProcess);
						q.remove(0);
					}
					else {
						if(responded[currentProcess]==false) {
							responseTime[q.get(0)]=timeElapsed-process[currentProcess][0];
							responded[currentProcess]=true;
						}
						System.out.print(timeElapsed + " ");
						//add the remaining time
						timeElapsed+=remainingBurst[currentProcess];
						
						System.out.print(process[currentProcess][3] + " ");
						System.out.println(remainingBurst[currentProcess]+ "X");
						
						waitingTime[currentProcess]=timeElapsed-process[currentProcess][0]-process[currentProcess][1];
						remainingBurst[currentProcess]=0;
						q.remove(0);
					}
				}
			}
			if(done==true)
				break;
		}
		//computations
		for(int i=0; i<lines; i++) {
			turnAround[i]=process[i][1] + waitingTime[i];
		}
		cpuBurst=timeElapsed;
		cpuUtil=((double)cpuBurst/(double)timeElapsed)*100.0;
		throughput=lines/(double)timeElapsed;
		averageWaitingTime=(double)getAverage(waitingTime);
		averageTurnAround=(double)getAverage(turnAround);
		averageResponseTime=(double)getAverage(responseTime);
		
		//printer
		String toPrint="";
		System.out.printf(toPrint + "Total time elapsed: %dns\nTotal CPU burst time: %dns\nCPU Utilization: %.0f%%\nThroughput: %.2f processes/ns\nWaiting times:\n"
		,timeElapsed, cpuBurst, cpuUtil, throughput);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, waitingTime[i-1]);
		}
		System.out.printf("Average Waiting time: %.2fns\nTurnaround times:\n", averageWaitingTime);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, turnAround[i-1]);
		}
		System.out.printf("Average Turnaround time: %.2fns\nResponse times:\n", averageTurnAround);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, responseTime[i-1]);
		}
		System.out.printf("Average Response time: %.2fns\n\n", averageResponseTime);
	}
	private double getAverage(int[] array) {
		double sum = 0;
		for(int i=0;i<array.length;i++) {
			sum+=array[i]; 
		}
		return sum/array.length;
	}
}

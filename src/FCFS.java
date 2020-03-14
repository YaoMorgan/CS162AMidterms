public class FCFS {
	private int timeElapsed, cpuBurst;
	private int[]  waitingTime, turnAround, responseTime;
	private double throughput, cpuUtil, averageWaitingTime, averageTurnAround, averageResponseTime;
	public void run(int[][] process,int lines) {
		int[] waitingTime = new int[lines];
		int[] turnAround = new int[lines];
		int[] responseTime = new int[lines];
		for(int i=0; i<lines; i++) {
			if(process[i][0]>timeElapsed) {
				int n = Math.abs(timeElapsed-process[i][0]);
				timeElapsed+=n;
			}
			if(process[i][0]<timeElapsed) {
				waitingTime[i] = Math.abs(timeElapsed-process[i][0]);
				responseTime[i] = Math.abs(timeElapsed-process[i][0]);
			}
			for(int j=0; j<process[i][1]; j++) {
				timeElapsed++; cpuBurst++; turnAround[i]++;
			}
		}
//		System.out.println("timeelapsed: " + timeElapsed);
//		System.out.println("cpuburst: " + cpuBurst);
		cpuUtil=((double)cpuBurst/(double)timeElapsed)*100.0;
		throughput=lines/(double)timeElapsed;
		averageWaitingTime=getAverage(waitingTime);
		averageTurnAround=getAverage(turnAround);
		averageResponseTime=getAverage(responseTime);
	//printer
		String toPrint="";
		System.out.println(lines + " FCFS");
		for(int i=0; i<lines;i++) {
			toPrint=toPrint + process[i][0]+ " " + process[i][2] + " " + process[i][1] + "X\n";
		}
		System.out.printf(toPrint + "Total time elapsed: %dns\nTotal CPU burst time: %dns\nCPU Utilization: %.0f%%\nThroughput: %.3f processes/ns\nWaiting times:\n"
				,timeElapsed, cpuBurst, cpuUtil, throughput);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, waitingTime[i-1]);
		}
		System.out.printf("Average Waiting time: %.1fns\nTurnaround times:\n", averageWaitingTime);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, turnAround[i-1]);
		}
		System.out.printf("Average Turnaround time: %.1fns\nResponse times:\n", averageTurnAround);
		for(int i=1; i<=lines; i++) {
			System.out.printf(" Process %d: %dns\n", i, responseTime[i-1]);
		}
		System.out.printf("Average Response time: %.1fns\n\n", averageResponseTime);
		
		
	}
	public double getAverage(int[] array) {
		int sum = 0;
		for(int i=0;i<array.length;i++) {
			sum+=array[i]; 
		}
		return sum/array.length;
	}
}
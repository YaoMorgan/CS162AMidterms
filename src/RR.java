public class RR {
	private int timeElapsed, cpuBurst;
	private int[]  waitingTime, turnAround, responseTime;
	private double throughput, cpuUtil, averageWaitingTime, averageTurnAround, averageResponseTime;
	public void run(int[][] process, int lines, int rrTime) {
		int[] waitingTime = new int[lines];
		int[] turnAround = new int[lines];
		int[] responseTime = new int[lines];
		
		int remainingBurst[]=new int[lines];
		for(int i=0; i<lines; i++) {
			remainingBurst[i]=process[i][1];
		}
		while(true) {
			boolean done=true;
			for(int i=0; i<lines; i++) {
				if(remainingBurst[i]>0) {
					done=false;
					if(remainingBurst[i]>rrTime) {
						//add the given time and reduce remaining
						timeElapsed+=rrTime;
						remainingBurst[i]-=rrTime;
						//System.out.printf("Process%d switched at %dns\n",process[i][3],timeElapsed);
					}
					else {
						//add the remaining time
						timeElapsed+=remainingBurst[i];
						//how long the process waiting before it ended
						waitingTime[i]=timeElapsed-process[i][1];
						remainingBurst[i]=0;
						//System.out.printf("Process%d ended in %dns\n",process[i][3],timeElapsed);
					}
				}
			}
			if(done==true)
				break;
		}
	}
}

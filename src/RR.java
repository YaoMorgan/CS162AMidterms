public class RR {
	static void findWaitingTime(int numP, int[] bt, int[] wt, int rrTime) {
		int curbt[] = new int[numP];
		
		//Copy burst times
		for(int i = 0; i < numP; i++) {
			curbt[i] = bt[i];
		}
		
		int counter = 0;	//timer
		
		while(true) {
			boolean done = true;
			// Traverse all processes one by one repeatedly
			for(int i = 0; i < numP; i++) {
				//If bt of a process is greater than 0 it needs to be processed
				if(curbt[i] > 0) {
					done = false;	//process is on-going
					if(curbt[i] > rrTime) {
						//increase time based on the rrTime
						counter += rrTime;
						//decrease bt of current process by rrTime
						curbt[i] -= rrTime;
					}
					// If bt is less than or equal to rrTime, it's the last cycle of the process
					else { 
						//Increase time based on how long the process is
						counter += curbt[i];
						//wt = current time - time used by the process
						wt[i] = counter - bt[i];
						//Make bt 0 after full execution
						curbt[i] = 0;
					}
				}
			}
			
			if(done == true) {
				break;
			}
		}
	}
	
	static void findTurnAroundTime(int n, int[] bt, int[] wt, int[] tat) {
		//tat = bt + wt
		for(int i = 0; i < n; i++) {
			tat[i] = bt[i] + wt[i];
		}
	}
	
	static void findAveTime(int numP, int[] bt, int rrTime) {
		
	}
	
	static void RR(int rrTime) {
		
	}
}

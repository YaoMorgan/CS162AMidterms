public class P {
	void p(int[][] process, int num) {
		int numP = process.length;
		int pid[] = new int[numP];	//process id
		int at[] = new int[numP];	//arrival time
		int bt[] = new int[numP];	//burst time
		int nice[] = new int[numP];	//niceness
		int ct[] = new int[numP];	//current time
		int tat[] = new int[numP];	//turnaround time
		int wt[] = new int[numP];	//wait time
		int st[] = new int[numP];	//start time
		int rt[] = new int[numP];	//remaining time
		int res[] = new int[numP]; 	//response time
		int lrt[] = new int[numP];	//last remaining time
		int comp[] = new int[numP];	//completion status
		boolean printed[] = new boolean[numP];
		String bts[] = new String[numP];  //add X to end of finished process bt
		
		int start = 0;	//counter
		int total = 0;	//total processes finished
		float avewt = 0;  //average wait time
		float avetat = 0;  //average turnaround time
		boolean check = false; //checks if a process arrives
		int current = 0; //index of process with shortest remaining time
		int minbt = Integer.MAX_VALUE; //process with least bt
		int minni = Integer.MAX_VALUE; //process with least niceness
		int cthold = 0;	
		int ihold = 0;
		boolean status = false;
		
		System.out.println(num + " P");
		
		for(int i = 0; i < numP; i++) {
			at[i] = process[i][0];
			bt[i] = process[i][1];
			rt[i] = process[i][1];
			nice[i] = process[i][2];
			bts[i] = Integer.toString(process[i][1]);
			pid[i] = i+1;
			comp[i] = 0;
			printed[i] = false;
		}
		
		while(true) {
			
			//Check if number of process is equal to processes done
			//End loop if all processes are done.
			if(total == numP) {	
				break;			
			}
			
			for(int i = 0; i < numP; i++) {
				//Check which process arrived,
				//has least burst time.
				if((at[i] <= start) && (rt[i] <= minbt) && (rt[i] > 0) && nice[i] < minni) {
					minbt = rt[i];
					minni = nice[i];
					current = i;
					check = true;
					ct[i] = start;
					lrt[i] = rt[i];
				}
			}
			
			//if no process arrives at current time, increment start time
			if(check == false) { 
				start++;
				continue;
			}
			
			//reduce remaining time of shortest process
			rt[current]--;
			
			//update shortest time
			minbt = rt[current];
			if(minbt == 0) {
				minbt = Integer.MAX_VALUE;
			}
			
			//if process is complete, do this
			if(rt[current] == 0) {
				check = false;
				
				//final time = start + 1;
				tat[current] = start+1 - at[current];
				wt[current] = start+1 - bt[current] - at[current];
				//res[current] = tat[current] - wt[current] + wt[current];
				
				if(wt[current] < 0) {
					wt[current] = 0;
				}
				
				System.out.println(ct[current] + " " + (current+1) + " " + (lrt[current] - rt[current]) + "X");
				printed[current] = true;
			}
			
//			if (cthold != ct[current]) {
//				if(printed[current] == false) {
					System.out.println(ct[ihold] + " " + (ihold+1) + " " + (lrt[ihold] - rt[ihold]));
//					printed[ihold] = true;
//				}
//			}
			
			ihold = current;
			cthold = ct[current];
			
			start++;
		}
		
//		int tbt = 0; //total burst time
//		float awt = 0; //average wait time
//		float atat = 0; //average turnaround time
//		float art = 0; //average response time
//		float cpu = 0; //CPU utilization
//		float throughput = 0; //Throughput
//		
//		for(int i = 0; i < numP; i++) {
//			tbt += bt[i];
//		}
//		
//		System.out.println("Total time elapsed: " + start + "ns");
//		System.out.println("Total CPU burst time: " + tbt + "ns");
//		cpu = (tbt/start)*100;
//		System.out.printf("CPU Utilization: %.2f%% \n", cpu);
//		throughput = (float)(numP+1)/start;
//		System.out.printf("Throughput: %.3f processes/ns\n",throughput);
//		
//		System.out.println("Waiting times:");
//		for(int i = 0; i < numP; i++) {
//			awt += wt[pid[i]-1];
//			System.out.println(" Process " + (i+1) + ": " + wt[pid[i]-1]);
//		}
//		awt = awt/(float)numP;
//		System.out.println("Average waiting time: " + (float) awt + "ns");
//		
//		System.out.println("Turnaround times:");
//		for(int i = 0; i < numP; i++) {
//			atat += tat[pid[i]-1];
//			System.out.println(" Process " + (i+1) + ": " + tat[pid[i]-1]);
//		}
//		atat = atat/numP;
//		System.out.println("Average turnaround time: " + atat + "ns");
//		
//		System.out.println("Response times:");
//		for(int i = 0; i < numP; i++) {
//			art += res[pid[i]-1];
//			System.out.println(" Process " + (i+1) + ": " + res[pid[i]-1]);
//		}
//		art = art/numP;
//		System.out.println("Average response time: " + art + "ns");
	}
}

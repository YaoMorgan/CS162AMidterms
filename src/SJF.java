public class SJF {
	void sjf(int[][] process, int num) {
		int numP = process.length;
		int pid[] = new int[numP];	//process id
		int at[] = new int[numP];	//arrival time
		int bt[] = new int[numP];	//burst time
		int ct[] = new int[numP];	//current time
		int tat[] = new int[numP];	//turnaround time
		int wt[] = new int[numP];	//wait time
		int st[] = new int[numP];	//start time
		int rt[] = new int[numP];	//response time
		int comp[] = new int[numP];	//completion status
		String bts[] = new String[numP];  //add X to end of finished process bt
		
		int start = 0;
		int total = 0;
		float avewt = 0;
		float avetat = 0;
		
		for(int i = 0; i < numP; i++) {
			at[i] = process[i][0];
			bt[i] = process[i][1];
			bts[i] = Integer.toString(process[i][1]);
			pid[i] = i+1;
			comp[i] = 0;
		}
		
		while(true) {
			int current = numP;	//current process (used to check if a process arrived)
			int min = Integer.MAX_VALUE; //process with least bt
			
			//Check if number of process is equal to processes done
			//End loop if all processes are done.
			if(total == numP) {	
				break;			
			}
			
			for(int i = 0; i <numP; i++) {
				//Check which process arrived,
				//has least burst time,
				//not completed.
				if((at[i] <= start) && (comp[i] == 0) && (bt[i] < min)) {
					min = bt[i];
					current = i;
				}
			}
			//if no process arrives at current time, increment start time
			if(current == numP) { 
				start++;
			} else {
				st[current] = start;
				rt[current] = at[current] + start;
				ct[current] = start + bt[current];	//add start time to bt to get current time
				start += bt[current];	//add burst to update start time
				tat[current] = ct[current] - at[current];
				wt[current] = tat[current] - bt[current];
				comp[current] = 1;	//make process status complete(1)
				bts[current] = bts[current] + "X";
				pid[total] = current + 1; //add completed process id to pid
				total++;
			}
		}
		
		int tbt = 0; //total burst time
		int awt = 0; //average wait time
		int atat = 0; //average turnaround time
		int art = 0; //average response time
		float cpu = 0; //CPU utilization
		float throughput = 0; //Throughput
		
		for(int i = 0; i < numP; i++) {
			tbt += bt[i];
		}
		
		System.out.println(num + " SJF");
		for(int i = 0; i < numP; i++) {
			System.out.println(st[pid[i]-1] + " " + pid[i] + " " + bts[pid[i]-1]);
		}
		System.out.println("Total time elapsed: " + start + "ns");
		System.out.println("Total CPU burst time: " + tbt + "ns");
		cpu = (tbt/start)*100;
		System.out.printf("CPU Utilization: %.2f%% \n", cpu);
		throughput = (float)(numP+1)/start;
		System.out.printf("Throughput: %.3f processes/ns\n",throughput);
		
		System.out.println("Waiting times:");
		for(int i = 0; i < numP; i++) {
			awt += wt[pid[i]-1];
			System.out.println(" Process " + (i+1) + ": " + wt[pid[i]-1]+ "ns");
		}
		awt = awt/numP;
		System.out.println("Average waiting time: " + awt + "ns");
		
		System.out.println("Turnaround times:");
		for(int i = 0; i < numP; i++) {
			atat += tat[pid[i]-1];
			System.out.println(" Process " + (i+1) + ": " + tat[pid[i]-1]+"ns");
		}
		atat = atat/numP;
		System.out.println("Average turnaround time: " + atat + "ns");
		
		System.out.println("Response times:");
		for(int i = 0; i < numP; i++) {
			art += rt[pid[i]-1];
			System.out.println(" Process " + (i+1) + ": " + rt[pid[i]-1] +"ns");
		}
		art = art/numP;
		System.out.println("Average response time: " + art + "ns");
	}
}

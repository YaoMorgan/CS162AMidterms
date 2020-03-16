public class SRTF {
	void srtf(int[][] process, int num) {
		int numP = process.length;
		int pid[] = new int[numP];	//process id
		int at[] = new int[numP];	//arrival time
		int bt[] = new int[numP];	//burst time
		int ct[] = new int[numP];	//current time
		int tat[] = new int[numP];	//turnaround time
		int wt[] = new int[numP];	//wait time
		int st[] = new int[numP];	//start time
		int rt[] = new int[numP];	//remaining time
		int res[] = new int[numP]; 	//response time
		int rok[] = new int[numP];
		int lrt[] = new int[numP];	//last remaining time
		int comp[] = new int[numP];	//completion status
		String bts[] = new String[numP];  //add X to end of finished process bt
		
		int start = 0;
		int total = 0;
		float avewt = 0;
		float avetat = 0;
		boolean check = false; //checks if a process arrives
		int current = 0; //index of process with shortest remaining time
		int min = Integer.MAX_VALUE; //process with least bt
		int ihold = 0;
		boolean status = false;
		
		System.out.println(num + " SRTF");
		
		for(int i = 0; i < numP; i++) {
			at[i] = process[i][0];
			bt[i] = process[i][1];
			rt[i] = process[i][1];
			bts[i] = Integer.toString(process[i][1]);
			pid[i] = i+1;
			comp[i] = 0;
			rok[i] = 0;
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
				if((at[i] <= start) && (rt[i] <= min) && (rt[i] > 0)) {
					if(rt[i] == min) {
						if(pid[current] > pid[i]) {
							min = rt[i];
							current = i;
							check = true;
							ct[i] = start;
							lrt[i] = rt[i];
						}
					} else {
						min = rt[i];
						current = i;
						check = true;
						ct[i] = start;
						lrt[i] = rt[i];
					}

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
			min = rt[current];
			if(min == 0) {
				min = Integer.MAX_VALUE;
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
				
				comp[current] = 1;
				total++;
			}

			
			if(ihold != current && (lrt[ihold] - rt[ihold]) > 0) {
				if(comp[ihold] == 1) {
					System.out.println(ct[ihold] + " " + (ihold+1) + " " + (lrt[ihold] - rt[ihold]) + "X");
					
				} else {
					System.out.println(ct[ihold] + " " + (ihold+1) + " " + (lrt[ihold] - rt[ihold]));
				}
			}
			
			if(rok[ihold] == 0) {
				System.out.println("current time" + ct[ihold]);
				System.out.println("arrival time" + at[ihold]);
				res[ihold] = ct[ihold] - at[ihold];
				rok[ihold] = 1;
			}
			
			ihold = current;
			
			start++;
		}
		
		System.out.println(ct[ihold] + " " + (ihold+1) + " " + (lrt[ihold] - rt[ihold]) + "X");
		res[ihold] = ct[ihold] - at[ihold];
		
		int tbt = 0; //total burst time
		float awt = 0; //average wait time
		float atat = 0; //average turnaround time
		float art = 0; //average response time
		float cpu = 0; //CPU utilization
		float throughput = 0; //Throughput
		
		for(int i = 0; i < numP; i++) {
			tbt += bt[i];
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
			System.out.println(" Process " + (i+1) + ": " + wt[pid[i]-1]);
		}
		awt = awt/(float)numP;
		System.out.printf("Average waiting time: %.2fns \n",awt);
		
		System.out.println("Turnaround times:");
		for(int i = 0; i < numP; i++) {
			atat += tat[pid[i]-1];
			System.out.println(" Process " + (i+1) + ": " + tat[pid[i]-1]);
		}
		atat = atat/numP;
		System.out.printf("Average turnaround time: %.2fns \n",atat);
		
		System.out.println("Response times:");
		for(int i = 0; i < numP; i++) {
			art += res[pid[i]-1];
			System.out.println(" Process " + (i+1) + ": " + res[pid[i]-1]);
		}
		art = art/numP;
		System.out.printf("Average response time: %.2fns \n",art);
	}

}

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception { 
	    File file = new File("C:\\Users\\Morgan Yao\\Documents\\GitHub\\CS162A_Project01_Villanueva_Yao\\src\\InputFile.txt"); 
	    Scanner sc = new Scanner(file); 
	    int cases = Integer.parseInt(sc.next());
	    //System.out.println("Cases:" + cases);
	    for(int i = 0; i < cases; i++) {
	    	int lines = Integer.parseInt(sc.next());
	    	//System.out.println("Lines:" + lines);
	    	String type = sc.next();
	    	int rrTime = 0;
	    	if(type.equals("RR")) {
	    		rrTime = Integer.parseInt(sc.next());
	    	}
	    	sc.nextLine();
	    	//System.out.println("Type:" + type);
	    	int[][] process = new int[lines][4];
	    	//System.out.println(process[0][0]);
	    	for(int j = 1; j <= lines; j++) {
	    		for(int k = 0; k < 3; k++) {
	    			process[j-1][k] = Integer.parseInt(sc.next());
	    			//System.out.print(process[j-1][k] + " ");
	    		}
	    	}
	    	for(int v =1;v<=lines;v++) {
    			process[v-1][3]=v;
    		}
	    	
	 		switch(type) {
	 			case "FCFS":
	 				java.util.Arrays.sort(process, new java.util.Comparator<int[]>() {
	 					@Override
	 					public int compare(int[] a, int[] b) {
	 						return Double.compare(a[0], b[0]);
	 					}
	 				});
	 				FCFS func = new FCFS();
	 				func.run(process,lines);
	 				break;
	 	    	case "SJF":
	 	    		SJF t = new SJF();
	 	    		t.sjf(process, i+1);
	 	    		break;
		 	    case "RR":
	 	    	int numP = process.length;
	 	    	int bt[] = new int[numP];
	 	   		for(int l = 0; l < numP; l++) {
	 	   			bt[l] = process[l][1];
	 	   		}
	 	   		RR rr = new RR();
	     			rr.run(process, lines, rrTime);
		 	    	break;
	 	    }
	    }
	} 
}

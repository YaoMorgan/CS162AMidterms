import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
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
	    	for(int v=1;v<=lines;v++) {
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
	 				System.out.println(i+1 + " FCFS");
	 				func.run(process,lines);
	 				break;
	 	    	case "SJF":
	 	    		SJF t = new SJF();
	 	    		t.sjf(process, i+1);
	 	    		break;
		 	    case "RR":
		 	   		RR rr = new RR();
		 	   		System.out.println(i+1 + " RR");
	     			rr.run(process, lines, rrTime);
		 	    	break;
		 	    case "SRTF":
		 	    	SRTF srtf = new SRTF();
		 	    	srtf.srtf(process, i+1);
		 	    	break;
		 	    case "P":
		 	    	Arrays.sort(process, new Comparator<int[]>(){
		 		        public int compare(int[] o1, int[] o2){
		 		            return Integer.valueOf(o1[2]).compareTo(Integer.valueOf(o2[2]));
		 		        }
		 		    });
		 	    	P p = new P();
		 	    	System.out.println(i+1 + " P");
		 	    	p.run(process, lines);
		 	    	break;
	 	    }
	    }
	} 
}

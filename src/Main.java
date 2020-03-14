import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception { 
	    File file = new File("C:\\Users\\Morgan Yao\\Documents\\GitHub\\CS162AMidterm\\CS162A_Project01_Villanueva_Yao\\src\\InputFile.txt"); 
	    Scanner sc = new Scanner(file); 
	    int cases = Integer.parseInt(sc.next());
	   // System.out.println("Cases:" + cases);
	    for(int i = 0; i < cases; i++) {
	    	int lines = Integer.parseInt(sc.next());
	    	//System.out.println("Lines:" + lines);
	    	String type = sc.next();
	    	sc.nextLine();
	    	//System.out.println("Type:" + type);
	    	int[][] process = new int[lines][3];
	    	for(int j = 0; j < lines; j++) {
	    		for(int k = 0; k <3; k++) {
	    		process[j][k] = Integer.parseInt(sc.next());
	    		}
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
    		}
	    }
	}
//	private String printer(int lines, int[] process, int timeElapsed, int cpuBurst, int cpuUtil, int throughput, String waitTime, 
//						  String turnaroundTime, String responseTime) {
//		String printer = "";
//		return printer;
//	}
//	private String waitTime(int lines) {
//		String waitTime ="";
//		return waitTime;
//	}
//	private String turnaroundTime(int lines) {
//		String turnaroundTime = "";
//		return turnaroundTime;
//	}
//	private String responseTime(int lines) {
//		String responseTime = " ";
//		return responseTime;
//	}
}

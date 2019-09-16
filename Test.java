package p1;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class Test is the Driver class for Cache class
 * Author: Osama Natouf
 * V1.0
 * 
 * 
 */

public class Test {
	/**
	 *  Helper Function to determine if the inserted input can be parsed into integer or not
	 * @param str: the input
	 * @return True if it can be parsed and false otherwise
	 * @throws NumberFormatException
	 */
	
	
	private static boolean isInteger(String str) throws NumberFormatException {
		
		try {
			Integer.parseInt(str);
			return true;
			
		}catch(Exception e) {
			e= new NumberFormatException("The specifed value "+"\""+str+"\""+" cannot be parsed into integer");
			
			System.out.println(e.getMessage());
			Test.printUsage();
			System.exit(0);
			return false;
		
		}
	}
	/**
	 * Helper method to print how to use the program 
	 */
	private static void printUsage() {
		
		System.out.println("The correct Argument for the program is \n"
				+ "java Test 1 <Positive Integer cache size> <input textfile name> or\n" + 
				"java Test 2 <1st-level Positive integer cache size> <2nd-level Positive Integer cache size> <input textfile name>");
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// check the arguments of the program
	
		if(args.length==0||(args.length!=3&&args.length!=4)) {
			System.out.println("The arguments passed to the program is not Correct");
			printUsage();
			System.exit(0);
		}else {
			//chech is the args[0] can be paresed into int
			
			if(isInteger(args[0])) {
		
				int option=Integer.parseInt(args[0]);
				//check if args[0]==1
				//check if the size can be parsed into int
				if(option==1 && args.length==3) {//starts of if
						int wordCount=0;
						try {
							Test.isInteger(args[1]);
							Cache<String>c= new Cache<String>(Integer.parseInt(args[1]));
							
							File file= new File(args[2]); 
							c.printinfo();
							Scanner scan= new Scanner(file);
							while(scan.hasNextLine()) {
								StringTokenizer str=new StringTokenizer(scan.nextLine());
								while(str.hasMoreTokens()) {
									c.addObj(str.nextToken());
									wordCount++;
									if(wordCount==30000) {
										System.out.print(".");
										wordCount=0;
										}
									}// end of the second while
								}// end of first while
							System.out.println();
							c.PrintData();
							scan.close();
						}//end of try
						catch(Exception e) {
							System.out.println(e.getMessage());
							Test.printUsage();
							System.exit(0);
						}// end of catch 
					
				}//end of the 
				
				
				//check if args[0]==2
				//check if the size can be parsed into int
					
				else if(option==2 && args.length==4 ) {
					int wordCount=0;
					try {
						Test.isInteger(args[1]);
						Test.isInteger(args[2]);
						Cache<String>c= new Cache<String>(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
						
						File file= new File(args[3]); 
						c.printinfo();
						Scanner scan= new Scanner(file);
						while(scan.hasNextLine()) {
							StringTokenizer str=new StringTokenizer(scan.nextLine());
							while(str.hasMoreTokens()) {
								c.addObj(str.nextToken());
								wordCount++;
								if(wordCount==30000) {
									System.out.print(".");
									wordCount=0;
									}
								}// end of the second while
							}// end of first while
						System.out.println();
						c.PrintData();
						scan.close();
					}//end of try
					catch(Exception e) {
						System.out.println(e.getMessage());
						Test.printUsage();
						System.exit(0);
					}// end of catch 
					
				
				}else {
					System.out.println("Wrong argument Input for the program");
					System.out.println("The first argument should be 1 or 2 ONLY");
					Test.printUsage();
					System.exit(0);
					
				}// end of else

			}//end of OUtTER
			
			
		}
		
		
		
	}

}

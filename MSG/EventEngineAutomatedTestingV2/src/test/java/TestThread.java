import java.util.Scanner;

public class TestThread 
 {

   public static void main(String args[]) 
   {
     
	   
	   
	   	Scanner input = new Scanner(System.in);
	    System.out.println("Please input the number of Threads you want to create: ");
	    int n = input.nextInt();
	    System.out.println("You selected " + n + " Threads");

	    for (int x=0; x<n; x++)
	    {
	        MyThread temp= new MyThread("Thread #" + x);
	        //MyThread temp= new MyThread("Test -- > " + x );
	        temp.testName = "Test -- > " + x;
	        temp.start();
	        //temp.start();
	        //System.out.println("Started Thread:" + x);
	    }
	   
	   
	   
	   
	   
	  /*RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start();*/
   }   
}
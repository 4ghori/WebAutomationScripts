import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class MyThread implements Runnable  
{ 

	   private Thread t;
	   
	   public  String testName;

	
	  public MyThread (String s) 
	  { 
	    //super(s); 
		  testName = s;
	  }
	
	  
	  public void start () 
	  {
	      System.out.println("Starting " +  "" );
	      if (t == null) 
	      {
	         t = new Thread (this, "");
	         t.start ();
	      }
	  }
	  
	  
	  
	  synchronized  public void run() 
	  { 
	    //System.out.println("Run: "+ getName()); 
	    loadWebDriver();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  } 
	  
	  
	  public void loadWebDriver()
	  {
		  
		  System.out.println(testName);
			//WebDriver driver ; 
			//driver = new ChromeDriver();
	
	  }
	  
  
}

public class Stringworks {

	public static void main(String[] args) 
	{
		
		
		  	String boldGrayLine = "\\033[0;1m This is a BOLD line\\033[0;1m";
	        String setBold = "\033[1mThis keeps it bold.";
	        String normalLine = "This is a normal (default) line";
	        String setNormal = "\033[0mThis un-bolds it.";
	        System.out.println(normalLine);
	        System.out.println(boldGrayLine);
	        System.out.println(normalLine);
	        System.out.println(setBold);
	        System.out.println(normalLine);
	        System.out.println(setNormal);
	        System.out.println(normalLine);
		
		
		
		
		
		/*System.out.println(
				   "Failed in \t:\t ["+thisClassName+ "->" + thisMethodName +"]\n"
				 + "Failed reason \t:\t Event/Event ID API Response doesn't Match with \n" 
				 +  expResponseAttribute + ":" + expResponseValue);*/
		
		String separator = "|";
		String expResponseAttribute = "category";
		String expResponseValue = "[{name="+"Category Name1"+"}, {name="+"Category Name 2"+"}]";

		
		//printfailreport(sFailedInClass, sFailedinMethod, sFailReason, sExpectedValue, sActualValue);
		printfailreport("Page", "Methodx", "Test Reason", expResponseAttribute+":"+ separator + expResponseValue, "some actual values");
		
		
		
	}
	
	
	
	public static void printfailreport(String sFailedInClass,String sFailedinMethod, String sFailReason, String sExpectedValue, String sActualValue)
	{
		System.out.println("***************Failed Reporting ************-**********************\n");
		System.out.println("Failed in \t:\t ["+sFailedInClass+ " -> " + sFailedinMethod +"]");
		System.out.println("Failed reason \t:\t "+sFailReason);

		//==Printing Expected Values 
		String[] sTempExpectedValueArray = sExpectedValue.split("\\|");
		for(int i=0;i<sTempExpectedValueArray.length;i++)
		{
			if(i>0) //Subsequent Print
			{
				System.out.print(" " + sTempExpectedValueArray[i]);
			}
			else //First Time Print 
			{
				System.out.print("Expected \t:\t" + sTempExpectedValueArray[i]);
			}
		}
		System.out.println("");
		
		
		//==Printing Actual Values 
		String[] sTempActualValueArray = sActualValue.split("\\|");
		for(int i=0;i<sTempActualValueArray.length;i++)
		{
			if(i>0) //Subsequent Print
			{
				System.out.print(" " + sTempActualValueArray[i]);
			}
			else //First Time Print 
			{
				System.out.print("Actual \t\t:\t" + sTempActualValueArray[i]);
			}
		}
		System.out.println("\n\n********************************XX**********************************\n");
	}

}

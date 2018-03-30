package com.msg.qa.tests.MSGOnlineDigital.SalesCenter;
import java.io.IOException;
import java.lang.reflect.Constructor; 
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.ITestAnnotation;

import Utility.Parsers.TestExcelParser;

import org.testng.IAnnotationTransformer; 

public class MSGTestNGAnnotationTransfer implements IAnnotationTransformer { 
	
	public void transform(ITestAnnotation annotation, Class testClass,  
	      Constructor testConstructor, Method testMethod)  
	  { 
		  /**
			 * Fetch the list of Test Cases needs to be run from Excel
			 */
			TestExcelParser myExcelParser;
			try {
				myExcelParser = new TestExcelParser();
				List<String> myTestCases = myExcelParser.getAllTestCasesOfTestCycle(System.getProperty("testCycle"));
				if(myTestCases.contains(testMethod.getName()))
				{
					annotation.setEnabled(true);	
				}
				else
				{
					annotation.setEnabled(false);	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } 
	 
	}
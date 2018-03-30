package com.msg.qa.MSGOnlineDigital.Rockettes;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class RockettesRegressionScriptExample extends AbstractPage {
	MSGRockettesGlobalFunctions globalFunction = new MSGRockettesGlobalFunctions(driver);
        private static final Logger logger = Logger.getLogger(RockettesRegressionScriptExample.class);

        //************ LOGIN OBJECTS ************//
        public String testAutomationInstance;
        public String frontEndHomePageURL;
        public String FrontEndHomePageURLRefactored = frontEndHomePageURL + "?rfc=1";
        public String loginResults;
        //************ LOGIN OBJECTS ************//
    

        public RockettesRegressionScriptExample(WebDriver driver) {
                super(driver);
        }
    
      

        //************ IDENTIFIER OBJECTS ************//
        //************ IDENTIFIER OBJECTS ************//
        //************ IDENTIFIER OBJECTS ************//

        //************ MSG ROCKETTES FRONT END HOMEPAGE OBJECTS ************//

        @FindBy(xpath = "//section[@id='main'][@class='gallerylanding-page']/div[@class='container']")
        public ExtendedWebElement statusPhotosPhotoRow;

        //************ MSG ROCKETTES FRONT END HOMEPAGE OBJECTS ************//



        //************ IDENTIFIER OBJECTS ************//
        //************ IDENTIFIER OBJECTS ************//
        //************ IDENTIFIER OBJECTS ************//

        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
        //************ BEGIN FUNCTIONS ************//
    
        // ***************** OPEN THE PHOTO PAGE ON THE FRONT END  ***************** // 
        public void gotoPhotosPage(){        
                driver.get("https://www.rockettes.com/photos/");
                List <WebElement> photoRowChildArray = statusPhotosPhotoRow.getElement().findElements(By.xpath("//div[@class='flexslider']")); 
                WebElement photoRowChildArrayFlexport = photoRowChildArray.get(0).findElement(By.xpath("div[@class='flex-viewport']"));
                List <WebElement> firstRowOfPhotos = photoRowChildArrayFlexport.findElement(By.xpath("ul[@class='slides']")).findElements(By.xpath("li[@class='slide']"));
                logger.info(firstRowOfPhotos.size());
        }
        // ***************** OPEN THE HOMEPAGE ON THE FRONT END  ***************** // 


    
    
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//
        //************ END FUNCTIONS ************//

}




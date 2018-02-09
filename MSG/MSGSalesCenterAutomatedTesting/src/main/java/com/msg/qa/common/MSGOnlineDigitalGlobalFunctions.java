package com.msg.qa.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("deprecation")
public class MSGOnlineDigitalGlobalFunctions extends AbstractPage {
	private static final Logger logger = Logger
			.getLogger(MSGOnlineDigitalGlobalFunctions.class);
	public String jsonParsedObject;

	public MSGOnlineDigitalGlobalFunctions(WebDriver driver) {
		super(driver);
		open();
	}

	// ************ BEGIN FUNCTIONS ************//

	public static File captureElementPicture(WebElement element)
			throws Exception {

		// get the WrapsDriver of the WebElement
		WrapsDriver wrapsDriver = (WrapsDriver) element;

		// get the entire screenshot from the driver of passed WebElement
		File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver())
				.getScreenshotAs(OutputType.FILE);

		// create an instance of buffered image from captured screenshot
		BufferedImage img = ImageIO.read(screen);

		// get the width and height of the WebElement using getSize()
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		// create a rectangle using width and height
		Rectangle rect = new Rectangle(width, height);

		// get the location of WebElement in a Point.
		// this will provide X & Y co-ordinates of the WebElement
		Point p = element.getLocation();

		// create image for element using its location and size.
		// this will give image data specific to the WebElement
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,
				rect.height);

		// write back the image data for element in File object
		ImageIO.write(dest, "png", screen);

		// return the File object containing image data
		return screen;
	}

	public String currentTime() {
		return String.valueOf(java.lang.System.currentTimeMillis());
	}

	public void clickWebElement(ExtendedWebElement extendedWebElementParent,
			String xPathChildWebElement) {
		WebElement webElementToClick = extendedWebElementParent.getElement()
				.findElement(By.xpath(xPathChildWebElement));
		webElementToClick.click();
	}

	public void clickListedElement(ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int elementInList) {
		List<WebElement> listedWebElementToClick = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		listedWebElementToClick.get(elementInList).click();
	}

	public void validate(ExtendedWebElement itemToValidate) {
		Assert.assertTrue(isElementPresent(itemToValidate, 60),
				itemToValidate.toString() + " is missing!");
		logger.info("I see " + itemToValidate.getName());
	}

	public void validateText(String textToValidate,
			String textValidatedAgainst) {
		Assert.assertEquals(textValidatedAgainst, textToValidate,
				"'" + textToValidate + "' is not the same as expected value '"
						+ textValidatedAgainst + "'!");
	}

	public void validateList(List<String> textToValidate,
			List<String> textValidatedAgainst) {
		Assert.assertEquals(textValidatedAgainst, textToValidate,
				"'" + textToValidate + "' is not the same as expected value '"
						+ textValidatedAgainst + "'!");
	}

	public void validateTextContains(String textContainer,
			String textContained) {
		Assert.assertTrue(textContainer.contains(textContained));
	}

	public void invalidate(ExtendedWebElement itemToInvalidate) {
		Assert.assertFalse(isElementPresent(itemToInvalidate, 60),
				itemToInvalidate.toString() + " is missing!");
		logger.info("I don't see " + itemToInvalidate.getName());
	}

	public void invalidateText(String textToInvalidate,
			String textInvalidatedAgainst) {
		Assert.assertNotEquals(textInvalidatedAgainst, textToInvalidate,
				"'" + textToInvalidate + "' is not the same as expected value '"
						+ textInvalidatedAgainst + "'!");
	}

	public void validateWebElement(ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		WebElement itemToValidate = parentExtendedWebElement.getElement()
				.findElement(By.xpath(xPathChildWebElement));
		Assert.assertTrue(itemToValidate.isDisplayed(),
				itemToValidate.toString() + " is missing!");
		logger.info("I see " + itemToValidate.getTagName());
	}

	public void invalidateWebElement(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		WebElement itemToValidate = parentExtendedWebElement.getElement()
				.findElement(By.xpath(xPathChildWebElement));
		Assert.assertFalse(itemToValidate.isDisplayed(),
				itemToValidate.toString() + " is missing!");
		logger.info("I don't see " + itemToValidate.getTagName());
	}

	public void validateWebElementList(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		int n = childWebElementList.size();
		logger.info(n);
		for (int a = 0; a < n; a++) {
			logger.info(a);
			Assert.assertTrue(childWebElementList.get(a).isDisplayed(),
					childWebElementList.get(a).toString() + " is missing!");
			logger.info("I see " + childWebElementList.get(a).getText());
		}
	}

	public void validateWebElementListEquals(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, List<String> listToValidateAgainst,
			int startingElement) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		int q = listToValidateAgainst.size();
		for (int a = startingElement; a < startingElement + q; a++) {
			logger.info(a);
			Assert.assertEquals(childWebElementList.get(a).getText(),
					listToValidateAgainst.get(a - startingElement));
			logger.info(
					"I see " + childWebElementList.get(a).getText() + " equals "
							+ listToValidateAgainst.get(a - startingElement));
		}
	}

	public void logWebElementListText(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		int n = childWebElementList.size();
		for (int a = 0; a < n; a++) {
			logger.info(a);
			logger.info("Element Number " + a + " is "
					+ childWebElementList.get(a).getText());
		}
	}

	public List<String> getWebElementListText(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		List<String> webElementListText = new ArrayList<String>();
		int n = childWebElementList.size();
		for (int a = 0; a < n; a++) {
			logger.info(a);
			logger.info("Element Number " + a + " is "
					+ childWebElementList.get(a).getText());
			webElementListText.set(a, childWebElementList.get(a).getText());
		}
		return webElementListText;
	}

	public String getWebElementText(ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		WebElement childWebElementList = parentExtendedWebElement.getElement()
				.findElement(By.xpath(xPathChildWebElement));
		logger.info("Element Text is " + childWebElementList.getText());
		return childWebElementList.getText();
	}

	public String getListedWebElementText(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int n) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		logger.info("Element Number " + n + " is "
				+ childWebElementList.get(n).getText());
		return childWebElementList.get(n).getText();
	}

	public void validateWebElementOCR(WebElement objectToOCR,
			String textToCompare) throws Exception {
	}

	public void invalidateWebElementList(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		int n = childWebElementList.size();
		for (int a = 0; a < n; a++) {
			Assert.assertFalse(childWebElementList.get(a).isDisplayed(),
					childWebElementList.get(a).toString() + " is missing!");
			logger.info("I don't see " + childWebElementList.get(a).getText());
		}
	}

	public void validateWebElementListAbsolute(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int startingElementNumber,
			int numberOfElements) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		logger.info("Set to return an absolute number of " + numberOfElements
				+ " elements");
		for (int a = 0; a < numberOfElements; a++) {
			logger.info(a + startingElementNumber);
			Assert.assertTrue(
					childWebElementList.get(a + startingElementNumber)
							.isDisplayed(),
					childWebElementList.get(a + startingElementNumber)
							.toString() + " is missing!");
			logger.info("I see " + childWebElementList
					.get(a + startingElementNumber).getText());
		}
	}

	public void invalidateWebElementListAbsolute(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int startingElementNumber,
			int numberOfElements) {
		List<WebElement> childWebElementList = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		logger.info("Set to return an absolute number of " + numberOfElements
				+ " elements");
		for (int a = 0; a < numberOfElements; a++) {
			logger.info(a + startingElementNumber);
			Assert.assertFalse(
					childWebElementList.get(a + startingElementNumber)
							.isDisplayed(),
					childWebElementList.get(a + startingElementNumber)
							.toString() + " is missing!");
			logger.info("I see " + childWebElementList
					.get(a + startingElementNumber).getText());
		}
	}

	public void mouseOverElement(ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		Actions action = new Actions(driver);
		WebElement elementToBeMousedOver = parentExtendedWebElement.getElement()
				.findElement(By.xpath(xPathChildWebElement));
		action.moveToElement(elementToBeMousedOver).build().perform();
	}

	public void mouseOverListedElement(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int elementInList) {
		Actions action = new Actions(driver);
		List<WebElement> elementListToBeMousedOver = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		action.moveToElement(elementListToBeMousedOver.get(elementInList))
				.build().perform();
	}

	public WebElement getListedElement(
			ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement, int elementInList) {
		List<WebElement> elementListToBeMousedOver = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		return elementListToBeMousedOver.get(elementInList);
	}

	public int getSizeOfElementList(ExtendedWebElement parentExtendedWebElement,
			String xPathChildWebElement) {
		List<WebElement> elementListToBeMousedOver = parentExtendedWebElement
				.getElement().findElements(By.xpath(xPathChildWebElement));
		return elementListToBeMousedOver.size();
	}

	public String stripAllNonAlpha(String stringToStrip) {
		return stringToStrip.replaceAll("[^a-zA-Z]", "");
	}

	public String stripAllNonNumeric(String stringToStrip) {
		return stringToStrip.replaceAll("[^0-9]", "");
	}

	public String stripAllNonAlphaNumeric(String stringToStrip) {
		return stringToStrip.replaceAll("[^a-zA-Z0-9]", "");
	}

	public String stripAllWhiteSpaces(String stringToStrip) {
		return stringToStrip.replaceAll("\\s", "");
	}

	public void imageFind(String imageToBeFound) throws InterruptedException {
	}

	public void imageFindAll(String imageToBeFound)
			throws InterruptedException {
	}

	public void imageWaitToAppear(String imageToBeCheckedIfAppears)
			throws InterruptedException {
	}

	public void imageWaitToDisappear(String imageToBeCheckedIfDisappears)
			throws InterruptedException {
	}

	public void imageExists(String imageToBeCheckedIfExists)
			throws InterruptedException {
	}

	public void imageClick(String imageToBeClicked)
			throws InterruptedException {
	}

	public void imageDoubleClick(String imageToBeDoubleClicked)
			throws InterruptedException {
	}

	public void imageRightClick(String imageToBeRightClicked)
			throws InterruptedException {
	}

	public void imageHover(String imageToBeHoveredOver)
			throws InterruptedException {
	}

	public void imageDragAndDrop(String imageToBeDragged, String imageToDropTo)
			throws InterruptedException {
	}

	public void mouseOverVideoPlayer(
			ExtendedWebElement videoPlayerContainerParent,
			String xPathVideoPlayerContainerChild, int n) {
		mouseOverListedElement(videoPlayerContainerParent,
				xPathVideoPlayerContainerChild, n);
	}

	public void scroll(int xAxis, int yAxis) {
		((JavascriptExecutor) driver)
				.executeScript("scroll(" + xAxis + "," + yAxis + ");");
	}

	public WebDriver switchToFrameByIdOrName(String frameNameOrId) {
		String frameElementXpath = String
				.format("//frame[@name='%1$s' or @id='%1$s']", frameNameOrId);
		WebElement f = driver.findElement(By.xpath(frameElementXpath));
		return driver.switchTo().frame(f);
	}

	public void switchToWindowWithTitle(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

	public JsonElement jsonObjectParser(String jsonInput,
			String jsonObjectInput) throws ParseException {
		JsonParser parser = new JsonParser();
		// The JsonElement is the root node. It can be an object, array, null or
		// java primitive.
		logger.info("JSON Before Element: " + jsonInput);
		JsonElement element = parser.parse(jsonInput);
		logger.info("JSON: " + element);
		JsonElement jsonObjectResult;
		JsonObject jsonObjects = element.getAsJsonObject();
		// use the isxxx methods to find out the type of jsonelement. In our
		// example we know that the root object is the Albums object and
		// contains an array of dataset objects
		if (element.isJsonObject()) {
			jsonObjects = element.getAsJsonObject();
			jsonObjectResult = jsonObjects.get(jsonObjectInput);
			// System.out.println(jsonObjects.get("networks").getAsString());
			JsonArray datasets = jsonObjects.getAsJsonArray(jsonObjectInput);
			/*
			 * for (int i = 0; i < datasets.size(); i++) { JsonObject dataset =
			 * datasets.get(i).getAsJsonObject(); //
			 * System.out.println(dataset.get("album_title").getAsString()); }
			 */
		}
		jsonObjectResult = jsonObjects.get(jsonObjectInput);
		return jsonObjectResult;
	}

	public String jsonParse(String jsonInput, String jsonTargetObject,
			String jsonTargetProperty, int jPropertyInstance,
			String jsonTargetItem) {
		JsonElement jelement = new JsonParser().parse(jsonInput);
		JsonObject jobject = jelement.getAsJsonObject();
		jobject = jobject.getAsJsonObject(jsonTargetObject);
		JsonArray jarray = jobject.getAsJsonArray(jsonTargetProperty);
		jobject = jarray.get(jPropertyInstance).getAsJsonObject();
		String result = jobject.get(jsonTargetItem).toString();
		return result;
	}

	public String jsonParseTest(String jsonInput, String jsonTargetObject) {
		JsonElement jelement = new JsonParser().parse(jsonInput);
		JsonObject root = jelement.getAsJsonObject();
		String objectValue = root.get(jsonTargetObject).getAsString();
		return objectValue;
	}

	public boolean doesThisIncrementingWebElementExist(
			String elementToCheckIfItExists) throws NoSuchElementException {
		try {
			// if it exists return it, otherwise and exception is thrown
			return driver.findElement(By.xpath(elementToCheckIfItExists))
					.isDisplayed();
		} catch (NoSuchElementException exceptionA) {
		}
		return false;
	}

}

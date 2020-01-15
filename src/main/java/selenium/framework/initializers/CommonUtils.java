package selenium.framework.initializers;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonUtils extends BaseClass {
	//

	/**
	 * @implNote this method is written to find the webElement.
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getWebElement(By locator) {
		WebElement element = null;
		try {
			element = getDriver().findElement(locator);
		} catch (StaleElementReferenceException sere) {
			waitUntilVisibilityOfElement(locator);
			element = getDriver().findElement(locator);
		} catch (NoSuchElementException nsee) {
			infoFail("Please check your locator:" + nsee);
		} catch (Exception e) {
			infoFail("Exception is" + e);
		}
		return element;
	}

	/**
	 * @implNote this method is written to get all web elements.
	 * @param locator
	 * @return List<WebElement
	 */
	public List<WebElement> getWebElements(By locator) {
		List<WebElement> element = null;
		try {
			element = getDriver().findElements(locator);
		} catch (StaleElementReferenceException sere) {
			waitUntilVisibilityOfElement(locator);
			element = getDriver().findElements(locator);
		} catch (NoSuchElementException nsee) {
			infoFail("Please check your locator :" + nsee);
		} catch (Exception e) {
			infoFail("exception is" + e);
		}
		return element;
	}

	/**
	 * @implNote this method is written to get text from all the columns.
	 * @param locator
	 * @return List<String>
	 */
	public List<String> getColumnText(By locator) {
		List<WebElement> element = null;
		List<String> columnText = new ArrayList<>();
		try {
			element = getDriver().findElements(locator);
			for (WebElement ele : element) {
				String text = ele.getText();
				columnText.add(text);
			}
		} catch (StaleElementReferenceException sere) {
			waitUntilVisibilityOfElement(locator);
		} catch (NoSuchElementException nsee) {
			infoFail("Please check your locator :" + nsee);
		} catch (Exception e) {
			infoFail("exception is" + e);
		}
		return columnText;
	}

	/**
	 * @implNote this method is written to click element
	 * @param locator
	 * @param identifier
	 */
	public void clickElement(By locator, String identifier) {
		try {
			getWebElement(locator).click();
			infoPass(identifier + " is clicked");
		} catch (ElementNotInteractableException enie) {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", getWebElement(locator));
		}
	}

	/**
	 * @implNote this method is written to get text from a element
	 * @param locator
	 * @param identifier
	 * @return String
	 */
	public String getText(By locator, String identifier) {
		String eleText = getWebElement(locator).getText();
		infoPass(identifier + " text is " + eleText);
		return eleText;
	}

	/**
	 * @implNote this method is written to check if element is displayed
	 * @param locator
	 * @return Boolean
	 */
	public boolean isElementDisplayed(By locator) {
		try {
			getDriver().findElement(locator);
			return true;
		} catch (NoSuchElementException nsee) {
			return false;
		}
	}

	/**
	 * @implNote this method is written to switch to new window when multiple
	 *           windows are open
	 * @param mainWindow
	 */
	public void switchToNewTab(String mainWindow) {
		for (String childWindow : getDriver().getWindowHandles()) {
			if (!childWindow.equals(mainWindow))
				switchToWindow(childWindow);
		}
	}

	/**
	 * @implNote this method is written to switch to new window
	 * @param window
	 */
	public void switchToWindow(String window) {
		getDriver().switchTo().window(window);
		infoPass("Switch to window " + getDriver().getTitle());
	}

	/**
	 * @implNote this method is written to close the active window
	 */
	public void closeWindow() {
		getDriver().close();
	}

	/**
	 * @implNote this method is written to verify element is displayed
	 * @param locator
	 * @param identifier
	 */
	public void verifyElementDisplayed(By locator, String identifier) {
		WebElement ele = getWebElement(locator);
		if (ele.isDisplayed()) {
			infoPass(identifier + " is displayed");
			Assert.assertTrue(true, identifier + " is displayed");
		} else {
			infoFail(locator + " is not displayed");
			Assert.assertTrue(false, identifier + " is not displayed");
		}
	}

	/**
	 * @implNote this method is written to verify the color of the element
	 * @param locator
	 * @param expectedColorHexadecimal
	 */
	public void verifyColor(By locator, String expectedColorHexadecimal) {
		String actualColor = getWebElement(locator).getCssValue("color");
		Assert.assertEquals(expectedColorHexadecimal, actualColor, "Color is matching");
		infoPass("color is matching expected: " + expectedColorHexadecimal + " actual color: " + actualColor);
	}

	/**
	 * @implNote this method is written to verify the background color of the
	 *           element
	 * @param locator
	 * @param expectedColorHexadecimal
	 * @param identifier
	 */
	public void verifyBackGroundColor(By locator, String expectedColorHexadecimal, String identifier) {
		String actualColor = getWebElement(locator).getCssValue("background-color");
		Assert.assertEquals(expectedColorHexadecimal, actualColor, "Color is matching");
		infoPass(identifier + " background color " + actualColor + " matching with the expected color "
				+ expectedColorHexadecimal);
	}

	/**
	 * @implNote this method is written to verify the URL of the driver.
	 * @param expectedUrl
	 */
	public void verifyUrl(String expectedUrl) {
		String actualUrl = getDriver().getCurrentUrl();
		if (expectedUrl.equals(actualUrl)) {
			infoPass("Expected URL " + expectedUrl + " is matching with actual " + actualUrl);
			Assert.assertTrue(true, "expected " + expectedUrl + " is matching with actual " + actualUrl);
		} else {
			infoFail("Expected URL " + expectedUrl + " is not matching with actual " + actualUrl);
			Assert.assertTrue(false, "expected " + expectedUrl + " is not matching with actual " + actualUrl);
		}
		Assert.assertEquals(expectedUrl, actualUrl, "URL is matching");
	}

	/**
	 * @implNote this method is written to wait
	 * @param seconds
	 */
	public void waitThread(long seconds) {
		long startTime = System.currentTimeMillis() / 1000;
		long endTime = startTime + seconds;
		do {
			infoPass("wait explicit....");
		} while (endTime >= System.currentTimeMillis() / 1000);
	}

	/**
	 * @implNote this method is written to check if text is in bold
	 * @param locator
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkTextIsBold(By locator) {
		try {
			boolean isBold = false;
			String actualStyle = getWebElement(locator).getAttribute("style");
			if (actualStyle.equals("bold"))
				isBold = true;

			return isBold;
		} catch (NoSuchElementException nsee) {
			return false;
		}
	}

	/**
	 * @implNote this method is written to verify that list contains the specified
	 *           text
	 * @param list
	 * @param valueToSearch
	 */
	public void verifyListContainsText(List<String> list, String valueToSearch) {
		if (list.contains(valueToSearch))
			infoPass(list + "contains " + valueToSearch);
		else
			infoFail(list + "does not contain " + valueToSearch);

	}

	/**
	 * @implNote this method is written to verify all the list values is equal to
	 *           the specified value
	 * @param list
	 * @param value
	 */
	public void verifyAllListValueIsEqualToText(List<String> list, String value) {
		List<String> listContains = new ArrayList<>();
		List<String> listNotContains = new ArrayList<>();
		for (String eachValue : list) {
			if (eachValue.equals(value))
				listContains.add(value);
			else
				listNotContains.add(value);
		}
		if (listNotContains.isEmpty())
			infoPass(list + "is equal to " + value);
		else
			infoFail(list + "is not equal to " + value);		
	}

	/**
	 * @implNote this method is written to verify two strings are not equal
	 * @param actual
	 * @param expected
	 */
	public void stringNotEqualsTo(String actual, String expected) {
		if (actual.equals(expected))
			infoFail(actual + " value is still the same as " + expected);
		else
			infoPass(actual + " value is not the same as " + expected);
	}

	/**
	 * @implNote this method is written to get the attribute value
	 * @param locator
	 * @param attributeName
	 * @return String
	 */
	public String getAttributeValue(By locator, String attributeName) {
		return getWebElement(locator).getCssValue(attributeName);
	}

	/**
	 * @implNote this method is written to wait until the element is visible
	 * @param locator
	 */
	public void waitUntilVisibilityOfElement(By locator) {
		WebDriverWait wait;
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		infoPass("waiting for element to be visible " + locator);
	}

	/**
	 * @implNote this method is written to verify the text in specific element
	 * @param locator
	 * @param expectedText
	 */
	public void verifyTextInElement(By locator, String expectedText,String identifier) {
		String eleText = getWebElement(locator).getText();
		assertEquals(eleText, expectedText);
		infoPass(identifier + " text is " + eleText);

	}

	/**
	 * @implNote this method is written to verify element is a link
	 * @param locator
	 * @param identifier
	 */
	public void verifyElementIsALink(By locator,String identifier) {
	  WebElement ele = getWebElement(locator);
	  if(ele.getTagName().equals("a"))
		  infoPass(identifier+ " is a link");
	  else
		  infoFail(identifier+ " is not a link");
	}
	
	public void infoPass(String comments) {
		logger.log(Status.PASS, comments);
	}

	public void infoFail(String comments) {
		logger.log(Status.FAIL, comments);
	}

}
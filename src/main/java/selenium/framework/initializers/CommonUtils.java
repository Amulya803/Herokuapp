package selenium.framework.initializers;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonUtils extends  BaseClass{
    private WebDriverWait wait;

    public WebElement getWebElement(By locator){
        WebElement element= null;
        try{
            element=getDriver().findElement(locator);
        }catch (StaleElementReferenceException sere){
            waitUntilVisibilityOfElement(locator);
            element=getDriver().findElement(locator);
        }catch (NoSuchElementException nsee){
            System.out.println("Please check your locator :"+nsee);
            infoFail("Please check your locator :"+nsee);
        }catch (Exception e){
            System.out.println("exception is"+e);
            infoFail("exception is"+e);
        }
        return element;
    }

    public List<WebElement> getWebElements(By locator){
        List<WebElement> element= null;
        try{
            element=getDriver().findElements(locator);
        }catch (StaleElementReferenceException sere){
            waitUntilVisibilityOfElement(locator);
            element=getDriver().findElements(locator);
        }catch (NoSuchElementException nsee){
            System.out.println("Please check your locator :"+nsee);
            infoFail("Please check your locator :"+nsee);
        }catch (Exception e){
            System.out.println("exception is"+e);
            infoFail("exception is"+e);
        }
        return element;
    }

    public List<String> getColumnText(By locator){
    	List<WebElement> element = null;
    	List<String> columnText = new ArrayList<>();
    	try {
    		 element=getDriver().findElements(locator);
    		 for(WebElement ele:element) {
    			 String text = ele.getText();
    			 columnText.add(text);
    		 }
    	}catch (StaleElementReferenceException sere){
            waitUntilVisibilityOfElement(locator);
        }catch (NoSuchElementException nsee){
            System.out.println("Please check your locator :"+nsee);
            infoFail("Please check your locator :"+nsee);
        }catch (Exception e){
            System.out.println("exception is"+e);
            infoFail("exception is"+e);
        }
        return columnText;
    }

      
    /**
     * To click an element
     * @param locator xpath
     */
    public void clickElement(By locator,String identifier) {
        try{
            getWebElement(locator).click();
            infoPass(identifier+" is clicked");
        }catch (ElementNotInteractableException enie){
            JavascriptExecutor js=(JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", getWebElement(locator));
        }
    }

 
    /**
     * To read a static element
     * @param locator xpath
     * @return String
     */
    public String getText(By locator,String identifier) {
        String eleText=getWebElement(locator).getText();
        infoPass(identifier+" text is "+eleText);
        return eleText;
    }

    
    public void infoPass(String comments){
        logger.log(Status.PASS, comments);
    }

    public void infoFail(String comments){
        logger.log(Status.FAIL, comments);
    }

    public boolean isElementDisplayed(By locator){
        try{
            getDriver().findElement(locator);
            return true;
        }catch (NoSuchElementException nsee){
            return  false;
        }
    }

    public List<String> getElementsText(By locator) {
        List<WebElement> allElements=getAllWebElement(locator);
        List<String> allText=new ArrayList<>();
        for (WebElement eachElement:allElements){
            allText.add(eachElement.getText());
        }
        infoPass(locator+" element texts are "+allText);
        return allText;
    }


    public List<WebElement> getAllWebElement(By locator){
        wait=new WebDriverWait(getDriver(),120);
        List<WebElement> allElements=new ArrayList<>();
        try{
            allElements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        }catch (StaleElementReferenceException sere){
            allElements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        }
        infoPass("waiting for element to be visible "+locator);
        return allElements;
    }

    public void switchToNewTab(String mainWindow){
        for(String childWindow : getDriver().getWindowHandles()){
            if(!childWindow.equals(mainWindow))
                switchToWindow(childWindow);
        }
    }

    public void switchToWindow(String window){
        getDriver().switchTo().window(window);
        infoPass("Switch to window "+getDriver().getTitle());
    }

    public void closeWindow(){
        getDriver().close();
    }

    public void verifyElementDisplayed(By locator,String identifier){
        if (getWebElement(locator).isDisplayed()){
            infoPass(identifier+ " is displayed");
            Assert.assertTrue(true,identifier+" is displayed");
        }else {
            infoFail(locator+ " is not displayed");
            Assert.assertTrue(false,identifier+" is not displayed");
        }
    }

    public void pressTABKey(By locator) {
        getWebElement(locator).sendKeys(Keys.TAB);
        infoPass("TAB key is clicked");
    }

    /**
     * To enter value in text box
     * @param locator xpath
     * @param expectedColorHexadecimal text to enter
     */
    public void verifyColor(By locator, String expectedColorHexadecimal) {
        String actualColor=getWebElement(locator).getCssValue("color");
        Assert.assertEquals(expectedColorHexadecimal,actualColor,"Color is matching");
        infoPass("color is matching expected: "+expectedColorHexadecimal+" actual color: "+actualColor);
    }

    public void verifyBackGroundColor(By locator, String expectedColorHexadecimal,String identifier) {
        String actualColor=getWebElement(locator).getCssValue("background-color");
        Assert.assertEquals(expectedColorHexadecimal,actualColor,"Color is matching");
        infoPass(identifier+" background color "+actualColor+" matching with the expected color "+expectedColorHexadecimal);
    }

 
    public void verifyUrl(String expectedUrl){
       String actualUrl=getDriver().getCurrentUrl();
       if(expectedUrl.equals(actualUrl)){
           infoPass("expected "+expectedUrl+" is matching with actual "+actualUrl);
           Assert.assertTrue(true,"expected "+expectedUrl+" is matching with actual "+actualUrl);
       }else{
           infoFail("expected "+expectedUrl+" is not matching with actual "+actualUrl);
           Assert.assertTrue(false,"expected "+expectedUrl+" is not matching with actual "+actualUrl);
       }
       Assert.assertEquals(expectedUrl,actualUrl,"URL is matching");
    }

    public void waitThread(long seconds){
        long startTime= System.currentTimeMillis()/1000;
        long endTime=startTime+seconds;
        do{
            System.out.println("wait explicit....");
        }while(endTime>=System.currentTimeMillis()/1000);
    }
    
    public Boolean checkTextIsBold(By locator) throws Exception{
    	try {
    		boolean isBold= false;
    		String actualStyle=getWebElement(locator).getAttribute("style");
    		if(actualStyle.equals("bold"))
    		isBold=true;
    			
         return isBold;
    	}catch (NoSuchElementException nsee){
            return  false;
        }
    }

    public void verifyListContainsText(List<String> list,String valueToSearch) {
      if(list.contains(valueToSearch)) 
    	  infoPass(list+ "contains "+ valueToSearch);
      else
    	  infoFail(list+ "does not contain "+ valueToSearch);
    	  
    }
    
    public void verifyAllListValueIsEqualToText(List<String> list,String value) {
        List<String> listContains= new ArrayList<String>();
        List<String> listNotContains = new ArrayList<String>();
    	for(String eachValue:list) {
    		if(eachValue.equals(value))
    			listContains.add(value);
    		else
    			listNotContains.add(value);
    	}
    	if(listNotContains.size()>0)
    		 infoFail(list+ "is not equal to "+value);
    	else
      	  infoPass(list+ "is equal to "+ value);    	  
      }
    
    public void stringNotEqualsTo(String actual,String expected) {
    	  if(actual.equals(expected))
          	infoFail(actual+" value is still the same as "+expected);
    	  else
    		  infoPass(actual+" value is not the same as "+expected);
    }
    
    public String getAttributeValue(By locator,String attributeName) {
    	String attributeValue = getWebElement(locator).getCssValue(attributeName);
    	return attributeValue;
    }
    
    public void waitUntilVisibilityOfElement(By locator){
        wait=new WebDriverWait(getDriver(),60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        infoPass("waiting for element to be visible "+locator);
    }
    
    public void verifyTextInElement(By locator,String expectedText) {
        String eleText=getWebElement(locator).getText();
        assertEquals(eleText, expectedText);
        infoPass(locator+" element text is "+eleText);
        
    }
   

}
package selenium.framework.testsuite.herokuapp;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import selenium.framework.initializers.BaseClass;
import selenium.framework.initializers.CommonUtils;
import selenium.framework.initializers.Constants;
import selenium.framework.objectrepo.herokuapp.ChallengingDomObj;
import selenium.framework.pageobject.herokuapp.ChallengingDomPageObj;

public class ChallengingDomPage extends BaseClass implements Constants {

    private CommonUtils utils;
    private ChallengingDomPageObj challengingDom;

    @BeforeClass()
    public void init(){
        utils=new CommonUtils();
        challengingDom = new ChallengingDomPageObj();
    }

	String[] expectedTableHeaders = { "Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action" };
	
	@Test(testName = "Verify_Challenging_DOM_Page_UI")
	public void verifychallengingDomUI() throws Exception {
		try {			

			logger = getInstance().createTest("Verify_Challenging_DOM_Page_UI");
	        
			// Verify the header text "Challenging DOM" will be displayed in bold
			utils.verifyElementDisplayed(ChallengingDomObj.HEADER_CHALLENGING_DOM, "Header");
			utils.getText(ChallengingDomObj.HEADER_CHALLENGING_DOM, "Header Text").equals(CHALLENGING_DOM);
			utils.checkTextIsBold(ChallengingDomObj.HEADER_CHALLENGING_DOM);

			// Verify the paragraph displayed below the 'challenging dom' header
			utils.verifyTextInElement(ChallengingDomObj.CHALLENGING_DOM_PARA, Constants.CHALLENGING_DOM_PARA,"Challenging Dom Para");

			// Verify the presence of 3 buttons in the webpage
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);

			// Verify the presence of a table next to the buttons
			utils.verifyElementDisplayed(ChallengingDomObj.TABLE, "Table");

			// Verify that the 'edit' and 'delete' links displayed in the 'Action' columns
			// are hyperlinks.
			utils.verifyElementDisplayed(ChallengingDomObj.editLink(1), EDIT_LINK);
			utils.verifyElementIsALink(ChallengingDomObj.editLink(1), EDIT_LINK);
			utils.verifyElementDisplayed(ChallengingDomObj.deleteLink(1), DELETE_LINK);
			utils.verifyElementIsALink(ChallengingDomObj.deleteLink(1), DELETE_LINK);
			
			// Verify the "Answer" label displayed below the table.
			utils.verifyElementDisplayed(ChallengingDomObj.LABEL_ANSWER, "Answer Display Area");

			// Verify the green banner with text "Fork me on GitHub" is displayed
			utils.verifyElementDisplayed(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, FORK_ME_ON_GIT_HUB);

			// Verify the presence of text "Powered by Elemental Selenium" at the bottom.
			utils.verifyElementDisplayed(ChallengingDomObj.FOOTER_TEXT, POWERED_BY_ELEMENTAL_SELENIUM);
			utils.verifyElementIsALink(ChallengingDomObj.LINK_ELEMENTAL_SELENIUM,ELEMENTAL_SELENIUM);
		} catch (Exception e) {
			throw new Exception();
		}
	}
	

	@Test(testName = "Verify_Blue_Button")
	public void verifyBlueButton() throws Exception {
		try {
	
			logger = getInstance().createTest("Verify_Blue_Button");
			   
			// Verify the background of the first button is Blue Color.
			utils.verifyBackGroundColor(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON_RGB, BLUE_BUTTON);

			// Verify the text inside the Blue button
			String buttonText = utils.getText(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

			// Click on the blue button.
			utils.clickElement(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);

			// Verify the text inside the Blue button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_Red_Button")
	public void verifyRedButton() throws Exception {
		try {
			
			logger = getInstance().createTest("Verify_Red_Button");
			
			// Verify the background of the second button is red Color.
			utils.verifyBackGroundColor(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON_RGB, RED_BUTTON);

			// Verify the text inside the Red button
			String buttonText = utils.getText(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

			// Click on the red button.
			utils.clickElement(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);

			// Verify the text inside the Red button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_Green_Button")
	public void verifyGreenButton() throws Exception {
		try {
			
			logger = getInstance().createTest("Verify_Green_Button");
			
			// Verify the background of the second button is green Color.
			utils.verifyBackGroundColor(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON_RGB, GREEN_BUTTON);
			
			// Verify the text inside the Green button
			String buttonText = utils.getText(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

			// Click on the Green button.
			utils.clickElement(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);

			// Verify the text inside the Green button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);
			utils.verifyListContainsText(challengingDom.expectedButtonValues(), buttonText);

		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_contents_of_the_table")
	public void verifyContentsOfTable() throws Exception {
		try {
			 
			logger = getInstance().createTest("Verify_contents_of_the_table");
			   
			// Verify the table headers.
			 List<String> headers = utils.getColumnText(ChallengingDomObj.TABLE_HEADERS);
			 assertEquals(headers.toArray(), expectedTableHeaders);
			 utils.infoPass("Table Headers are: "+headers);
			
			// Verify the values displayed under all the columns
			//Lorem Ipsum Dolor Sit Amet Diceret
            for(int i=0;i<expectedTableHeaders.length-1;i++)
			challengingDom.verifyColumnText(expectedTableHeaders[i],i);
          
            //Verify the values displayed under Action column
            List<String> editLinks = utils.getColumnText(ChallengingDomObj.LINKS_EDIT);
            utils.verifyAllListValueIsEqualToText(editLinks, EDIT);
            
            List<String> deleteLinks = utils.getColumnText(ChallengingDomObj.LINKS_DELETE);
            utils.verifyAllListValueIsEqualToText(deleteLinks, DELETE);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_edit_link_under_Action_column")
	public void verifyEditLinkUnderActionColumn() throws Exception {
		try {
			
			logger = getInstance().createTest("Verify_edit_link_under_Action_column");
			
			// Verify the number of rows and edit link in each row.
			List<String> editLinks = utils.getColumnText(ChallengingDomObj.LINKS_EDIT);
            utils.verifyAllListValueIsEqualToText(editLinks, EDIT);
            
			// Verify edit is a link.
            utils.verifyColor(ChallengingDomObj.editLink(1), LINK_COLOR);
            
			// Click on Edit link and verify the URL
            utils.clickElement(ChallengingDomObj.editLink(1), EDIT_LINK);
            utils.verifyUrl(EDIT_URL);
            
			// Verify the edit link is clickable in all the rows respectively.
            for(int i=1;i<=editLinks.size();i++) {
            	 utils.clickElement(ChallengingDomObj.editLink(i), EDIT_LINK);
                 utils.verifyUrl(EDIT_URL);
           }            	
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_delete_link_under_Action_column")
	public void verifyDeleteLinkUnderActionColumn() throws Exception {
		try {
			
			logger = getInstance().createTest("Verify_delete_link_under_Action_column");
			   
			// Verify the number of rows and delete link in each row.
			List<String> deleteLinks = utils.getColumnText(ChallengingDomObj.LINKS_DELETE);
            utils.verifyAllListValueIsEqualToText(deleteLinks, DELETE);
            
			// Verify delete is a link.
            utils.verifyColor(ChallengingDomObj.deleteLink(1), LINK_COLOR);
            
			// Click on Delete link and verify the URL
            utils.clickElement(ChallengingDomObj.deleteLink(1), DELETE_LINK);
            utils.verifyUrl(DELETE_URL);
           
			// Verify delete link is clickable in all the rows respectively.
            for(int i=1;i<=deleteLinks.size();i++) {
           	 utils.clickElement(ChallengingDomObj.deleteLink(i), DELETE_LINK);
                utils.verifyUrl(DELETE_URL);
          }
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_green_banner_Fork_me_on_GitHub")
	public void verifyGreenBannerForkMeOnGitHub() throws Exception {
		try {
			
			logger = extent.createTest("Verify_green_banner_Fork_me_on_GitHub");
			
			// Verify the green banner with text "Fork me on GitHub"
			utils.verifyElementDisplayed(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, FORK_ME_ON_GIT_HUB);

			// Click on the banner "Fork me on GitHub"
            utils.clickElement(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, FORK_ME_ON_GIT_HUB);
            utils.verifyUrl(GIT_HUB_URL);
            
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_text_Powered_by_Elemental_Selenium")
	public void verifyPoweredByElementalSelenium() throws Exception {
		try {
			logger = extent.createTest("Verify_text_Powered_by_Elemental_Selenium");
			String mainTab = driver.getWindowHandle();
		
			// Verify the text "Powered By Elemental Selenium"
			utils.verifyElementDisplayed(ChallengingDomObj.FOOTER_TEXT, POWERED_BY_ELEMENTAL_SELENIUM);
			
			// Verify that "Elemental Selenium" is a hyperlink.
            utils.verifyElementDisplayed(ChallengingDomObj.LINK_ELEMENTAL_SELENIUM, ELEMENTAL_SELENIUM);
            
			// Click on "Elemental Selenium" link.
            utils.clickElement(ChallengingDomObj.LINK_ELEMENTAL_SELENIUM, ELEMENTAL_SELENIUM);
           
            utils.switchToNewTab(mainTab);
            utils.verifyUrl(ELEMENTAL_SELENIUM_URL);
            
			// Close the new window.
            utils.closeWindow();
            utils.switchToWindow(mainTab);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test
	public void challengingDomPageLoad() {
		logger = extent.createTest("Challenging Dom Page Load");
		
		 RequestSpecification httpRequest = RestAssured.given();
		 Response response = httpRequest.get("https://the-internet.herokuapp.com/challenging_dom");
		 int statusCode = response.getStatusCode();
		 if(statusCode==200)
	    utils.infoPass("Page loaded successfully");
        else
        	utils.infoFail("Page not loaded");
	}

}
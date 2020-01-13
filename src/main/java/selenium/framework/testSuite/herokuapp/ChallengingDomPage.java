package selenium.framework.testSuite.herokuapp;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.framework.initializers.BaseClass;
import selenium.framework.initializers.CommonUtils;
import selenium.framework.initializers.Constants;
import selenium.framework.objectRepo.herokuapp.ChallengingDomObj;
import selenium.framework.pageObject.herokuapp.ChallengingDomPageObj;

public class ChallengingDomPage extends BaseClass implements Constants {

    private CommonUtils utils;
    private WebDriver driver;
    private ChallengingDomPageObj challengingDomPage;

    @BeforeClass()
    public void init(){
        driver=getDriver();
        utils=new CommonUtils();
        challengingDomPage = new ChallengingDomPageObj();
    }
        
	@Test(testName = "Verify_Challenging_DOM_Page_UI")
	public void verifyChallengingDomPageUI() throws Exception {
		try {			

			logger = getInstance().createTest("Verify_Challenging_DOM_Page_UI");
	        
			// Verify the header text "Challenging DOM" will be displayed in bold
			utils.verifyElementDisplayed(ChallengingDomObj.HEADER_CHALLENGING_DOM, "Header");
			utils.getText(ChallengingDomObj.HEADER_CHALLENGING_DOM, "Header Text").equals("Challenging DOM");
			utils.checkTextIsBold(ChallengingDomObj.HEADER_CHALLENGING_DOM);

			// Verify the paragraph displayed below the 'challenging dom' header
			utils.verifyTextInElement(ChallengingDomObj.CHALLENGING_DOM_PARA, Constants.CHALLENGING_DOM_PARA);

			// Verify the presence of 3 buttons in the webpage
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);
			utils.verifyElementDisplayed(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);

			// Verify the presence of a table next to the buttons
			utils.verifyElementDisplayed(ChallengingDomObj.TABLE, "Table");

			// Verify that the 'edit' and 'delete' links displayed in the 'Action' columns
			// are hyperlinks.
			utils.verifyElementDisplayed(ChallengingDomObj.editLink(1), EDIT_LINK);
			utils.verifyElementDisplayed(ChallengingDomObj.deleteLink(1), DELETE_LINK);

			// Verify the "Answer" label displayed below the table.
			utils.verifyElementDisplayed(ChallengingDomObj.LABEL_ANSWER, "Answer Display Area");

			// Verify the green banner with text "Fork me on GitHub" is displayed
			utils.verifyElementDisplayed(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, "Fork me on GitHub Image");

			// Verify the presence of text "Powered by Elemental Selenium" at the bottom.
			utils.verifyElementDisplayed(ChallengingDomObj.FOOTER_TEXT, "Powered by Elemental Selenium");

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
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

			// Click on the blue button.
			utils.clickElement(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);

			// Verify the text inside the Blue button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_FIRST, BLUE_BUTTON);
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

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
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

			// Click on the red button.
			utils.clickElement(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);

			// Verify the text inside the Red button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_SECOND, RED_BUTTON);
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

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
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

			// Click on the Green button.
			utils.clickElement(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);

			// Verify the text inside the Green button
			buttonText = utils.getText(ChallengingDomObj.BUTTON_THIRD, GREEN_BUTTON);
			utils.verifyListContainsText(challengingDomPage.expectedButtonValues(), buttonText);

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
			 assertEquals(headers.toArray(), EXPECTED_TABLE_HEADERS);
			 utils.infoPass("Table Headers are: "+headers);
			
			// Verify the values displayed under all the columns
			//Lorem Ipsum Dolor Sit Amet Diceret
            for(int i=0;i<EXPECTED_TABLE_HEADERS.length-1;i++)
			challengingDomPage.verifyColumnText(EXPECTED_TABLE_HEADERS[i],i);
          
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

	@Test(testName = "Verify_the_number_displayed_next_to_Answer_label")
	public void verifyNumDisplayedInAnswerLabel() throws Exception {
		try {
			
			logger = getInstance().createTest("Verify_the_number_displayed_next_to_Answer_label");
			
			// Verify the value displayed the text display area below the table
			utils.verifyElementDisplayed(ChallengingDomObj.LABEL_ANSWER, "Answer Display Area");
			String answerDisplayed = utils.getAttributeValue(ChallengingDomObj.XYZ, "canvas.strokeText");
		
			//String answerDisplayed = utils.getText(ChallengingDomObj.LABEL_ANSWER, "Answer Value");

			// Click on the blue button.
            utils.clickElement(ChallengingDomObj.BUTTON_FIRST, "First Button");
			
			// Verify the value displayed the text display area below the table
            String answerDisplayedFirstButton = utils.getText(ChallengingDomObj.LABEL_ANSWER, "Answer Value");
            utils.stringNotEqualsTo(answerDisplayedFirstButton, answerDisplayed); 
            
			// Click on the red button
            utils.clickElement(ChallengingDomObj.BUTTON_SECOND, "Second Button");
            
			// Verify the value displayed the text display area below the table
            String answerDisplayedSecondButton = utils.getText(ChallengingDomObj.LABEL_ANSWER, "Answer Value");
            utils.stringNotEqualsTo(answerDisplayedSecondButton, answerDisplayedFirstButton); 
            
			// Click on the green button
            utils.clickElement(ChallengingDomObj.BUTTON_THIRD, "Third Button");
            
			// Verify the value displayed the text display area below the table
            String answerDisplayedThirdButton = utils.getText(ChallengingDomObj.LABEL_ANSWER, "Answer Value");
            utils.stringNotEqualsTo(answerDisplayedThirdButton, answerDisplayedSecondButton); 
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_random_number_generated.")
	public void verifyRandomNumberGenerated() throws Exception {
		try {
			
			extent.createTest("Verify_random_number_generated");
			   
			List<String> answerValues = new ArrayList<>();
			// Verify the value displayed the text display area below the table

			// Click on the any button
			utils.clickElement(ChallengingDomObj.BUTTON_FIRST, "First Button");
			
			// Verify the value displayed the text display area below the table

			// Repeat the above 2 steps for 10 iteration and note all the values generated.
            for(int i=0;i<=10;i++) {
            	 utils.clickElement(ChallengingDomObj.BUTTON_THIRD, "Third Button");
            	 answerValues.add("");
            }
			
			// Check if any alphanumeric value is displayed.

		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_green_banner_Fork_me_on_GitHub")
	public void verifyGreenBannerForkMeOnGitHub() throws Exception {
		try {
			
			extent.createTest("Verify_green_banner_Fork_me_on_GitHub");
			
			// Verify the green banner with text "Fork me on GitHub"
			utils.verifyElementDisplayed(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, "Fork me on GitHub Image");

			// Click on the banner "Fork me on GitHub"
            utils.clickElement(ChallengingDomObj.IMAGE_FORK_ME_ON_GITHUB, "Fork mess on GitHub Image");
            utils.verifyUrl(GIT_HUB_URL);
            
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Test(testName = "Verify_text_Powered_by_Elemental_Selenium")
	public void verifyPoweredByElementalSelenium() throws Exception {
		try {
			extent.createTest("Verify_text_Powered_by_Elemental_Selenium");
			WebDriver driver = getDriver();
		
			// Verify the text "Powered By Elemental Selenium"
			utils.verifyElementDisplayed(ChallengingDomObj.FOOTER_TEXT, "Powered by Elemental Selenium");
			
			// Verify that "Elemental Selenium" is a hyperlink.
            utils.verifyElementDisplayed(ChallengingDomObj.LINK_ELEMENTAL_SELENIUM, "Elemental Selenium");
            
			// Click on "Elemental Selenium" link.
            utils.clickElement(ChallengingDomObj.LINK_ELEMENTAL_SELENIUM, "Elemental Selenium");
            String childWindow = driver.getWindowHandle();
            utils.switchToWindow(childWindow);
            utils.verifyUrl(ELEMENTAL_SELENIUM_URL);
            
			// Close the new window.
            utils.closeWindow();
		} catch (Exception e) {
			throw new Exception();
		}
	}


}
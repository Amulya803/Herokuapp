package selenium.framework.testSuite.herokuapp;

import org.testng.annotations.Test;

import selenium.framework.initializers.BaseClass;
import selenium.framework.initializers.CommonUtils;
import selenium.framework.initializers.Constants;
import selenium.framework.objectRepo.herokuapp.ChallengingDomObject;
import selenium.framework.pageObject.herokuapp.ChallengingDomPageObj;

public class ChallengingDomPage extends BaseClass implements ChallengingDomObject, Constants {

	ChallengingDomPageObj challengingDomPage = new ChallengingDomPageObj();
    CommonUtils utils = new CommonUtils();

	@Test(testName = "Verify_Challenging_DOM_Page_UI")
	public void verifyChallengingDomPageUI() throws Exception {
		try {			

			// Verify the header text "Challenging DOM" will be displayed in bold
			utils.verifyElementDisplayed(ChallengingDomObject.HEADER_CHALLENGING_DOM, "Header");
			utils.getText(ChallengingDomObject.HEADER_CHALLENGING_DOM, "Header Text").equals("Challenging DOM");
			utils.checkTextIsBold(ChallengingDomObject.HEADER_CHALLENGING_DOM);

			// Verify the paragraph displayed below the 'challenging dom' header
			utils.verifyTextInElement(ChallengingDomObject.CHALLENGING_DOM_PARA, Constants.CHALLENGING_DOM_PARA);

			// Verify the presence of 3 buttons in the webpage
			utils.verifyElementDisplayed(ChallengingDomObject.BUTTON_FIRST, "First Button");
			utils.verifyElementDisplayed(ChallengingDomObject.BUTTON_SECOND, "Second Button");
			utils.verifyElementDisplayed(ChallengingDomObject.BUTTON_THIRD, "Third Button");

			// Verify the presence of a table next to the buttons
			utils.verifyElementDisplayed(ChallengingDomObject.TABLE, "Table");

			// Verify that the 'edit' and 'delete' links displayed in the 'Action' columns
			// are hyperlinks.
			utils.verifyElementDisplayed(ChallengingDomObject.editLink(1), "Edit Link");
			utils.verifyElementDisplayed(ChallengingDomObject.deleteLink(1), "Delete Link");

			// Verify the "Answer" label displayed below the table.
			utils.verifyElementDisplayed(ChallengingDomObject.LABEL_ANSWER, "Answer Display Area");

			// Verify the green banner with text "Fork me on GitHub" is displayed
			utils.verifyElementDisplayed(ChallengingDomObject.IMAGE_FORK_ME_ON_GITHUB, "Fork me on GitHub Image");

			// Verify the presence of text "Powered by Elemental Selenium" at the bottom.
			utils.verifyElementDisplayed(ChallengingDomObject.FOOTER_TEXT, "Powered by Elemental Selenium");

		} catch (Exception e) {
			throw new Exception();
		}
	}
}
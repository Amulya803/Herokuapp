package selenium.framework.pageObject.herokuapp;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import selenium.framework.initializers.BaseClass;
import selenium.framework.initializers.CommonUtils;
import selenium.framework.initializers.Constants;
import selenium.framework.objectRepo.herokuapp.ChallengingDomObj;

public class ChallengingDomPageObj extends BaseClass implements Constants {

	CommonUtils utils;

	public ChallengingDomPageObj() {
		utils = new CommonUtils();

	}

	public void verifyColumnText(String columnName, int colNum) {
		List<String> columnValues = new ArrayList<>();
		List<String> actualRowValue = new ArrayList<>();
		List<WebElement> allValues = utils.getWebElements(ChallengingDomObj.eachColumnValue(columnName));
		columnValues = utils.getColumnText(ChallengingDomObj.eachColumnValue(columnName));
		for (int i = 0; i < allValues.size(); i++)
			actualRowValue.add(EXPECTED_COLUMN_VALUES[colNum].concat(String.valueOf(i)));
		assertEquals(actualRowValue, columnValues, "Columns Values should in ascending order");
		utils.infoPass(columnName + " values are " + actualRowValue);

	}

	public List<String> expectedButtonValues() {
		List<String> buttonValues = new ArrayList<>();
		buttonValues.add("baz");
		buttonValues.add("qux");
		buttonValues.add("bar");
		buttonValues.add("foo");
		return buttonValues;
	}
}

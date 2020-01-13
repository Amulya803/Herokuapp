package selenium.framework.objectRepo.herokuapp;

import org.openqa.selenium.By;

public interface ChallengingDomObject {
    
	By BUTTON_FIRST = By.xpath("//a[@class='button']");
	By BUTTON_SECOND = By.xpath("//a[@class='button alert']");
	By BUTTON_THIRD = By.xpath("//a[@class='button success']");
	
	By TABLE = By.xpath("//div[@class='large-10 columns']/table");
    By TABLE_HEADERS=By.xpath("//table//th");
    By HEADER_CHALLENGING_DOM = By.xpath("//div[@id='content']//h3");
    By CHALLENGING_DOM_PARA = By.xpath("//div[@id='content']//p");
    By LABEL_ANSWER = By.id("canvas");
    By IMAGE_FORK_ME_ON_GITHUB = By.xpath("//img[contains(@alt,'Fork me on GitHub')]");
    By FOOTER_TEXT = By.id("page-footer");
    By LINKS_EDIT = By.xpath("//tbody//td[count(//table//th[text()='Action']/preceding-sibling::th)+1]/a[1]");
    By LINKS_DELETE = By.xpath("//tbody//td[count(//table//th[text()='Action']/preceding-sibling::th)+1]/a[2]");
    By XYZ = By.xpath("//div[@id='content']//script");
    By LINK_ELEMENTAL_SELENIUM = By.xpath("//a[text()='Elemental Selenium']");
    
    public static By eachColumnValue(String columnName){
		return By.xpath("//tbody//td[count(//table//th[text()='"+columnName+"']/preceding-sibling::th)+1]");
	}
    
    public static By eachHeader(int headerCount){
		return By.xpath("//table//th["+headerCount+"]");
	}
    
    public static By editLink(int i) {
    	return By.xpath("(//td/a[text()='edit'])["+i+"]");
    }
    
    public static By deleteLink(int i) {
    	return By.xpath("(//td/a[text()='delete'])["+i+"]");
    }
    
}

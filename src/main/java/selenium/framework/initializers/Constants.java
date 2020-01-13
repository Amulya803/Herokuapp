package selenium.framework.initializers;

public interface Constants {

	String[] EXPECTED_TABLE_HEADERS = { "Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action" };
	String[] EXPECTED_COLUMN_VALUES = { "Iuvaret", "Apeirian", "Adipisci", "Definiebas", "Consequuntur", "Phaedrum" };
	String CHALLENGING_DOM_PARA = "The hardest part in automated web testing is finding the best locators (e.g., ones that well named, unique, and unlikely to change). It's more often than not that the application you're testing was not built with this concept in mind. This example demonstrates that with unique IDs, a table with no helpful locators, and a canvas element.";
	String BLUE_BUTTON_RGB = "rgba(43, 166, 203, 1)";
	String RED_BUTTON_RGB = "rgba(198, 15, 19, 1)";
	String GREEN_BUTTON_RGB = "rgba(93, 164, 35, 1)";
	String BLUE_BUTTON = "Blue Button";
	String RED_BUTTON = "Red Button";
	String GREEN_BUTTON = "Green Button";
	String EDIT = "edit";
	String DELETE = "delete";
	String EDIT_URL = "https://the-internet.herokuapp.com/challenging_dom#edit";
	String DELETE_URL = "https://the-internet.herokuapp.com/challenging_dom#delete";
	String LINK_COLOR ="rgba(43, 166, 203, 1)";
	String EDIT_LINK = "Edit Link";
	String DELETE_LINK = "Delete Link";
    String GIT_HUB_URL = "https://github.com/tourdedave/the-internet";
    String ELEMENTAL_SELENIUM_URL = "http://elementalselenium.com/";
}

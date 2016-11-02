package frc;

public class Config {
	private static String[][] sidebarConfig = {
			{"H"},
			{"H", "X"},
			{"L"},
			{"L", "X"},
			{"\u0394"},
			{"\u2207"}};
	private static String fieldURL = "file:img/field.png";
	
	public static String getFieldURL() {
		return fieldURL;
	}
	
	public static String[][] getSidebarConfig() {
		return sidebarConfig;
	}
}

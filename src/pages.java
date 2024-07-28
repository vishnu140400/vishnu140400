import org.openqa.selenium.By;

public class pages {
	public static By idPaths(String fieldName) {
		return By.id(fieldName); 
	}
	public static final By calendarIcon = By.xpath("//div[@class='labelCalendarContainer']");
	public static By datePicker(String date) {
		return By.xpath("//span[text()='"+date+"']");
	}
	public static By filters(String filterType, String filter) {
		return By.xpath("//div[text()='"+filterType+"']/following-sibling::ul[1]//*[@title='"+filter+"']");
	}
	public static final By busCount = By.xpath("//span[contains(@class,'busFound')]");
	public static final By ratings = By.xpath("//span[text()='Ratings']");
	public static final By ratingsOrder = By.xpath("//i[contains(@class,'down-arrow')]");
	public static final By viewSeats = By.xpath("//ul[@class='bus-items']/div[1]//div[text()='View Seats']");
	public static By seatSelection = By.xpath("//div[text()='Lower Deck']/following-sibling::canvas");
	public static By pointsPick(String point) {
		return By.xpath("//ul[@data-value='"+point+"']/li[1]/div[1]");
	}
	public static final By proceedToBookButton = By.xpath("//button[text()='Proceed to book']");
	public static By personalDetails(String fieldName) {
		return By.xpath("//label[text()='"+fieldName+"']/input");
	}
	public static final By phoneField = By.xpath("//div[@class='phone_field']//input");
	public static final By backButton = By.xpath("//i[@title='Back']");
	public static final By closeButton = By.xpath("//span[@title='close']");
	public static final By resetButton = By.xpath("//span[text()='RESET']");
}

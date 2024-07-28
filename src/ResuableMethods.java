import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ResuableMethods extends pages{
	WebDriver driver = null;
	
	public String takeScreenshot(String screenshotName) throws IOException {
		File sourceScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String ssPath = "src\\Screenshots\\"+screenshotName+".png";
		FileUtils.copyFile(sourceScreenshot, new File(ssPath));
		return ssPath;
	}
	
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void invokeApplication() {
		driver.get("https://www.redbus.in/");
	}
	
	public WebElement findWebElement(By element) {
		WebElement el = null;
		el = driver.findElement(element);
		return el;
	}
	
	public void clickOnElement(By element) {
		driver.findElement(element).click();
	}
	
	public void enterText(By element,String value) {
		driver.findElement(element).sendKeys(value);
	}
	
	public void closeDriver() {
		driver.quit();
	}
	
	public void waitForNextAction(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void canvasClick() {
		Actions act = new Actions(driver);
		WebElement ele = findWebElement(pages.seatSelection);
		Dimension canDimension = ele.getSize();
		Double canXcenter = (double) (canDimension.getWidth()/2);
		Double canYcenter = (double) (canDimension.getHeight()/2);
		int buttonX = (int) ((canXcenter/(-3))*2);
		int buttonY = (int) ((canYcenter/(-3))*2);
		act.moveToElement(ele,buttonX,buttonY).click().perform();
	}
	
	public void scrollByAmount(int amount) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+amount+")", "");
	}
}

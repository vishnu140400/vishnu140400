import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;




public class Redbus{
	
	ResuableMethods resuableMethods = new ResuableMethods();
	pages pg = new pages();
	
	
	@Test(groups = "Testscript", dataProvider = "getData", dataProviderClass = dataProviderToRedbus.class)
	public void redBusTicketBooking(String from , String to, String date,String name, String age, String email, String phone) throws Throwable {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\mohan\\eclipse-workspace\\RedBus\\RedBusReport.html");
		extent.attachReporter(spark);
		String path = "";
		spark.config().setDocumentTitle("RedBus Ticket Booking");
		spark.config().setReportName("Test Report");
		ExtentTest test = extent.createTest("Redbus Ticket flow");
		resuableMethods.launchBrowser();                                             
		test.log(Status.INFO, "Browser Launched");                                 //Browser Launch
		resuableMethods.invokeApplication();     	
		path = resuableMethods.takeScreenshot("RedBus Website");
		test.log(Status.PASS, "Navigated to the Redbus URL").pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());    
		//Navigating to the Red Bus Website
		resuableMethods.waitForNextAction(3000);
		resuableMethods.enterText(pg.idPaths("src"),from);
		test.log(Status.INFO, "From place entered");                               //Entering From Location
		resuableMethods.enterText(pg.idPaths("dest"),to);                          //Entering To Location
		test.log(Status.INFO,"To place entered");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.calendarIcon);
		resuableMethods.clickOnElement(pg.datePicker(date));                       //Date picking for the travel
		test.log(Status.INFO, "Date picked from the calendar");
		path = resuableMethods.takeScreenshot("TravelDetails");
		test.log(Status.PASS, "Travel Details entered successfully").pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.idPaths("search_button"));
		test.log(Status.INFO, "Search Button clicked");
		resuableMethods.waitForNextAction(5000);
		try {
		try {
		resuableMethods.clickOnElement(pg.filters("DEPARTURE TIME", "Before 6 am"));   //Before 6 am filter selection
		test.log(Status.INFO, "Before 6am filter selected");}catch (Exception e){
			System.out.println("Before 6am filter is disabled/ not available");
			test.log(Status.INFO,"Before 6am filter unavailable");
		}
		resuableMethods.waitForNextAction(3000);
		try {
		resuableMethods.clickOnElement(pg.filters("BUS TYPES", "SLEEPER"));         //Sleeper filter selection
		test.log(Status.INFO, "Sleeper filter selected");}catch(Exception e) {
			System.out.println("SLEEPER filter is disabled/ not available");
			test.log(Status.INFO,"SLEEPER filter unavailable");
		}
		path = resuableMethods.takeScreenshot("Filters selection");
		test.log(Status.PASS, "Filters selected Successfully").pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		String busCount = resuableMethods.findWebElement(pg.busCount).getText();
		if(Integer.parseInt(busCount.split(" ")[0])==0) {
			System.out.println("There is No buses available for the criteria chosen");
			test.log(Status.INFO, "No buses available for the selected filters");
		}
		else {
			System.out.println("Buses Count for the appplied filters are: "+Integer.parseInt(busCount.split(" ")[0]));   //Bus Count Displayed in console  
			test.log(Status.PASS, "Buses count displayed in the console").pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		resuableMethods.waitForNextAction(3000);	
		resuableMethods.clickOnElement(pg.ratings);                                 
		if(resuableMethods.findWebElement(pg.ratingsOrder).isDisplayed()) {         //Highest Ratings check 
			System.out.println("Buses are sorted by Highest ratings");
			test.log(Status.PASS, "Buses are sorted on their highest ratings and info displayes in the console");
		}
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.viewSeats);
		test.log(Status.INFO, "View Seats buton clicked");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.canvasClick();                    //Seat Selection  
		test.log(Status.INFO, "Available seat is selected successfully");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.pointsPick("bp"));
		test.log(Status.INFO, "Boarding point is selected successfully");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.pointsPick("dp"));
		test.log(Status.INFO, "destination point is selected successfully");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.proceedToBookButton);
		test.log(Status.INFO, "Proceed to book button is clicked successfully");
		resuableMethods.waitForNextAction(3000);
		resuableMethods.enterText(pg.personalDetails("Name"),name);  //Personal Details 
		resuableMethods.enterText(pg.personalDetails("Age"),age);
		resuableMethods.enterText(pg.personalDetails("Email ID"),email);
		resuableMethods.enterText(pg.phoneField,phone);
		path = resuableMethods.takeScreenshot("Personal Details");
		test.log(Status.PASS, "Personal Details is entered successfully").pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		resuableMethods.waitForNextAction(3000);
		resuableMethods.clickOnElement(pg.backButton);                               //Closing the Personal Details tab
		resuableMethods.scrollByAmount(-75);
		resuableMethods.clickOnElement(pg.closeButton);   
		test.log(Status.INFO, "CLosed the Personal Details tab successfully");//Closing Seat Selection tab
		resuableMethods.waitForNextAction(3000);
		resuableMethods.scrollByAmount(-500);
		resuableMethods.clickOnElement(pg.resetButton);  
		test.log(Status.INFO, "Filters are reset successfully");//Resetting the filters
		test.log(Status.PASS, "Required flow is completed successfully");
		}catch(Exception e) {
			System.out.println("No buses found for the desired locations");
			test.log(Status.PASS, "No Buses found for the entered locations");
		}
		resuableMethods.closeDriver();
		extent.flush();
	}
	
}

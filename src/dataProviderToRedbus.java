import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class dataProviderToRedbus {
	
	
	@DataProvider ()
	public Object[][] getData(Method method) throws Throwable{
		switch(method.getName()) {
			case "redBusTicketBooking":
				return new Object[][] {
					{readDataFromExcel("From",1),readDataFromExcel("To",1),readDataFromExcel("Date",1),readDataFromExcel("Name",1),readDataFromExcel("Age",1),readDataFromExcel("Email ID",1),readDataFromExcel("Phone",1)},
					{readDataFromExcel("From",2),readDataFromExcel("To",2),readDataFromExcel("Date",2),readDataFromExcel("Name",2),readDataFromExcel("Age",2),readDataFromExcel("Email ID",2),readDataFromExcel("Phone",2)},
					{readDataFromExcel("From",3),readDataFromExcel("To",3),readDataFromExcel("Date",3),readDataFromExcel("Name",3),readDataFromExcel("Age",3),readDataFromExcel("Email ID",3),readDataFromExcel("Phone",3)}
				};
		}
		return null;
	}
	
	public String readDataFromExcel(String columnName, int rowNum) throws Throwable {
		FileInputStream fs = new FileInputStream("src\\Datatable\\TestData.xlsx");
		//Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row0 = sheet.getRow(0);
		XSSFRow row1 = sheet.getRow(1);
		XSSFCell cell = null;
		int columns = row1.getLastCellNum();
			for(int j=0;j<columns;j++) {
				if(row0.getCell(j).getStringCellValue().equals(columnName)) {
					cell = sheet.getRow(rowNum).getCell(j);
				}
			}
		return cell.getStringCellValue();
	}
}

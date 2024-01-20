package com.evs.vtiger.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import net.bytebuddy.utility.RandomString;

public class AllGeneric {// all generic // custom // is also known as
	public  WebDriver driver;
	// public  Mycode objMycode= new Mycode();

	/**
	 * @param Url
	 * @param browserName
	 */
	public  void launchBrowser() {
		try {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			System.out.println("Browser launched successfully");
		}catch(Exception e) {

		}
	}public  void openUrl(String url) {

		try {
			driver.get(url);
			System.out.println("the Given url :-"+url+" has opened successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("the Given url :-"+url+" hasn't opened successfully");
			throw e;

		}}

	public  void myBrowserLounch(String Url, String browserName) {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Browser launch successfully");
		driver.get(Url);

	}
	public  void selectByText(String xpath, String elementName, String selectText) {

		WebElement we = myFindElement(xpath, elementName);
		try {

			Select selectObj=new Select(we);
			selectObj.selectByVisibleText(selectText);
			///  css selector 
		}catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("documents.getElementById("+e+").click()");			
			WebElement weOption = driver.findElement(By.xpath("//option[text()='"+selectText+"']"));
			js.executeScript(" var element = document   arguments[0].click()", weOption);
		}

	} 

	public  WebElement myFindElement(String xpath, String elementname) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(By.xpath(xpath));
			System.out.println(elementname + "element found successfully");
		} catch (NoSuchElementException e) {
			WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(60));
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			webElement = driver.findElement(By.xpath(xpath));
			System.out.println(elementname + "element found successfully");
		} catch (InvalidSelectorException e) {
			e.printStackTrace();
			System.out.println(elementname + "element not found on page");

		} catch (NullPointerException e) {
			e.printStackTrace();
			webElement = driver.findElement(By.xpath(xpath));
			System.out.println(elementname + " element found on UI successfully");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(elementname + "element not found on page");
			throw e;
		}

		return webElement;
	}public void takeScreenShort() {
		SimpleDateFormat dateTimeformat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss.S");
		String dateTime = dateTimeformat.format(new Date());
		// whenever  we take screenshot then its refresh that folder then show the our screenshot in folder 
		TakesScreenshot tt = (TakesScreenshot) driver;
		File srcfile = tt.getScreenshotAs(OutputType.FILE);
		  //destination 
		String dest = "screenshot\\kundesh"+dateTime+".png";
		File filobj = new File(dest);
		// CopyAction file= new CopyAction();
		try {
			Files.copy(srcfile, filobj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  WebElement myFindElement(By locator, String elementname) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(locator);
			System.out.println(elementname + "element found successfully");
		} catch (NoSuchElementException e) {
			WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(60));
			wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			webElement = driver.findElement(locator);

		} catch (InvalidSelectorException e) {
			e.printStackTrace();
			System.out.println(elementname + " element not found on page");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementname + " element not found on page");

			throw e;
		}
		return webElement;
	}

	public  void mySendKeys(String xpath, String elementName, String inputValue) {

		WebElement we = myFindElement(xpath, elementName);

		try {
			we.sendKeys(inputValue);
			System.out.println(inputValue + " value entered in" + elementName + "textbox successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("argumens[0].value=," + inputValue + "'", we);
			System.out.println(inputValue + " value entered in " + elementName + " textbox successfully");
		} catch (StaleElementReferenceException e) {
			we = myFindElement(xpath, elementName);
			we.sendKeys(inputValue);
			System.out.println(inputValue + "value entered in" + elementName + " textbox successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed." + inputValue + " value not entered in " + elementName + " textbox");
			throw e;
		}
	}

	public  void myClick(String xpath, String elementName) {
		WebElement webElement = myFindElement(xpath, elementName);

		try {
			webElement.click();
			;
			System.out.println("Click performed on " + elementName + " successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", webElement);
			System.out.println("Click performed on " + elementName + " Successfully");
		} catch (StaleElementReferenceException e) {
			webElement = myFindElement(xpath, elementName);
			webElement.click();
			System.out.println("Click performed on " + elementName + " Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " not click successfully");
			throw e;
		}
	}

//set have not get() method because its not maintain insertion oder .
	public  void myWindowhadles(String windowTitle, String elementname) {
		try {
			Set<String> handle = driver.getWindowHandles();
			for (String string : handle) {
				driver.switchTo().window(string);
				String title = driver.getTitle();
				if (title.contains(windowTitle)) {

					break;
				}

			}
			System.out.println(elementname + "window handle successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementname + "window handle not  successfully");

		}

	}

	public  void myWindowhadleWithUrl(String url, String elementName) {
		try {
			Set<String> handle = driver.getWindowHandles();
			for (String string : handle) {
				driver.switchTo().window(string);
				String title = driver.getCurrentUrl();
				if (title.contains(url)) {

					break;
				}

			}
			System.out.println(elementName + "window handle successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + "window handle not  successfully");

		}
	}

	public  void myMouseOver(String xpath, String Elementname) {

		WebElement we = myFindElement(xpath, Elementname);
		Actions mouseOver = new Actions(driver);
		try {
			mouseOver.moveToElement(we).build().perform();
			System.out.println(Elementname + "mouseover is successfully");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(Elementname + " mouseover is not performed");
		}
	}

	public  void mySelectByValue(String value, String xpath, String Elementname) {
		WebElement we = myFindElement(xpath, Elementname);
		Select dropdown = new Select(we);
		try {
			dropdown.selectByValue(value);
			System.out.println(Elementname + " value Select successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Elementname + " value not select successfully");

		}
	}

	public  void selectByIndex(int index) {

	}

	public  void selectByValue(String value) {

	}

	public  void myJavaSriptPopupAccept() {
		try {
			driver.switchTo().alert().accept();
			System.out.println("java popup click on okay/Accept successufully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("java popup click on okay/Accept is not successufully");

			throw e;
		}
	}

	public  void myDelete(By xpath) {
		myJavaSriptPopupAccept();
	}

	public  String getRandomName() {

		RandomString random = new RandomString(10);

		String randomName = random.nextString();

		return randomName;

	}

	public  void quitBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		System.out.println("Browser closed successfully");
	}

	public  void myClear(String xpath, String elementName) {
		WebElement webObj = myFindElement(xpath, elementName);
		try {
			webObj.clear();
			System.out.println(elementName + " textbox is cleared successfully");

		} catch (ElementNotInteractableException e) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value=''", webObj);
			System.out.println(elementName + " textbox is cleared successfully");
		} catch (StaleElementReferenceException e) {
			webObj = myFindElement(xpath, elementName);
			System.out.println("we have found " + elementName + " successfully");
			webObj.clear();
			System.out.println(elementName + " textbox is cleared successfully");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(elementName + " textbox is  not cleared successfully");

			throw e;

		}
	}

	public  String myGetText(String xpath, String elementName) {
		WebElement we = myFindElement(xpath, elementName);
		String innerText = null;
		try {
			innerText = we.getText();
			System.out.println(innerText + " - innertext of " + elementName + " got successfully");
		} catch (StaleElementReferenceException e) {

			we = myFindElement(xpath, elementName);
			innerText = we.getText();
			System.out.println(innerText + " - innertext of " + elementName + " got successfully");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " innertext not found");
			throw e;

		}
		return innerText;
	}

	public  void verifyString(String actualText, String expectedText, String elementName) {
		if (actualText.equalsIgnoreCase(expectedText)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		System.out.println(elementName + "String verify successfully.");
	}

	public  void mySelectByVisibleText(String xpath, String elementName, String selectText) {

		WebElement we = myFindElement(xpath, elementName);
		try {

			Select selectObj = new Select(we);
			selectObj.selectByVisibleText(selectText);
			System.out.println(elementName + "value select successfully  ");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);

			WebElement weOption = driver.findElement(By.xpath("//option[text()='" + selectText + "']"));

			js.executeScript("arguments[0].click()", weOption);
			System.out.println(elementName + " value not selected successfully  ");

		}
	}

	public  void mySelectByIndex(By xpath, String Elementname, int indexnumber) {
		WebElement we = myFindElement(xpath, Elementname);
		try {
			Select selectobj = new Select(we);
			selectobj.selectByIndex(indexnumber);
			System.out.println(Elementname + " drop down value select by index successfully.");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("index out of bound Exception please enter current index");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public  void myContextClick(String xpath, String Elementname) {
		WebElement we = myFindElement(xpath, Elementname);
		try {
			Actions object = new Actions(driver);
			object.contextClick(we).build().perform();
			System.out.println(Elementname + " right click perform successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println(Elementname + " right click perform successfully");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Elementname + " right click not performed.");

		}

	}

	public  void myClickAndHold(String xpath, String Elementname) {
		WebElement we = myFindElement(xpath, Elementname);
		try {
			Actions obj = new Actions(driver);
			obj.clickAndHold(we).build().perform();
			System.out.println(Elementname + " click and hold  perform successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Elementname + " click and hold not performed.");

		}
	}

	public  void myRelease(String xpath, String Elementname) {
		WebElement we = myFindElement(xpath, Elementname);
		try {
			Actions obj = new Actions(driver);
			obj.release(we).build().perform();
			System.out.println(Elementname + " mouse click hold release successfully");
//		}catch(ElementNotInteractableException e) {
//			JavascriptExecutor js=(JavascriptExecutor)driver;
//			js.executeScript("arguments[0].click()", we);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Elementname + " mouse click hold release successfully");

		}
	}

	public  void myDragAndDrop(String dragXpath, String dropxpath, String Elementname) {
		WebElement we = myFindElement(dragXpath, Elementname);
		WebElement we2 = myFindElement(dropxpath, Elementname);

		try {
			Actions obj = new Actions(driver);
			obj.dragAndDrop(we, we2).build().perform();
			System.out.println(Elementname + " Drag And Drop successfully ");
//			}catch(ElementNotInteractableException e) {
//				JavascriptExecutor js=(JavascriptExecutor)driver;
//				js.executeScript("arguments[0].click()", we);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Elementname + " Drag And Drop not performed. ");
			throw e;

		}
	}

	public  void myMouseClick(String xpath, String elementName) {
		WebElement webElement = myFindElement(xpath, elementName);

		try {
			Actions aobj = new Actions(driver);
			aobj.click(webElement).build().perform();
			webElement.click();
			System.out.println("Click performed on " + elementName + " successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", webElement);
			System.out.println("Click performed on " + elementName + " Successfully");
		} catch (StaleElementReferenceException e) {
			webElement = myFindElement(xpath, elementName);
			webElement.click();
			System.out.println("Click performed on " + elementName + " Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed.");
			throw e;
		}
	}

	public  void myDoubleClick(String xpath, String elementName) {
		WebElement webElement = myFindElement(xpath, elementName);

		try {
			Actions aobj = new Actions(driver);
			aobj.doubleClick(webElement).build().perform();
			webElement.click();
			System.out.println("Click performed on " + elementName + " successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", webElement);
			System.out.println("Click performed on " + elementName + " Successfully");
		} catch (StaleElementReferenceException e) {
			webElement = myFindElement(xpath, elementName);
			webElement.click();
			System.out.println("Click performed on" + elementName + "successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("click performed not successfully");
			throw e;
		}
	}

	public  String myAlertGetText() {
		Alert alert = null;
		String getTextAlert = null;
		try {
			alert = driver.switchTo().alert();
			getTextAlert = alert.getText();
			System.out.println("Alert Massage is " + getTextAlert + " found successfully");

			// System.out.println("AlertGetText is ="+""+getTextAlert);
		} catch (NoAlertPresentException e) {
			getTextAlert = alert.getText();
			System.out.println("Alert Massage not found successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return getTextAlert;
	}

	public  void mySwitchToFrame(int indexnumber, String elementName) {
		try {
			driver.switchTo().frame(indexnumber);
			System.out.println(elementName + " Switch to iframe by index no. successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + "failed. Switch to iframe");

			throw e;
		}
	}

	public  void mySwitchToFrame(String nameOrId, String elementName) {
		try {
			driver.switchTo().frame(nameOrId);
			System.out.println(elementName + " Switch to iframe by Name or Id successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + "failed. Switch to iframe");

			throw e;

		}
	}// if we want to use getfirtSelected

	public  void mySwitchToFrameByWebElement(String framexpath, String elementName) {
		WebElement we = myFindElement(framexpath, elementName);
		driver.switchTo().frame(we);
	}

	public  String myGetFirstSelectedOption(String xpath, String elementName) {
		WebElement we = myFindElement(xpath, elementName);
		Select objs = new Select(we);
		WebElement we2 = objs.getFirstSelectedOption();
		String selectText = we2.getText();
		return selectText;
	}// jiske pas apna tag hota h wah element hote h
		// dropdown kitana value h use check karne ke liye getOption ka use h karte
		// return type List<WebElement>
		// jis method ka name get hota hai usase kuchh prapt kiya jata h kuchha

	public  int myGetAllOptioncount(String xpath, String elementName) {
		WebElement we = myFindElement(xpath, elementName);
		Select selobj = new Select(we);
		List<WebElement> listweb = selobj.getOptions();
		int value = listweb.size();
		return value;
	}

	public  List<String> myTextOfAllOption(String xpath, String elementName) {
		List<String> listOptionText = new ArrayList<String>();
		WebElement we = myFindElement(xpath, elementName);

		try {
			Select selobj = new Select(we);
			List<WebElement> weoptionoflist = selobj.getAllSelectedOptions();
			for (int i = 0; i < weoptionoflist.size() - 1; i++) {
				WebElement weoption = weoptionoflist.get(i);
				String optionText = weoption.getText();
				listOptionText.add(optionText);
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		return listOptionText;
	}

	public  void mySelectByTextContains(String xpath, String elementName, String selectText) {
		int indexNuber = -1;
		WebElement we = myFindElement(xpath, elementName);
		Select selobj = new Select(we);
		List<WebElement> listobj = selobj.getOptions();
		for (int i = 0; i < listobj.size() - 1; i++) {
			WebElement webOptionText = listobj.get(i);
			String OptionText = webOptionText.getText();
			boolean status = OptionText.contains(selectText);
			if (status == true) {
				indexNuber = i;
				break;
			}
		}
		selobj.selectByIndex(indexNuber);
	}

	public  void MyCleckAllCheckBox(String xpath, String elementCollectionName) {
		try {
			List<WebElement> welistclick = driver.findElements(By.xpath(xpath));
			for (int i = 0; i <= welistclick.size() - 1; i++) {
				WebElement wecheckbox = welistclick.get(i);
				if (wecheckbox.isSelected() == false) {
					wecheckbox.click();
				}
			}
			System.out.println(elementCollectionName + "check successfullt");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementCollectionName + " is   uncheck");
			throw e;
		}
	}

	public  void MyUnCleckAllCheckBox(String xpath, String elementCollectionName) {
		try {
			List<WebElement> welistclick = driver.findElements(By.xpath(xpath));
			for (int i = 0; i <= welistclick.size() - 1; i++) {
				WebElement wecheckbox = welistclick.get(i);
				if (wecheckbox.isSelected() == true) {
					wecheckbox.click();
				}
			}
			System.out.println(elementCollectionName + "  uncheck  successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementCollectionName + "uncheck is not successfully ");
		}
	}

	//public  void getAllSheetRowCount(String xpath,String elementName, ) {
		
		
		//int rowCount = getTableRowCount(xpath, elementName);
		//String sheetNo=driver.findElement(By.xpath("//span[@name='Leads_listViewCountContainerName'])[1]")).getText();
		
	
		
		
	//}
	
	public  List<WebElement> myFindElements(String xpath, String elementName) {
		List<WebElement> we = driver.findElements(By.xpath(xpath));
		try {
			driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " has not work");
			throw e;
		}
		return we; ////// table[@class='lvt small']//td[@class='lvtCol']
	}

	public  int getTableCOuntColumn(String xpath, String elementName) { // column
		List<WebElement> welist = myFindElements(xpath, elementName);
		int columnsize = welist.size();
		return columnsize;
	}

	public  int getTableCountRow(String xpath, String elementName) { // column
		List<WebElement> welist = myFindElements(xpath, elementName);
		int rowsize = welist.size();
		return rowsize;
	}

	public  List<String> getTableColunmNameList(String xpath, String elementName) {

		List<String> colunmTextList = new ArrayList<>();
		List<WebElement> tableColunmNameList = myFindElements(xpath, elementName);
		for (int i = 0; i <= tableColunmNameList.size() - 1; i++) {
			WebElement Wetable = tableColunmNameList.get(i);
			String tableElementName = Wetable.getText();
			colunmTextList.add(tableElementName);
		}

		return colunmTextList;
	}

	/*this method use for row count not consider header row*/
	public  int getTableRowCount(String tableXpath, String elementName) {
		
		List<WebElement> weRowsList=driver.findElements(By.xpath(tableXpath+"//tr"));
		int rowCount=weRowsList.size()-1;
		return rowCount;
	}

	public  int getTableHeaderColumnCount(String tableXpath, String elementName) {
		List<WebElement> weListColumns = driver.findElements(By.xpath(tableXpath + "//tr[1]//td"));
		int columnCount = weListColumns.size();
		return columnCount;
	}

	/* in this method we want all the column header names in a list<String> */
	public  List<String> getTableColumnNamesList(String tableXpath, String elementName) {
		List<WebElement> weListColumns = driver.findElements(By.xpath(tableXpath + "//tr[1]//td"));
		List<String> listColumnNames = new ArrayList<String>();
		int columnCount = weListColumns.size();
		for (int i = 0; i <= columnCount - 1; i++) {
			WebElement weColumnHeader = weListColumns.get(i);
			String columnName = weColumnHeader.getText();
			listColumnNames.add(columnName);
		}
		return listColumnNames;
	}

	/* this method returns column number on the basis of column name */
	
						////table[@class='ldhggf'],"account column no. by column name","Last Name"
	public  int getColumnNumberByColumnName(String tableXpath, String tableName, String columnName) {
		int columnNumber = 0;
		List<WebElement> listColumnNames = driver.findElements(By.xpath(tableXpath + "//tr[1]//td"));
		int columnCount = listColumnNames.size();
		for (int i = 1; i <= columnCount - 1; i++) {
			WebElement weTableColumn = listColumnNames.get(i);
			String tablColumnName = weTableColumn.getText();
			if (tablColumnName.equalsIgnoreCase(columnName) ) {
				columnNumber = i;
				break;
			}
		}

		return columnNumber;
	}

	/* this method returns row data in list on the basis of row number */
	public  List<String> getRowDataListByRowNumber(String tableXpath, String tableName, int rowNumber) {
		List<WebElement> weListRowData = driver
				.findElements(By.xpath(tableXpath + "//tr[" + (rowNumber + 1) + "]//td"));
		List<String> rowDataList = new ArrayList<String>();
		for (int i = 0; i <= weListRowData.size() - 1; i++) {
			WebElement weRowData = weListRowData.get(i);
			String data = weRowData.getText();
			rowDataList.add(data);
		}
		return rowDataList;
	}

	public  List<String> getColumnDataListByColumnNumber(String tableXpath, int index) { // Integer.parseInt(Stringvalueconvertin
																								// int)
		List<WebElement> weListColumnData = driver.findElements(By.xpath(tableXpath + "//tr//td[" + index + "]"));
		List<String> listAllColumnData = new ArrayList<String>();
		for (WebElement listColumnData : weListColumnData) {
			String columnData = listColumnData.getText();
			listAllColumnData.add(columnData);
		}
		return listAllColumnData;
	}

	public  List<String> getColumnDataListByColumnName(String tableXpath,String columeName,String tableName) {

		int columeNo=getColumnNumberByColumnName(tableXpath, tableXpath, tableXpath);
		List<String> ListAllColumnDataName= getColumnDataListByColumnNumber(tableXpath, columeNo);
		return ListAllColumnDataName;
		

		}
	//(//table[@class='lvt small']//tr//td)[53]
// pratice
	public  int getRowNumberByRowId(String tablexpath ,String tablename ,String RowId) {
		
	int rownumber=0;
		List<WebElement> we = driver.findElements(By.xpath(tablexpath));
		int rowcount=we.size();
		for(int i=0;i<rowcount;i++) {
		WebElement weList=	we.get(i);
	String rowIdList=	weList.getText();
	System.out.println("start"+rowIdList+"all list end");
	if(rowIdList.equalsIgnoreCase(RowId)==true) {
		rownumber= i;
	}
		}
		return rownumber;
	} 
	
	public  void getRowDataListByRowId() {

	}

	public  void printAllTableData() {

	}

}
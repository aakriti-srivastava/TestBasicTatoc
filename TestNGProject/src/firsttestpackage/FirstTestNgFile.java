package firsttestpackage;
import java.io.File;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;



public class FirstTestNgFile {
  
	public String baseurl = "http://10.0.1.86";
	public String nexturl = " http://10.0.1.86/tatoc/basic/grid/gate";
	public String dungeonurl = "http://10.0.1.86/tatoc/basic/frame/dungeon";
	public String dragurl = " http://10.0.1.86/tatoc/basic/drag" ;
	File binaryPath = new File("/home/aakritisrivastava/Desktop/firefox (2)/firefox");
	FirefoxBinary ffbinary = new FirefoxBinary(binaryPath);
	FirefoxProfile ffProfile = new FirefoxProfile();
	public static WebDriver driver2 = null ;
	 
	
	@BeforeTest
	public void setbaseurl(){
	driver2 = new FirefoxDriver(ffbinary, ffProfile);
		 }
	
	
	
	
 @Test(priority=0)
  public void verifyHomepageTitle() {
  driver2.get(baseurl);
	String expectedTitle       = "TAP Utility Server";
	String actualTitle = driver2.getTitle();
	System.out.println(actualTitle);
	System.out.println(expectedTitle);
	Assert.assertEquals(actualTitle, expectedTitle);
	
	}
 
 
 
 
 @Test(priority=1)
 public void greencolorboxdisplayed()
 {
 	Boolean actual , expected ;
 	expected = true ;
 	driver2.get(nexturl);
 	WebElement box1 = driver2.findElement(By.cssSelector(".greenbox"));
 	actual = box1.isDisplayed();
 	Assert.assertEquals(actual, expected);
 	
 }
 
 
 
 
 
 @Test(priority=2)
 public void redcolorboxdisplayed()
 {
 	Boolean actual , expected ;
 	expected = true ;
 	driver2.get(nexturl);
 	WebElement box1 = driver2.findElement(By.cssSelector(".redbox"));
 	actual = box1.isDisplayed();
 	Assert.assertEquals(actual, expected);
 	
 }
 
 
 

 @Test(priority=3)
 public void  greenColorGridproceed()
 {
	 driver2.get(nexturl);
	 greenbox(driver2);
	 String expectedTitle       = "Frame Dungeon - Basic Course - T.A.T.O.C";
		String actualTitle = driver2.getTitle();
		System.out.println(actualTitle);
		System.out.println(expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		
 }
 
 
 void greenbox(WebDriver driver2 )
 {
 	//green box
 	
 	WebElement element = driver2.findElement(By.cssSelector(".greenbox"));
 	element.click();
 	

 }
      	 
	 


@Test(priority=4)
public void RedColorGridproceed()
 {
	    driver2.get(nexturl);
	    WebElement element = driver2.findElement(By.cssSelector(".redbox"));
		element.click(); 
		String expectedTitle       = "Error - T.A.T.O.C";
		String actualTitle = driver2.getTitle();
		System.out.println(actualTitle);
		System.out.println(expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
	
	 
 }
	
// For Frame Box Dungeon 

//@Test(groups = { "frame" })

@Test(priority = 5)
public void verifycolorboxdisplayed()
{
	Boolean actual , expected ;
	expected = true ;
	driver2.get(dungeonurl);
	driver2.switchTo().frame("main");
	WebElement box1 = driver2.findElement(By.cssSelector("#answer"));
	actual = box1.isDisplayed();
	Assert.assertEquals(actual, expected);
	
}



//@Test(groups = { "frame" })
@Test(priority = 6)
public void verifywhiteboxdisplayed()
{
	Boolean actual , expected ;
	expected = true ;
	driver2.get(dungeonurl);
	driver2.switchTo().frame("main");
	driver2.switchTo().frame("child");
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	WebElement box1 = driver2.findElement(By.cssSelector("#answer"));
	actual = box1.isDisplayed();
	Assert.assertEquals(actual, expected);
	
	
}

//@Test(dependsOnGroups = { "frame.* " })
@Test(priority = 7)
public void verifyrepaintworks()
{
	            driver2.get(dungeonurl);
				driver2.switchTo().frame("main");
				WebElement box1 = driver2.findElement(By.cssSelector("#answer"));
				String color = box1.getAttribute("class");
				String color2 ; 
				List<WebElement> links=driver2.findElements(By.cssSelector("html>body>center>a"));
				
					links.get(0).click();
					driver2.switchTo().frame("child");
					WebElement box2 = driver2.findElement(By.cssSelector("#answer"));
					color2 =  box2.getAttribute("class");
					System.out.println(color2);
					String color1 = "white";
					Assert.assertNotEquals(color1, color2, "repaint works");
					driver2.switchTo().defaultContent();
					driver2.switchTo().frame("main");
				
}


@Test(priority=8)
public void verifyframeproceedwithoutsamecolor()
{ 
	
	
	 driver2.get(dungeonurl);
		driver2.switchTo().frame("main");
	    
		List<WebElement> links=driver2.findElements(By.cssSelector("html>body>center>a"));
		links.get(1).click();
		String actualurl = "http://10.0.1.86/tatoc/error";
		driver2.getCurrentUrl();
		Assert.assertEquals(actualurl,driver2.getCurrentUrl() , " proceed works");
			

}

@Test(priority=9)
public void verifyframeproceedwithsamecolor()
{ 
	
	
	
	  driver2.get(dungeonurl);
		driver2.switchTo().frame("main");
		WebElement box1 = driver2.findElement(By.cssSelector("#answer"));
		String color = box1.getAttribute("class");
		String color2 ; 
		List<WebElement> links=driver2.findElements(By.cssSelector("html>body>center>a"));
		do{
			links.get(0).click();
			driver2.switchTo().frame("child");
			WebElement box2 = driver2.findElement(By.cssSelector("#answer"));
			color2 =  box2.getAttribute("class");
			driver2.switchTo().defaultContent();
			driver2.switchTo().frame("main");
		} while (!color.equals(color2));
		links=driver2.findElements(By.cssSelector("html>body>center>a"));
		links.get(1).click();
		String actualurl = "http://10.0.1.86/tatoc/basic/drag";
		driver2.getCurrentUrl();
		Assert.assertEquals(actualurl,driver2.getCurrentUrl() , " proceed works");
        			

}

//Draggable element present or not 

@Test(priority=10)
public void verifydraggabledisplayed()
{ 
	
   
   	Boolean actual , expected ;
 	expected = true ;
 	driver2.get(dragurl);
 	WebElement dragbox = driver2.findElement(By.cssSelector("#dragbox"));
 	actual = dragbox.isDisplayed();
 	Assert.assertEquals(actual, expected);
	
}

@Test(priority=11)
public void verifydropboxdisplayed()
{ 
	
   
   	Boolean actual , expected ;
 	expected = true ;
 	driver2.get(dragurl);
 	WebElement dropbox = driver2.findElement(By.cssSelector("#dropbox"));
 	actual = dropbox.isDisplayed();
 	Assert.assertEquals(actual, expected);
	
}


@Test(priority = 12)
public void verifydragging()
{

	WebElement dragobj = driver2.findElement(By.cssSelector("#dragbox"));
	WebElement dropobj = driver2.findElement(By.cssSelector("#dropbox"));
	Point obj= driver2.findElement(By.cssSelector("#dragbox")).getLocation();
    System.out.println("dragbox"+obj);
	int xdragbox = obj.getX();
	int ydragbox = obj.getY();
	
	Point obj1= driver2.findElement(By.cssSelector("#dropbox")).getLocation();
	System.out.println("dropbox"+obj1);
	int xdropbox = obj1.getX();
	int ydropbox = obj1.getY();

	
	//System.out.println( driver2.findElement(By.cssSelector("#dropbox")).getLocation());
	(new Actions(driver2)).dragAndDrop(dragobj, dropobj).perform();
	Point newobj= driver2.findElement(By.cssSelector("#dragbox")).getLocation();
	
	int nxdragbox = newobj.getX();
	int nydragbox = newobj.getY();
	
String cond = null ; 
	if (nydragbox < ydragbox && nxdragbox> xdragbox)
	{
		System.out.println("dragged and dropped ");
		cond = "true" ; 
	}
	
	Assert.assertEquals(cond,"true");
	/*WebElement proceed = driver2.findElement(By.cssSelector(".page>a"));
	proceed.click();*/
	
}


@Test(priority = 13)
public void verifyproceedafterdrag()
{
   driver2.get(dragurl);
	WebElement dragobj = driver2.findElement(By.cssSelector("#dragbox"));
	WebElement dropobj = driver2.findElement(By.cssSelector("#dropbox"));
	(new Actions(driver2)).dragAndDrop(dragobj, dropobj).perform();
	
	WebElement proceed = driver2.findElement(By.cssSelector(".page>a"));
	proceed.click();
	
	String expectedTitle       = "Windows - Basic Course - T.A.T.O.C";
	String actualTitle = driver2.getTitle();
	System.out.println(actualTitle);
	System.out.println(expectedTitle);
	Assert.assertEquals(actualTitle, expectedTitle);
	
}


@Test(priority = 14)
public void verifyproceedwithoutdrag()
{
   driver2.get(dragurl);
	WebElement dragobj = driver2.findElement(By.cssSelector("#dragbox"));
	WebElement dropobj = driver2.findElement(By.cssSelector("#dropbox"));
	//(new Actions(driver2)).dragAndDrop(dragobj, dropobj).perform();
	
	WebElement proceed = driver2.findElement(By.cssSelector(".page>a"));
	proceed.click();
	
	String expectedTitle       = "Error - T.A.T.O.C";
	String actualTitle = driver2.getTitle();
	System.out.println(actualTitle);
	System.out.println(expectedTitle);
	Assert.assertEquals(actualTitle, expectedTitle);
	
}



@Test(priority = 15)
public void verifypopuplaunched()
{
       
		// Launching POp Up Windows 
		List<WebElement> poplinks=driver2.findElements(By.cssSelector(".page>a"));	
		poplinks.get(0).click();
		try {
    	Thread.sleep(2000);
    	} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    }
		
        System.out.println("handle " + driver2.getWindowHandle());
        Assert.assertNotNull(driver2.getWindowHandle());

 
}
	

@Test(priority = 16)
public void popuptextboxdisplayed()
{
	driver2.get("http://10.0.1.86/tatoc/basic/windows/popup");		
			WebElement  textbox = driver2.findElement(By.cssSelector("#name"));
					
					 Boolean actual = textbox.isDisplayed();
					 Boolean expected = true ; 
						Assert.assertEquals(actual, expected);

 
}


@Test(priority = 17)
public void popupsubmitdisplayed()
{
	driver2.get("http://10.0.1.86/tatoc/basic/windows/popup");		
			WebElement  textbox = driver2.findElement(By.cssSelector("#submit"));
					
					 Boolean actual = textbox.isDisplayed();
					 Boolean expected = true ; 
						Assert.assertEquals(actual, expected);

 
}


@Test(priority = 18)
public void popupsubmitwithtext()
{
	driver2.get("http://10.0.1.86/tatoc/basic/windows");
	List<WebElement> poplinks=driver2.findElements(By.cssSelector(".page>a"));	
	poplinks.get(0).click();
	try {
	Thread.sleep(2000);
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	Object obj = null;

	
	 
	for (String handle : driver2.getWindowHandles()) {
	obj = driver2.getWindowHandles() ; 
    driver2.switchTo().window(handle);
    System.out.println(handle);
	}

	WebElement name = driver2.findElement(By.cssSelector("#name"));
	name.sendKeys("Aakriti");
	WebElement submit = driver2.findElement(By.cssSelector("#submit"));
	submit.click();
	for (String handle : driver2.getWindowHandles()) {
		driver2.switchTo().window(handle);
	}
	poplinks=driver2.findElements(By.cssSelector(".page>a"));
	poplinks.get(1).click();	
	
	for (String handle2 : driver2.getWindowHandles()) {
		driver2.switchTo().window(handle2);
	}
	
	 Assert.assertNotEquals(obj,driver2.getWindowHandle());
}


@Test(priority = 19)
public void popupsubmitwithouttext()
{
	driver2.get("http://10.0.1.86/tatoc/basic/windows");
	List<WebElement> poplinks=driver2.findElements(By.cssSelector(".page>a"));	
	poplinks.get(0).click();
	try {
	Thread.sleep(2000);
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	Object obj = null;

	
	 
	for (String handle : driver2.getWindowHandles()) {
	obj = driver2.getWindowHandles() ; 
    driver2.switchTo().window(handle);
    System.out.println(handle);
	}

	WebElement name = driver2.findElement(By.cssSelector("#name"));
	//name.sendKeys("Aakriti");
	WebElement submit = driver2.findElement(By.cssSelector("#submit"));
	submit.click();
	for (String handle : driver2.getWindowHandles()) {
		driver2.switchTo().window(handle);
	}
	poplinks=driver2.findElements(By.cssSelector(".page>a"));
	poplinks.get(1).click();	
	
	for (String handle2 : driver2.getWindowHandles()) {
		driver2.switchTo().window(handle2);
	}
	
	 Assert.assertNotEquals(obj,driver2.getWindowHandle());
	
	
}

@AfterTest
public void closing()
{
	driver2.quit();
	
}


}


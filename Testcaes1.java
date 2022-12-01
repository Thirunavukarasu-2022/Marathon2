package marathon2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Testcaes1 {
	
	/*Testcase:1
	Architect Certifications
	==========================
	1. Launch Salesforce application https://login.salesforce.com/
	2. Login with Provided Credentials
	3. Click on Learn More link in Mobile Publisher  and click Confirm
	4. Click Learning and Mouse hover on Learning On Trailhead
	5. Select SalesForce Certification 
	6. Choose Your Role as Salesforce Architect and verify the URL
	7. Get the Text(Summary) of Salesforce Architect 
	8. Get the List of Salesforce Architect Certifications Available
	9. Click on Application Architect 
	10.Get the List of Certifications available
	Credentials:
	hari.radhakrishnan@qeagle.com
	Testleaf$321*/
	
	public static void main(String[] args) throws InterruptedException {
		//Launch Salesforce application https://login.salesforce.com/
		WebDriverManager.edgedriver().setup();
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Login with Provided Credentials
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.id("password")).sendKeys("Testleaf$321");
		driver.findElement(By.xpath("//input[@class='button r4 wide primary']")).click();
		//Click on Learn More link in Mobile Publisher  and click Confirm
		driver.findElement(By.xpath("//button[contains(@title,'Learn More')]")).click();
		Thread.sleep(1000);
		//Move to second window
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> confirmWindow = new ArrayList<String>(windowhandles);
		//give permission to new window
		driver.switchTo().window(confirmWindow.get(1));
		driver.findElement(By.xpath("//button[@onclick='goAhead()']")).click();
		//Handle Shadow DOM page
		Shadow dom = new Shadow(driver);
		dom.setImplicitWait(10);
		//Inspect in shadow DOM page
		dom.findElementByXPath("//span[text()='Learning']").click();
		//Mouse hover on Learning On Trailhead
		WebElement mousehover = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder = new Actions(driver);
		builder.moveToElement(mousehover).perform();
		//Select SalesForce Certification
		dom.findElementByXPath("//a[text()='Salesforce Certification']").click();
		String adminstrativelink = driver.getCurrentUrl();
		System.out.println(adminstrativelink);
		
		//Choose Your Role as Salesforce Architect and 
		driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();
		//verify the URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		if(currentUrl.contains("architectoverview")){
			System.out.println("architectoverview Page loaded sucessfully");
		}
		else {
			System.out.println("architectoverview Page loaded not sucessfully");
		}
		//Get the Text(Summary) of Salesforce Architect
		String salesforceArchitectSummary = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']")).getText();
		System.out.println(salesforceArchitectSummary);
		
		//Get the List of Salesforce Architect Certifications Available
		 List<WebElement> certificationAvailable = driver.findElements(By.className("credentials-card_title"));
		 for (WebElement listOfCertification : certificationAvailable) {
			 String listOfCertification1 = listOfCertification.getText();
			 System.out.println(listOfCertification1);			
		}
		 //Click on Application Architect
		 driver.findElement(By.linkText("Application Architect")).click();
		 String applicationArchitect = driver.getCurrentUrl();
		 System.out.println(applicationArchitect);		 
		 //Get the List of Certifications available
		 List<WebElement> applicationArchitect1 = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		 for (WebElement listOfCertification1 : applicationArchitect1) {
			 String applicationArchitectcertification = listOfCertification1.getText();
			 System.out.println(applicationArchitectcertification);
			
		}
	    
	
	}

}

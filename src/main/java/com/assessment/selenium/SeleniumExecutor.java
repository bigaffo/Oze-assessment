package com.assessment.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
public class SeleniumExecutor implements Executor {

  private final WebDriver driver;

  public SeleniumExecutor(WebDriver driver) {
    this.driver = driver;
  }

  /// Page 1
  @Override
  public void SetLoginAndClickNext(String login){
	driver.findElement(By.id("emailBox")).sendKeys(login);
	driver.findElement(By.linkText("Next")).click();
  }

  /// Page 2
  @Override
  public String OpenCodePageAndReturnCode(){
	 
	/String winHandleBefore = driver.getWindowHandle();
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='open page']")));
	driver.findElement(By.xpath("//a[text()='open page']")).click();
	for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
	}

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("code")));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; 
	String authcode = (String) jsExecutor.executeScript("return document.getElementById('code').value");
	
	 System.out.println(authcode);
	 driver.switchTo().window(winHandleBefore); 
	
	return authcode;
	   
  }

  @Override
  public void SetCodeAndClickNext(String code){
	  WebDriverWait wait = new WebDriverWait(driver,30);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("codeBox")));
	  driver.findElement(By.id("codeBox")).sendKeys(code);
	  driver.findElement(By.linkText("Next")).click();
  }

  /// Page 3
  @Override
  public void FillMaskedPasswordAndClickLogin(String password){
	  String[] split = password.split("(?!^)");
		driver.switchTo().frame(0);
		if(driver.findElement(By.id("passwd_0")).isEnabled()) {
			driver.findElement(By.id("passwd_0")).sendKeys(split[0]);
		}
		if(driver.findElement(By.id("passwd_1")).isEnabled()) {
			driver.findElement(By.id("passwd_1")).sendKeys(split[1]);
		}
		if(driver.findElement(By.id("passwd_2")).isEnabled()) {
			driver.findElement(By.id("passwd_2")).sendKeys(split[2]);
		}
		if(driver.findElement(By.id("passwd_3")).isEnabled()) {
			driver.findElement(By.id("passwd_3")).sendKeys(split[3]);
		}
		if(driver.findElement(By.id("passwd_4")).isEnabled()) {
			driver.findElement(By.id("passwd_4")).sendKeys(split[4]);
		}
		if(driver.findElement(By.id("passwd_5")).isEnabled()) {
			driver.findElement(By.id("passwd_5")).sendKeys(split[5]);
		}
		if(driver.findElement(By.id("passwd_6")).isEnabled()) {
			driver.findElement(By.id("passwd_6")).sendKeys(split[6]);
		}
		if(driver.findElement(By.id("passwd_7")).isEnabled()) {
			driver.findElement(By.id("passwd_7")).sendKeys(split[7]);
		}
		if(driver.findElement(By.id("passwd_8")).isEnabled()) {
			driver.findElement(By.id("passwd_8")).sendKeys(split[8]);
		}
		if(driver.findElement(By.id("passwd_9")).isEnabled()) {
			driver.findElement(By.id("passwd_9")).sendKeys(split[9]);
		}
		driver.findElement(By.xpath("//button[@class='buttonLogin' and @type='button']")).click();
	}	

  }

  @Override
  public String GetLoggedInText(){
    String text = driver.findElement(By.id("loggedIn")).getText();
    return text;
  }

}

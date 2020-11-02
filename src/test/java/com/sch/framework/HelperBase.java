package com.sch.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {
    AppiumDriver driver;


    public HelperBase(AppiumDriver driver) {
        this.driver = driver;
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void closeKeyboard() {
        driver.hideKeyboard();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void waitForElementAndClick(By locator, int timeOut){
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    }

    public void waitForElementAndType(By locator, int timeOut, String text){
        if(text != null) {
            waitForElementAndClick(locator, timeOut);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }
    public WebElement waitForElement(By locator, int timeOut){
        return new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void swipeToLeft(By locator) {
        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();

        int leftPoint = (int) (size.width* 0.8);
        int rightPoint  = (int) (size.width* 0.8);

        WebElement element = waitForElement(locator, 10);

        int leftX = (int) (element.getLocation().getX() * 0.2);
        int rightX = (int) ((leftX + element.getSize().getWidth()) * 0.8);
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action.longPress(PointOption.point(rightPoint, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))

                .moveTo(PointOption.point(leftPoint, middleY))
                .release().perform();
    }

    public void moveElementToLeft(By locator){
        TouchAction action = new TouchAction(driver);
        //get activity points
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width* 0.8);
        int rightPoint  = (int) (size.width* 0.8);

        //get element's point
        WebElement element = driver.findElement(locator);

        int leftX = (int) (element.getLocation().getX()* 0.2);
        int rightX = (int) ((leftX + element.getSize().getWidth())* 0.8);
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action.longPress(PointOption.point(rightPoint, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))

                .moveTo(PointOption.point(leftPoint, middleY))
                .release().perform();
    }


}

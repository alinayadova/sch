package com.sch.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class EventHelper extends HelperBase {
    public EventHelper(AppiumDriver driver) {
        super(driver);
    }

    /*tap on Plus button
     * tapOnPencil
     * fillEventCreationForm
     * TapOnAddEventButton*/

    public void clickOnPlusButton() {
        click(By.id("fab_main"));

    }

    public void fillEventCreationForm(String title, String type, int breaks, String wage) {
        //swipeLeftSelectPastDate
        //swipeRightSelectFutureDate

        type(By.id("info_title_input"), title);
        type(By.id("info_type_input"), type);
        // closeKeyboard();
        if (breaks > 0) {
            for (int i = 0; i < breaks; i++) {
                clickOnAddBreakButton();
            }
        }
        addWage(wage);
    }

    private void addWage(String wage) {
        click(By.id("info_wage_edit"));
        type(By.id("info_wage_input"), wage);
        click(By.id("info_wage_save"));

    }


    public void clickOnAddBreakButton() {
        click(By.id("info_break_plus_btn"));
    }

    public void clickOnAddEventButton() {
        click(By.id("info_save_btn"));

    }

    public void clickOnPencil() {
        click(By.id("fab_add_event"));
    }

    public void swipeCalendarToLeft() {
        moveElementToLeft(By.id("date_container_layout"));
    }

    public boolean isEventPresent() {
        waitForElement(By.id("row_container_main"), 20);
        return isElementPresent(By.id("row_container_main"));
    }


    public int getEventCountByMonth(String month) {
        //get displayedMonth
        //if  displayedMonth != month, click on arrow
        String displayedMonth = driver.findElement(By.id("nav_month_name")).getText();
        while (!displayedMonth.equals(month.toUpperCase())) {
            click(By.id("nav_next_img"));
            displayedMonth = driver.findElement(By.id("nav_month_name")).getText();

        }
        return driver.findElements(By.id("row_container_main")).size();
    }


    public void selectDate(String type, String month, String dayOfMonth) {
        if (!getSelectedMonth().equals(month)) {
            if (type.equals("past")) {
                swipeDateToRightUntilNeededMonth(month);

                if(! getSelectedDayOfMonth().equals(dayOfMonth)){
                    swipeDateToRightUntilNeededDayOfMonth(dayOfMonth);
                }
//                swipeToRightUntilNeededDay(dayOfMonth);
            } else if (type.equals("future")) {
                swipeDateToLeftUntilNeededMonth(month);
                if(! getSelectedDayOfMonth().equals(dayOfMonth)){
                    swipeDateToLeftUntilNeededDayOfMonth(dayOfMonth);
                } //swipeToLeftUntilNeededDay(dayOfMonth);
            }

        } else if(! getSelectedDayOfMonth().equals(dayOfMonth)){
            if (type.equals("past")) {
                swipeDateToRightUntilNeededDayOfMonth(dayOfMonth);
            }else if(type.equals("future")){
                swipeDateToLeftUntilNeededDayOfMonth(dayOfMonth);
            }
        }
    }

    public void swipeDateToLeftUntilNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    public void swipeDateToRightUntilNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    public void swipeDateToRightUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }

    public void moveElementToRight(By locator) {
        TouchAction action = new TouchAction(driver);
        //get activity points
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width* 0.2);
        int rightPoint = (int) (size.width* 0.5);

//get Element's point
        WebElement element = driver.findElement(locator);

        int leftX = (int) (element.getLocation().getX()* 0.2);
        int rightX = (int) ((leftX + element.getSize().getWidth())* 0.8);
        int upperY = element.getLocation().getY() ;
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .longPress(PointOption.point(leftPoint, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))

                .moveTo(PointOption.point(rightPoint, middleY))
                .release()
                .perform();
    }

    public void swipeDateToLeftUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedMonth();
        }

    }

    public String getSelectedMonth() {
        WebElement selectedDate = driver.findElement(By.id("date_container_layout"));
        return selectedDate.findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_month_txt']"))
                .getText();
    }

    public String getSelectedDayOfMonth() {
        WebElement selectedDate = driver.findElement(By.id("date_container_layout"));
        return selectedDate.findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']"))
                .getText();
    }
}


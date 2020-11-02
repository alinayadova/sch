package com.sch.framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        closeKeyboard();
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
        click(By.id("info_wage_add"));

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
    public void swipeCalendarToLeft(){
        moveElementToLeft(By.id("date_container_layot"));
    }

    // public void swipeCalendarToLeft(){
    //   moveElementToLeft(By.id("date_container_layot"));
    //}

    public boolean isEventPresent(){
        WebElement event =  waitForElement(By.id("row_container_main"), 20);
        return isElementPresent(By.id("row_container_main"));
    }


    public int getEventCountByMonth(String month) {
        //get displayedMonth
        //if  displayedMonth != month, click on arrow
        String displayedMonth = driver.findElement(By.id("nav_month_name")).getText();
        while(!displayedMonth.equals(month.toUpperCase())){
            click(By.id("nav_next_img"));
            displayedMonth = driver.findElement(By.id("nav_month_name")).getText();

        }

        return driver.findElements(By.id("row_container_main")).size();
    }
}
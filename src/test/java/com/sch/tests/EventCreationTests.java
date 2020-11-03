package com.sch.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if(app.getUserHelper().isLoginButtonPresent()){
            app.getUserHelper().defaultLogin();
        }
    }

    @Test
    public void testEventCreation(){
        app.getEventHelper().clickOnPlusButton();
        app.getEventHelper().clickOnPencil();
        app.getEventHelper().fillEventCreationForm("ala", "1", 3, "500");
        app.getEventHelper().clickOnAddEventButton();
    }

    @Test
    public void testEventCreationChangeDate(){
        //int before = app.getEventHelper().getEventCountByMonth("November");
        app.getEventHelper().clickOnPlusButton();
        app.getEventHelper().clickOnPencil();
        app.getEventHelper().swipeCalendarToLeft();
        app.getEventHelper().selectDate("future","NOVEMBER", "10");

        app.getEventHelper().fillEventCreationForm("aаjjh", "1", 3, "50");
        app.getEventHelper().clickOnAddEventButton();

        Assert.assertTrue(app.getEventHelper().isEventPresent());
        int after = app.getEventHelper().getEventCountByMonth("November");

        //   Assert.assertEquals(after, before+1 );


    }


}

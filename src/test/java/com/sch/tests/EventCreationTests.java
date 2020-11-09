package com.sch.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (app.getUserHelper().isLoginButtonPresent()) {
            app.getUserHelper().defaultLogin();
        }
    }

        @Test
        public void testEventCreation(){
            /*
             * tap on Plus button
             * tapOnPencil
             * fillEventCreationForm
             * TapOnAddEventButton
             * */
            app.getEventHelper().clickOnPlusButton();
            app.getEventHelper().clickOnPencil();

            app.getEventHelper().fillEventCreationForm("ahj", "1", 3, "150");
            app.getEventHelper().clickOnAddEventButton();

        }

        @Test
        public void testEventCreationChangeDate(){

            //  int before = app.getEventHelper().getEventCountByMonth("November");
            app.getEventHelper().clickOnPlusButton();
            app.getEventHelper().clickOnPencil();
            app.getEventHelper().swipeCalendarToLeft();

       /*string month, String day (Dec 31)
        list of dates (3)
        (for int i = 0; i<list.size; i++){
        String month = ...
        while(month!=month){
        tapOnArrow();
        }
        while day!= day
        }
     */
            app.getEventHelper().selectDate("future","NOVEMBER", "10");

            app.getEventHelper().fillEventCreationForm("aaadaaa", "1", 0, "50");
            app.getEventHelper().clickOnAddEventButton();

            Assert.assertTrue(app.getEventHelper().isEventPresent());
            int after = app.getEventHelper().getEventCountByMonth("November");

            //   Assert.assertEquals(after, before+1 );

        }


}

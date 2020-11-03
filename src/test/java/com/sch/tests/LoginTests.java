package com.sch.tests;

import com.sch.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws InterruptedException {
        app.getUserHelper().fillLoginForm(new User()
                .withEmail("mickeymouse.tester1@gmail.com")
                .withPassword("Mm123456789"));
        // app.getUserHelper().closeKeyboard();
        app.getUserHelper().clickOnLoginButton();
        Thread.sleep(5000);

        Assert.assertFalse(app.getUserHelper().isLoginButtonPresent());

    }

}

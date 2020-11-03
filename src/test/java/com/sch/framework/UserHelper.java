package com.sch.framework;

import com.sch.model.User;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase{

    public UserHelper(AppiumDriver driver) {
        super(driver);
    }

    public void fillLoginForm(User user) {
        waitForElementAndType(By.id("log_email_input"), 5, user.getEmail());
        type(By.id("log_password_input"), user.getPassword());
    }

    public void clickOnLoginButton() {
        click(By.id("login_btn"));
    }

    public boolean isLoginButtonPresent()  {
        return isElementPresent(By.id("login_btn"));
    }

    public void defaultLogin() throws InterruptedException {
        fillLoginForm(new User()
                .withEmail("mickeymouse.tester1@gmail.com")
                .withPassword("Mm123456789"));
      //  closeKeyboard();
        clickOnLoginButton();
        Thread.sleep(5000);
    }


}



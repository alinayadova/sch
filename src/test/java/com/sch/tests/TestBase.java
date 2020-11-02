package com.sch.tests;

import com.sch.framework.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;

public class TestBase {


    protected  static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        app.init();
    }


    @AfterSuite(enabled = false)
     public void tearDown(){
        app.stop();
    }

}

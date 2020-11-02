package com.sch.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenAppTests extends TestBase{


    @Test
    public void testLaunchTest(){
        String version = app.getAppVersion();
        System.out.println("App version " + version + " launched");
        Assert.assertEquals(version, "0.0.3");

    }


}

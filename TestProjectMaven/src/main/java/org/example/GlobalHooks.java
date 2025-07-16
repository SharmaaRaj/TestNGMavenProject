package org.example;


import io.cucumber.java.After;

public class GlobalHooks {

    @After
    public void browserClose() {
        Utils.quitBrowser();
    }
}

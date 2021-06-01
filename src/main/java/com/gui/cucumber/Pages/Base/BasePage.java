package com.gui.cucumber.Pages.Base;


import com.gui.cucumber.Helpers.Driver;

public abstract class BasePage extends PageInstances {

    public BasePage() {
        currentWindowHandler = Driver.getDriver().getWindowHandle();
    }

}

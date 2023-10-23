package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.LocalDriverContext;
import org.openqa.selenium.WebElement;


public class ButtonBase extends ControlBase implements Button {


    public ButtonBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        getWrappedElement().click();
    }

    @Override
    public void PerformSubmit() {
        getWrappedElement().submit();
    }

    @Override
    public void waitAndPerformClick() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        getWrappedElement().click();
    }

    @Override
    public void PerformJSClick() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        DriverContext.jsClickOnElement(getWrappedElement());
    }

    public void PerformConditionalClick() {
        String browserType = LocalDriverContext.getFrameworkConfiguration().getBrowserType().toString();
        if (browserType.equals("Win10_IE_CBT") || browserType.equals("Win10_Edge_CBT") || browserType.equals("OSX_Safari_CBT") || browserType.equals("iOS_Safari_CBT") || browserType.equals("Android_Chrome_CBT")) {
            DriverContext.waitUntilElementIsVisible(getWrappedElement());
            DriverContext.jsClickOnElement(getWrappedElement());
        } else {
            DriverContext.waitUntilElementIsVisible(getWrappedElement());
            getWrappedElement().click();
        }
    }

    @Override
    public String GetButtonText() {
        return getWrappedElement().getText();
    }

}

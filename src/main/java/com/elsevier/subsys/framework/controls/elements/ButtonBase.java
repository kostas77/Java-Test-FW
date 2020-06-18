package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.base.LocalDriverContext;
import com.elsevier.subsys.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class ButtonBase extends ControlBase implements Button {


    public ButtonBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        DriverContext.WaitUntilElementIsVisible(getWrappedElement());
        getWrappedElement().click();
    }

    public void PerformJSClick() {
        DriverContext.WaitUntilElementIsVisible(getWrappedElement());
        DriverContext.jsClickOnElement(getWrappedElement());
    }

    public void PerformConditionalClick() {
        String browserType = LocalDriverContext.getFrameworkConfiguration().getBrowserType().toString();
        if (browserType.equals("Win10_IE_CBT") || browserType.equals("Win10_Edge_CBT") || browserType.equals("OSX_Safari_CBT") || browserType.equals("iOS_Safari_CBT") || browserType.equals("Android_Chrome_CBT")) {
            DriverContext.WaitUntilElementIsVisible(getWrappedElement());
            DriverContext.jsClickOnElement(getWrappedElement());
        } else {
            DriverContext.WaitUntilElementIsVisible(getWrappedElement());
            getWrappedElement().click();
        }
    }

    @Override
    public void PerformSubmit() {
        DriverContext.WaitUntilElementIsVisible(getWrappedElement());
        getWrappedElement().submit();
    }

    @Override
    public String GetButtonText() {
        return getWrappedElement().getText();
    }


}

package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.LocalDriverContext;
import org.openqa.selenium.WebElement;


public class HyperLinkBase extends ControlBase implements HyperLink {

    public HyperLinkBase(WebElement element) {
        super(element);
    }

    @Override
    public void ClickLink() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        getWrappedElement().click();
    }

    @Override
    public void JSClickLink() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        DriverContext.jsClickOnElement(getWrappedElement());
    }

    @Override
    public void ConditionalClickLink() {
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
    public String GetLinkText() {
        return getWrappedElement().getText();
    }

    @Override
    public String GetLinkUrlText() {
        return getWrappedElement().getAttribute("href");
    }

    @Override
    public boolean CheckLinkTextContains(String linkText) {
        if (getWrappedElement().getText().contains(linkText))
            return true;
        else
            return false;
    }

    @Override
    public boolean CheckLinkUrlTextContains(String urlText) {
        if (getWrappedElement().getAttribute("href").contains(urlText))
            return true;
        else
            return false;
    }

}

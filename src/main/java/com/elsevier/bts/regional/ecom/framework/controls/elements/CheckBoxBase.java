package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class CheckBoxBase extends ControlBase implements CheckBox {


    public CheckBoxBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        getWrappedElement().click();
    }


    public void PerformJSClick() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        DriverContext.jsClickOnElement(getWrappedElement());
    }
}

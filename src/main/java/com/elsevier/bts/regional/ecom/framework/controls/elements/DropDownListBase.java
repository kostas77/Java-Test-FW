package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

public class DropDownListBase extends ControlBase implements DropDownList {


    public DropDownListBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        getWrappedElement().click();
    }


}

package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class RadioButtonBase extends ControlBase implements RadioButton {


    public RadioButtonBase(WebElement element) {
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
    public String GetButtonText() {
        return getWrappedElement().getText();
    }


}

package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.internals.ControlBase;
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

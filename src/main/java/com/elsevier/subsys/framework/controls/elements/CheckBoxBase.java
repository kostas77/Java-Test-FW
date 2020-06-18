package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class CheckBoxBase extends ControlBase implements CheckBox {


    public CheckBoxBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        getWrappedElement().click();
    }

}

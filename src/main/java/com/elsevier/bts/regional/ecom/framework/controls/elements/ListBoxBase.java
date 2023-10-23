package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class ListBoxBase extends ControlBase implements ListBox {


    public ListBoxBase(WebElement element) {
        super(element);
    }

    @Override
    public void PerformClick() {
        getWrappedElement().click();
    }

}

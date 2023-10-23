package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class TextFieldBase extends ControlBase implements TextField {


    public TextFieldBase(WebElement element) {
        super(element);
    }

    public void EnterText(String text) {
        getWrappedElement().sendKeys(text);
    }

    @Override
    public String GetTextValue() {
        return getWrappedElement().getText();
    }
}

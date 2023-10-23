package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;


public class TextBase extends ControlBase implements Text {


    public TextBase(WebElement element) {
        super(element);
    }

    @Override
    public String GetTextValue() {
        return getWrappedElement().getText();
    }
}

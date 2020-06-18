package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.internals.ControlBase;
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

package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;
import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;

@ImplementedBy(TextFieldBase.class)
public interface TextField extends Control {

    void EnterText(String text);

    String GetTextValue();

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase ScrollToElement();

}

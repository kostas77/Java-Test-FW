package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.api.ImplementedBy;
import com.elsevier.subsys.framework.controls.internals.Control;

@ImplementedBy(TextFieldBase.class)
public interface TextField extends Control {

    void EnterText(String text);
    String GetTextValue();

}

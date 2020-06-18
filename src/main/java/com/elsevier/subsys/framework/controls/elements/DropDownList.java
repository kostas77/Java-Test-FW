package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.api.ImplementedBy;
import com.elsevier.subsys.framework.controls.internals.Control;
import com.elsevier.subsys.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface DropDownList extends Control {

    void PerformClick();
    ControlBase Wait();
    ControlBase WaitForVisible();
    ControlBase Click();
    ControlBase ScrollToElement();
}

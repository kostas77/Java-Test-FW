package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.api.ImplementedBy;
import com.elsevier.subsys.framework.controls.internals.Control;
import com.elsevier.subsys.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

    void PerformClick();
    void PerformJSClick();
    void PerformConditionalClick();
    void PerformSubmit();
    String GetButtonText();
    ControlBase Wait();
    ControlBase WaitForVisible();
    ControlBase Click();
    ControlBase ScrollToElement();
}

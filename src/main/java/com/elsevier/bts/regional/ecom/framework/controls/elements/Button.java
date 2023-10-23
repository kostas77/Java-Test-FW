package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;
import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

    void waitAndPerformClick();

    void PerformClick();

    void PerformJSClick();

    void PerformConditionalClick();

    void PerformSubmit();

    String GetButtonText();

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase WaitForClickable();

    ControlBase Click();

    ControlBase ScrollToElement();
}

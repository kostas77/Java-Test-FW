package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;
import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;

@ImplementedBy(ButtonBase.class)
public interface CheckBox extends Control {

    void PerformClick();

    void PerformJSClick();

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase Click();

    ControlBase ScrollToElement();
}

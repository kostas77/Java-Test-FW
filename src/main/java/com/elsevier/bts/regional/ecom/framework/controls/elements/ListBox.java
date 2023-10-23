package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;
import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface ListBox extends Control {

    void PerformClick();

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase Click();

    ControlBase ScrollToElement();
}

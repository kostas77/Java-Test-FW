package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;
import com.elsevier.bts.regional.ecom.framework.controls.internals.ControlBase;

@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control {

    void ClickLink();

    void JSClickLink();

    void ConditionalClickLink();

    String GetLinkText();

    boolean CheckLinkTextContains(String containsText);

    String GetLinkUrlText();

    boolean CheckLinkUrlTextContains(String containsText);

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase Click();
}

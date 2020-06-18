package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.api.ImplementedBy;
import com.elsevier.subsys.framework.controls.internals.Control;
import com.elsevier.subsys.framework.controls.internals.ControlBase;

@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control{

    void ClickLink();
    void JSClickLink();
    void ConditionalClickLink();
    String GetLinkText();
    boolean CheckLinkTextContains(String containsText);
    String GetLinkUrlText();
    boolean CheckLinkUrlTextContains(String containsText);
    ControlBase Wait();
    ControlBase WaitForVisible();
    ControlBase Click();
}

package com.elsevier.bts.regional.ecom.framework.controls.internals;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;


@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {

    ControlBase WaitForPageToLoad();

    ControlBase WaitForVisible();

    ControlBase WaitForClickable();

    ControlBase Click();

    ControlBase ScrollToElement();

//    ControlBase findElements();

}

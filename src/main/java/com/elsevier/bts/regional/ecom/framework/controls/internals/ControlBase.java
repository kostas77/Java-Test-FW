package com.elsevier.bts.regional.ecom.framework.controls.internals;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;

import java.util.List;


public class ControlBase implements Control {

    private final WebElement element;

    public ControlBase(final WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return element.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }

    @Override
    public Coordinates getCoordinates() {
        return null;
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    @Override
    public ControlBase WaitForPageToLoad() {
        DriverContext.waitForPageToLoad();
        return this;
    }

    @Override
    public ControlBase WaitForVisible() {
        DriverContext.waitUntilElementIsVisible(getWrappedElement());
        return this;
    }

    @Override
    public ControlBase WaitForClickable() {
        DriverContext.waitUntilElementIsClickable(getWrappedElement());
        return this;
    }

    @Override
    public ControlBase Click() {
        getWrappedElement().click();
        return this;
    }

//    @Override
//    public ControlBase EnterText(String text) {
//        getWrappedElement().sendKeys(text);
//        return this;
//    }

    @Override
    public ControlBase ScrollToElement() {
        //JAVA Script
        return this;
    }

}

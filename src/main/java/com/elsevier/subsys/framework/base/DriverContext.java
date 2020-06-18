package com.elsevier.subsys.framework.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

@Slf4j
public class DriverContext {


    public static void goToUrl(String url) {
        LocalDriverContext.getWebDriver().get(url);
    }

    public static void quitDriver() {
        LocalDriverContext.getWebDriver().quit();
    }

    public static WebElement getElement(final String cssLocator) {
        return LocalDriverContext.getWebDriver().findElement(By.cssSelector(cssLocator));
    }

    public static List<WebElement> getElementsList(final String cssLocator) {
        return LocalDriverContext.getWebDriver().findElements(By.cssSelector(cssLocator));
    }

    public static void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) LocalDriverContext.getWebDriver()).executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
        if(!jsReady) {
            wait.until(jsLoad);
        } else {
            log.debug("Page is ready!");
        }
    }

    public static String getCurrentUrl() {
        return LocalDriverContext.getWebDriver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return LocalDriverContext.getWebDriver().getTitle();
    }

    public static void jsScrollToElementAlignTop (final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elementFindBy);
    }

    public static void jsScrollToElementAlignBottom (final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", elementFindBy);
    }

    public static void jsClickOnElement (final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].click();", elementFindBy);
    }

    public static Set<String> getWindowHandlesSet() {
        return LocalDriverContext.getWebDriver().getWindowHandles();
    }

    public static void switchToWindowHandle(String windowHandle) {
        LocalDriverContext.getWebDriver().switchTo().window(windowHandle);
    }

    public static void switchToNewTab(){
        Set<String> tabs= DriverContext.getWindowHandlesSet();
        DriverContext.switchToWindowHandle(tabs.toArray()[1].toString());
    }

    // Element State verification helpers
    public static boolean IsElementDisplayed(final WebElement elementFindBy) {
        return elementFindBy.isDisplayed();
    }

    public static boolean IsElementEnabled(final WebElement elementFindBy) {
        return elementFindBy.isEnabled();
    }

    public static boolean IsElementSelected(final WebElement elementFindBy) {
        return elementFindBy.isSelected();
    }

    // Explicit Wait Helpers
    public static void WaitUntilElementIsVisible(final WebElement elementFindBy) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
//        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(elementFindBy));
    }

    public static void WaitUntilElementIsClickable(final WebElement elementFindBy) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
    }

    public static void WaitUntilElementIsSelected(final WebElement elementFindBy) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.elementToBeSelected(elementFindBy));
    }

    public static void WaitUntilElementSelectionStateIs(final WebElement elementFindBy, boolean selectedState) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.elementSelectionStateToBe(elementFindBy, selectedState));
    }

    public static void WaitUntilPageUrlContains(final String url) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public static void WaitUntilPageUrlIs(final String url) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.urlMatches(url));
    }

    public static void WaitUntilPageTitleContains(final String title) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void WaitUntilPageTitleIs(final String title) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void WaitForElementTextVisible(final WebElement elementFindBy, String text) {
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getWebDriver(), 30);
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    public static void WaitUntilTextIsDisplayed(final By element, String text) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(),30);
        wait.until(textDisplayed(element, text));
    }

    private static ExpectedCondition<Boolean> textDisplayed (final By elementFindBy, final String text) {
        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }

    public static void DriverSleep(Integer timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static async getElementAttribute (webElement: WebElement, elementAttribute: string) // TODO - placeholder

//    public static async elementHasClass (driver: WebDriver, selector: string, classNeedle: string) // TODO - placeholder

//    public static refreshPageUntilElementPresent // TODO - placeholder

}

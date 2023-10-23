package com.elsevier.bts.regional.ecom.framework.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Slf4j
public class DriverContext {

    static Duration secs120 = Duration.ofSeconds(120);
    static Duration secs60 = Duration.ofSeconds(60);

    public static void goToUrl(String url) {
        LocalDriverContext.getWebDriver().get(url);
    }

    public static void quitDriver() {
        LocalDriverContext.getWebDriver().quit();
    }


    public static WebElement findElement(final By Locator) {
        return LocalDriverContext.getWebDriver().findElement(Locator);
    }

    public static WebElement getElement(final String cssLocator) {
        return LocalDriverContext.getWebDriver().findElement(By.cssSelector(cssLocator));
    }

    public static WebElement getElementByXpath(final String xpath) {
        return LocalDriverContext.getWebDriver().findElement(By.xpath(xpath));
    }

    public static List<WebElement> getElementsList(final String cssLocator) {
        return LocalDriverContext.getWebDriver().findElements(By.cssSelector(cssLocator));
    }

    public static void waitForPageToLoad() {
        if (!System.getenv("BrowserType").equals("Win10_IE_LT")) {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs120);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                    } catch (Exception e) {
                        return true;
                    }
                }
            };
            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                            .toString().equals("complete");
                }
            };
//            wait.until(jQueryLoad);
            wait.until(jsLoad);

//            ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) LocalDriverContext.getWebDriver()).executeScript("return document.readyState").toString().equals("complete");
//            boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//            if (!jsReady) {
//                wait.until(jsLoad);
//            }
        }
    }

    public static double pageLoadTime() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        return (double) (Double) jsExecutor.executeScript(
                "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
    }

    public static void waitForReactComponentsToLoad() {
        if (!System.getenv("BrowserType").equals("Win10_IE_LT")) {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs120);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
            // wait for REactJS components to load
            ExpectedCondition<Boolean> reactJSLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        return ((JavascriptExecutor) driver).executeScript("window.document.hasHomeMounted").toString().equals("true"); // TODO: This need to be double checked and tested
                    } catch (Exception e) {
                        return true;
                    }
                }
            };
            wait.until(reactJSLoad);
        }
    }

    public static void waitForHTMLLoad(int maxWait, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWait) {
            String prevState = LocalDriverContext.getWebDriver().getPageSource();
            driverSleep(pollDelimiter);
            if (prevState.equals(LocalDriverContext.getWebDriver().getPageSource())) {
                log.debug("- Current page HTML unchanged");
                return;
            }
        }
        log.debug("- HTML Load timeout reached");
    }

    public static String getCurrentUrl() {
        return LocalDriverContext.getWebDriver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return LocalDriverContext.getWebDriver().getTitle();
    }

    public static void consoleData() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        List<Object> logs = (List<Object>) jsExecutor.executeScript("return window.console.logs");
        for (Object log : logs) {
            System.out.println(log.toString());
        }
    }

    public static void jsScrollToElementAlignTop(final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elementFindBy);
    }

    public static void jsScrollToElementAlignBottom(final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", elementFindBy);
    }

    public static void jsClickOnElement(final WebElement elementFindBy) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("arguments[0].click();", elementFindBy);
    }

    public static void jsSmartUIScreenshot(String screenshotName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("smartui.takeScreenshot=" + screenshotName);
    }

    public static void jsSmartUIFullPageScreenshot(String screenshotName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
        jsExecutor.executeScript("smartui.takeFullPageScreenshot=" + screenshotName);
    }

    public static Set<String> getWindowHandlesSet() {
        return LocalDriverContext.getWebDriver().getWindowHandles();
    }

    public static void switchToWindowHandle(String windowHandle) {
        LocalDriverContext.getWebDriver().switchTo().window(windowHandle);
    }

    public static void switchToTab(int tabIndex) {
        Set<String> tabs = getWindowHandlesSet();
        switchToWindowHandle(tabs.toArray()[tabIndex].toString());
    }

    public static void switchToFrame(WebElement targetFrame) {
        waitUntilElementIsClickable(targetFrame);
        LocalDriverContext.getWebDriver().switchTo().frame(targetFrame);
    }

    public static Alert switchToAlert() {
        return LocalDriverContext.getWebDriver().switchTo().alert();
    }

    public static void switchToDefaultContent() {
        LocalDriverContext.getWebDriver().switchTo().defaultContent();
    }


    // Element State verification helpers
    public static boolean isElementDisplayed(final WebElement elementFindBy) {
        return elementFindBy.isDisplayed();
    }

    public static boolean isElementEnabled(final WebElement elementFindBy) {
        return elementFindBy.isEnabled();
    }

    public static boolean isElementSelected(final WebElement elementFindBy) {
        return elementFindBy.isSelected();
    }


    // Explicit Wait Helpers
    public static void waitUntilElementIsVisible(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(elementFindBy));
    }

    public static void waitUntilElementIsClickable(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
    }

    public static void waitUntilElementIsSelected(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.elementToBeSelected(elementFindBy));
    }

    public static void waitUntilElementSelectionStateIs(final WebElement elementFindBy, boolean selectedState) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.elementSelectionStateToBe(elementFindBy, selectedState));
    }

    public static void waitUntilElementAttributeIs(final WebElement elementFindBy, String attribute, String text) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.attributeToBe(elementFindBy, attribute, text));
    }

    public static void waitUntilPageUrlContains(final String url) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public static void waitUntilPageUrlIs(final String url) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.urlMatches(url));
    }

    public static void waitUntilPageTitleContains(final String title) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilPageTitleIs(final String title) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitForElementTextVisible(final WebElement elementFindBy, String text) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    public static void waitUntilElementIsNotVisible(final WebElement element) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getWebDriver(), secs60);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void driverSleep(Integer timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            System.out.println("- Caught exception stacktrace (driverSleep method): ");
//            e.printStackTrace();
        }
    }

    public static boolean doesElementExist(final WebElement elementFindBy) {
        boolean exists = true;
        try {
            isElementEnabled(elementFindBy);
//        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            exists = false;
        }
        return exists;
    }

    public static void hoverOverElement(final WebElement element) {
        Actions actions = new Actions(LocalDriverContext.getWebDriver());
        actions.clickAndHold(element).pause(100);
        actions.clickAndHold(element).perform();
    }

    public static void hoverOverElementWithVariableTime(final WebElement element, int milliseconds) {
        Actions actions = new Actions(LocalDriverContext.getWebDriver());
        actions.clickAndHold(element).pause(milliseconds);
        actions.clickAndHold(element).perform();
    }

    public static void selectDropdownListElementUsingVisibleText(final By dropdownListLocator, String visibleText) {
        Select dropdownList = new Select(findElement(dropdownListLocator));
        dropdownList.selectByVisibleText(visibleText);
    }

    public static void selectDropDownListElementUsingValue(final WebElement dropdownListElement, String listItemValue) {
        Select dropdownList = new Select(dropdownListElement);
        dropdownList.selectByValue(listItemValue);
    }

    public static void selectDropdownListElementUsingIndex(final By dropdownListLocator, int listItemIndex) {
        Select dropdownList = new Select(findElement(dropdownListLocator));
        dropdownList.selectByIndex(listItemIndex);
    }

    public static void closeCurrentTab() {
        LocalDriverContext.getWebDriver().close();
    }

    public static void repeatWaitForElementVisibilityAttempt(final WebElement element, final Integer noAttempts) {
        for (int i = 0; i < noAttempts; i++) {
            try {
                DriverContext.waitUntilElementIsVisible(element);
                break;
            } catch (Exception e) {
                log.error("- Caught exception stacktrace (repeatWaitForElementVisibilityAttempt method) - " + (i + 1) + " of " + noAttempts);
//                e.printStackTrace();
            }
        }
    }

    public static void repeatElementClickAttempt(final WebElement element, final Integer noAttempts) {
        for (int i = 0; i < noAttempts; i++) {
            try {
                DriverContext.waitUntilElementIsClickable(element);
                element.click();
                break;
            } catch (Exception e) {
                log.error("- Caught exception stacktrace (repeatElementClickAttempt method) - " + (i + 1) + " of " + noAttempts);
//                e.printStackTrace();
            }
        }
    }
//    public static async getElementAttribute (webElement: WebElement, elementAttribute: string) // TODO - placeholder

//    public static async elementHasClass (driver: WebDriver, selector: string, classNeedle: string) // TODO - placeholder

//    public static refreshPageUntilElementPresent // TODO - placeholder

}

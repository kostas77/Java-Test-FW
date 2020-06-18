package com.elsevier.subsys.framework.base;

import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import org.openqa.selenium.WebDriver;

/*
Class to support Selenium Grid feature
 */

public class LocalDriverContext {

    private static ThreadLocal<WebDriverContext> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return webDriverThreadLocal.get().getWebDriver();
    }

    public static FrameworkConfigurationService getFrameworkConfiguration() {
        return webDriverThreadLocal.get().getFrameworkConfigurationService();
    }

    static void bindWebDriverToThreadLocal(WebDriverContext driverThreadLocal) {
        webDriverThreadLocal.set(driverThreadLocal);
    }

}

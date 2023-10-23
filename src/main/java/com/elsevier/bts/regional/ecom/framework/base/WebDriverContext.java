package com.elsevier.bts.regional.ecom.framework.base;

import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class WebDriverContext {

    private WebDriver webDriver;
    private FrameworkConfigurationService frameworkConfigurationService;

    public WebDriverContext(WebDriver webDriver, FrameworkConfigurationService frameworkConfigurationService) {
        this.webDriver = webDriver;
        this.frameworkConfigurationService = frameworkConfigurationService;
    }

}

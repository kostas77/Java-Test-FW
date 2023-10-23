package com.elsevier.bts.regional.ecom.framework.base;

import com.elsevier.bts.regional.ecom.framework.controls.api.ControlFactory;

import java.net.URL;

public class Base {

    public static final URL axeScriptUrl = Base.class.getResource("/axe.min.js");

    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page) {
        //Custom control page factory initialization
        Object obj = ControlFactory.initElements(LocalDriverContext.getWebDriver(), page);
        return page.cast(obj);
    }
}

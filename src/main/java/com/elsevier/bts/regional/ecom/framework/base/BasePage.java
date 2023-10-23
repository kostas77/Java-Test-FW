package com.elsevier.bts.regional.ecom.framework.base;

public abstract class BasePage extends Base {

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance) {
        try {
            return (TPage) this;
        } catch (Exception e) {
            e.getStackTrace();
        }

        return null;
    }

}

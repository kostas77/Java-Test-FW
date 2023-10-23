package com.elsevier.bts.regional.ecom.framework.config;

import com.elsevier.bts.regional.ecom.framework.base.BrowserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class FrameworkConfigurationService {

    @Getter(AccessLevel.NONE)
    @Value("#{environment.manuscriptDashboardLocalDeploymentUrl ?: 'https://md-ux.triage-nonprod.elsevier.com/index.html'}")
    private String rootUrl;

    @Value("#{environment.BrowserType ?: 'Win10_Chrome_LT'}")
    private BrowserType BrowserType;

    @Value("#{environment.BrowserVersion ?: 'latest'}")
    private String BrowserVersion;

    @Setter(AccessLevel.PUBLIC)
    @Value("#{environment.TestEnv ?: 'UAT'}")
    private String TestEnv;

    @Value("${AUTConnectionString}")
    private String AUTConnectionString;

    @Value("${ReportingConnectionString}")
    private String ReportingConnectionString;

    @Value("${LogPath}")
    private String LogPath;

    @Value("${DriverType}")
    private String DriverType;

    @Value("${ExcelSheetPath}")
    private String ExcelSheetPath;

    @Value("${AUT}")
    private String AUT;

    @Value("${CBTSeleniumGrid}")
    private String CBTSeleniumGridHub;

    @Value("#{'https://' + environment.LT_USERNAME + ':' + environment.LT_ACCESS_KEY + '@hub.lambdatest.com/wd/hub'}")
    private String LTSeleniumGridHub;

    @Value("#{'http://0.0.0.0:4723/wd/hub'}")
    private String LocalSeleniumGridHub;

    @Value("#{'https://' + environment.BROWSERSTACK_USERNAME + ':' + environment.BROWSERSTACK_ACCESS_KEY + '@hub-cloud.browserstack.com/wd/hub'}")
    private String BSSeleniumGridHub;

    @Value("${ZaleniumGrid}")
    private String ZaleniumGridHub;

    @Value("${HistoricalReport}")
    private String HistoricalReport;

    @Value("#{environment.reviewerRecommenderLocalDeploymentUrl ?: 'https://reviewerrecommender-nonprod.elsevier.com/#/journal/JOURNAL/submission/MOCK_SUCCESS/revision/690377/reviewer/1?code=READONLY-4ikos41fJQCKLS0aNpfXECLicOyrtbzmPGd0IFAeDkVnknG1Rt5hPLvBUGNH'}")
    private String reviewerRecommenderBaseUrl;

//    public String getManuscriptDashboardBaseUrlReferences() {
//        return rootUrl + manuscriptDashboardBaseUrlReferences;
//    }

    @Value("#{environment.ZAP_scan_enabled ?: false}")
    private Boolean ZAP_scan_enabled;

    @Value("${ZAP_ADDRESS}")
    private String ZAP_ADDRESS;

    @Value("${ZAP_PORT}")
    private Integer ZAP_PORT;

    @Value("${ZAP_API_KEY}")
    private String ZAP_API_KEY;

    @Value("${USHS_ECOM_LOCAL_URL}")
    private String USHS_ECOM_LOCAL_URL;

    @Value("${USHS_ECOM_DEV_URL}")
    private String USHS_ECOM_DEV_URL;

    @Value("${USHS_ECOM_UAT_URL}")
    private String USHS_ECOM_UAT_URL;

    @Value("${USHS_ECOM_STAGING_URL}")
    private String USHS_ECOM_STAGING_URL;

    @Value("${USHS_ECOM_IDPLUS_URL}")
    private String USHS_ECOM_IDPLUS_URL;

    @Value("${USHS_ECOM_PROD_URL}")
    private String USHS_ECOM_PROD_URL;

    @Value("${UKHS_ECOM_LOCAL_URL}")
    private String UKHS_ECOM_LOCAL_URL;

    @Value("${UKHS_ECOM_DEV_URL}")
    private String UKHS_ECOM_DEV_URL;

    @Value("${UKHS_ECOM_UAT_URL}")
    private String UKHS_ECOM_UAT_URL;

    @Value("${UKHS_ECOM_STAGING_URL}")
    private String UKHS_ECOM_STAGING_URL;

    @Value("${UKHS_ECOM_IDPLUS_URL}")
    private String UKHS_ECOM_IDPLUS_URL;

    @Value("${UKHS_ECOM_PROD_URL}")
    private String UKHS_ECOM_PROD_URL;

    @Value("${FRHS_ECOM_LOCAL_URL}")
    private String FRHS_ECOM_LOCAL_URL;

    @Value("${FRHS_ECOM_DEV_URL}")
    private String FRHS_ECOM_DEV_URL;

    @Value("${FRHS_ECOM_UAT_URL}")
    private String FRHS_ECOM_UAT_URL;

    @Value("${FRHS_ECOM_STAGING_URL}")
    private String FRHS_ECOM_STAGING_URL;

    @Value("${FRHS_ECOM_IDPLUS_URL}")
    private String FRHS_ECOM_IDPLUS_URL;

    @Value("${FRHS_ECOM_PROD_URL}")
    private String FRHS_ECOM_PROD_URL;

    @Value("${DEHS_ECOM_LOCAL_URL}")
    private String DEHS_ECOM_LOCAL_URL;

    @Value("${DEHS_ECOM_DEV_URL}")
    private String DEHS_ECOM_DEV_URL;

    @Value("${DEHS_ECOM_UAT_URL}")
    private String DEHS_ECOM_UAT_URL;

    @Value("${DEHS_ECOM_STAGING_URL}")
    private String DEHS_ECOM_STAGING_URL;

    @Value("${DEHS_ECOM_IDPLUS_URL}")
    private String DEHS_ECOM_IDPLUS_URL;

    @Value("${DEHS_ECOM_PROD_URL}")
    private String DEHS_ECOM_PROD_URL;

    @Value("${LATAMHS_ECOM_LOCAL_URL}")
    private String LATAMHS_ECOM_LOCAL_URL;

    @Value("${LATAMHS_ECOM_DEV_URL}")
    private String LATAMHS_ECOM_DEV_URL;

    @Value("${LATAMHS_ECOM_UAT_URL}")
    private String LATAMHS_ECOM_UAT_URL;

    @Value("${LATAMHS_ECOM_STAGING_URL}")
    private String LATAMHS_ECOM_STAGING_URL;

    @Value("${LATAMHS_ECOM_IDPLUS_URL}")
    private String LATAMHS_ECOM_IDPLUS_URL;

    @Value("${LATAMHS_ECOM_PROD_URL}")
    private String LATAMHS_ECOM_PROD_URL;

    @Value("${EUHS_ECOM_LOCAL_URL}")
    private String EUHS_ECOM_LOCAL_URL;

    @Value("${EUHS_ECOM_DEV_URL}")
    private String EUHS_ECOM_DEV_URL;

    @Value("${EUHS_ECOM_UAT_URL}")
    private String EUHS_ECOM_UAT_URL;

    @Value("${EUHS_ECOM_STAGING_URL}")
    private String EUHS_ECOM_STAGING_URL;

    @Value("${EUHS_ECOM_IDPLUS_URL}")
    private String EUHS_ECOM_IDPLUS_URL;

    @Value("${EUHS_ECOM_PROD_URL}")
    private String EUHS_ECOM_PROD_URL;

    @Value("${INHS_ECOM_DEV_URL}")
    private String INHS_ECOM_DEV_URL;

    @Value("${INHS_ECOM_UAT_URL}")
    private String INHS_ECOM_UAT_URL;

    @Value("${INHS_ECOM_STAGING_URL}")
    private String INHS_ECOM_STAGING_URL;

    @Value("${INHS_ECOM_IDPLUS_URL}")
    private String INHS_ECOM_IDPLUS_URL;

    @Value("${INHS_ECOM_PROD_URL}")
    private String INHS_ECOM_PROD_URL;

    @Value("${SPHS_ECOM_LOCAL_URL}")
    private String SPHS_ECOM_LOCAL_URL;

    @Value("${SPHS_ECOM_DEV_URL}")
    private String SPHS_ECOM_DEV_URL;

    @Value("${SPHS_ECOM_UAT_URL}")
    private String SPHS_ECOM_UAT_URL;

    @Value("${SPHS_ECOM_STAGING_URL}")
    private String SPHS_ECOM_STAGING_URL;

    @Value("${SPHS_ECOM_IDPLUS_URL}")
    private String SPHS_ECOM_IDPLUS_URL;

    @Value("${SPHS_ECOM_PROD_URL}")
    private String SPHS_ECOM_PROD_URL;

    @Value("${MEHS_ECOM_LOCAL_URL}")
    private String MEHS_ECOM_LOCAL_URL;

    @Value("${MEHS_ECOM_DEV_URL}")
    private String MEHS_ECOM_DEV_URL;

    @Value("${MEHS_ECOM_UAT_URL}")
    private String MEHS_ECOM_UAT_URL;

    @Value("${MEHS_ECOM_STAGING_URL}")
    private String MEHS_ECOM_STAGING_URL;

    @Value("${MEHS_ECOM_IDPLUS_URL}")
    private String MEHS_ECOM_IDPLUS_URL;

    @Value("${MEHS_ECOM_PROD_URL}")
    private String MEHS_ECOM_PROD_URL;

    @Value("${ANZHS_ECOM_DEV_URL}")
    private String ANZHS_ECOM_DEV_URL;

    @Value("${ANZHS_ECOM_UAT_URL}")
    private String ANZHS_ECOM_UAT_URL;

    @Value("${ANZHS_ECOM_STAGING_URL}")
    private String ANZHS_ECOM_STAGING_URL;

    @Value("${ANZHS_ECOM_IDPLUS_URL}")
    private String ANZHS_ECOM_IDPLUS_URL;

    @Value("${ANZHS_ECOM_PROD_URL}")
    private String ANZHS_ECOM_PROD_URL;

    @Value("${ASIAHS_ECOM_DEV_URL}")
    private String ASIAHS_ECOM_DEV_URL;

    @Value("${ASIAHS_ECOM_UAT_URL}")
    private String ASIAHS_ECOM_UAT_URL;

    @Value("${ASIAHS_ECOM_STAGING_URL}")
    private String ASIAHS_ECOM_STAGING_URL;

    @Value("${ASIAHS_ECOM_IDPLUS_URL}")
    private String ASIAHS_ECOM_IDPLUS_URL;

    @Value("${ASIAHS_ECOM_PROD_URL}")
    private String ASIAHS_ECOM_PROD_URL;

    @Value("${ORR_Url}")
    private String ORR_Url;

    @Value("${ORR_BASE_URI}")
    private String ORR_BASE_URI;

    @Value("${ORR_USHS_Order_Url}")
    private String ORR_USHS_Order_Url;

    @Value("${ORR_UKHS_Order_Url}")
    private String ORR_UKHS_Order_Url;

    @Value("${ORR_FRHS_Order_Url}")
    private String ORR_FRHS_Order_Url;

    @Value("${ORR_DEHS_Order_Url}")
    private String ORR_DEHS_Order_Url;

    @Value("${ORR_LATAMHS_Order_Url}")
    private String ORR_LATAMHS_Order_Url;

    @Value("${ORR_EUHS_Order_Url}")
    private String ORR_EUHS_Order_Url;

    @Value("${ORR_SPHS_Order_Url}")
    private String ORR_SPHS_Order_Url;

    @Value("${ORR_MEHS_Order_Url}")
    private String ORR_MEHS_Order_Url;

    @Value("#{environment.AD_USERNAME}")
    private String AD_USERNAME;

    @Value("#{environment.AD_PASSWORD}")
    private String AD_PASSWORD;

    @Value("${ORR_username}")
    private String ORR_USERNAME;

    @Value("${ORR_password}")
    private String ORR_PASSWORD;

    @Value("${AWS_SecretName}")
    private String AWS_SecretName;

    @Value("${ADMIN_PANEL_UAT_URL}")
    private String ADMIN_PANEL_UAT_URL;

    @Value("${ADMIN_PANEL_STAGING_URL}")
    private String ADMIN_PANEL_STAGING_URL;

    @Value("${GMAIL_Username}")
    public String GMAIL_Username;

    @Value("${GMAIL_Password}")
    public String GMAIL_Password;

    @Value("${US_SP_UAT_URL}")
    private String US_SP_UAT_URL;

    @Value("${US_EOP_UAT_URL}")
    private String US_EOP_UAT_URL;

    @Value("${UK_EOP_UAT_URL}")
    private String UK_EOP_UAT_URL;

    @Value("${JP_EOP_UAT_URL}")
    private String JP_EOP_UAT_URL;

    @Value("${EU_EOP_UAT_URL}")
    private String EU_EOP_UAT_URL;
}

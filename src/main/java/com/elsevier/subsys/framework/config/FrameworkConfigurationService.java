package com.elsevier.subsys.framework.config;

import com.elsevier.subsys.framework.base.BrowserType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class FrameworkConfigurationService {

    @Value("#{environment.BrowserType ?: 'Win10_Chrome_CBT'}")
    private BrowserType BrowserType;

    @Value("#{environment.BrowserVersion ?: '80'}")
    private String BrowserVersion;

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

    @Value("${TARDIS_placeholderPage}")
    private String manuscriptDashboardBaseUrl;

    @Value("${TARDIS_placeholderPage2}")
    private String manuscriptDashboardBaseUrl2;

    @Value("${TARDIS_placeholderPageAlternate}")
    private String manuscriptDashboardBaseUrlAlternate;

    @Value("${newDashboardPageStandaloneNonProdreferences}")
    private String manuscriptDashboardBaseUrlReferences;

    @Value("${ENGAGE_placeholderPage}")
    private String reviewerHubBaseUrl;

    @Value("${ENGAGE_certificatePage}")
    private String reviewerHubCertificateLocation;

    @Value("${CBTSeleniumGrid}")
    private String CBTSeleniumGridHub;

    @Value("${BSSeleniumGrid}")
    private String BSSeleniumGridHub;

    @Value("${ZaleniumGrid}")
    private String ZaleniumGridHub;

    @Value("${HistoricalReport}")
    private String HistoricalReport;

    @Value("${EASE_placeholderPage}")
    private String authorHubBaseUrl;

}

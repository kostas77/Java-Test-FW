package com.elsevier.subsys.pages.tardis.locators;

import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.Button;
import com.elsevier.subsys.framework.controls.elements.Text;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

@Getter
public class ManuscriptDashboardStandalonePageLocators extends BasePage {

// TODO - Try stream API for this
//    public List<Text> testTileHeadings = Arrays.asList(referencesTileHeading, authorsTileHeading, trendingTileHeading);

    public String locManuscriptDashboardTiles = ".tiles-Tile__tile-wrapper";

    @FindBy(how = How.ID,using = "Tiles")
    public WebElement tilesWrapper;

    @FindBy(how = How.XPATH,using = "//div[contains(@class,'heading3')]")
    public WebElement pageHeading;

    @FindBy(how = How.CSS, using = "#Tile_references .tile-Generic__title")
    public Text txtReferencesTileHeading;

    @FindBy(how = How.CSS, using = ".tile-References__reference-row:nth-child(1)>div:nth-child(2)")
    public WebElement numberOfReferencesInTilePreview;

    @FindBy(how = How.CSS, using = ".tile-References__reference-row:nth-child(2)>div:nth-child(2)")
    public WebElement lessThan5YearsReferencesInTilePreview;

    @FindBy(how = How.CSS, using = "button[id='references-justification-view-optionsAll References']>div:nth-child(1)")
    public WebElement numberOfReferencesInTileDetail;

    @FindBy(how = How.CSS, using = "button[id='references-justification-view-optionsOlder than 5 years']>div:nth-child(1)")
    public WebElement lessThan5YearsReferencesInTileDetail;

    public String referencesTileDetailsTitleContainer = "div[class*='references-title-container']>div";
    public String listOfReferencesTileDetailsPaperTitle = "div[class*='container-title-links-authors']>div:nth-child(1)";
    public String listOfReferencesTileDetailsYears = "div[id^='references-data-year']";

    @FindBy(how = How.XPATH, using = "(//div[contains(text(),\"viapubMed\")])[1]")
    public WebElement firstPubMedLink;

    @FindBy(how = How.XPATH, using = "(//div[contains(text(),\"viapubMed\")])[1]/ancestor::div[contains(@class,'container-title-links-authors')]/div[1]")
    public WebElement titleOfTheFirstPaperWithPubMedLink;

    public String listOfReferencesTileDetails = "div[id^='references-data-year']";


    @FindBy(how = How.CSS, using = "#Tile_plagiarism .tile-Generic__title")
    public Text txtPlagiarismTileHeading;

    @FindBy(how = How.CSS, using = "#Tile_authors .tile-Generic__title")
    public Text txtAuthorsTileHeading;

    public String listOfAuthorsInAuthorTile = "#authors .tile-Authors__author";
    public String listOfAuthorsInAuthorTileWithLinks="#authors .tile-Authors__author button[id^=authors-Authors]";
    public String listOfAuthorsInAuthorTileDetails="div[id^='authors-author_detail']";
    public String listOfAuthorsInAuthorTileDetailsWithLinks="button[id^='authors-AuthorsDetail']";

    @FindBy(how = How.CSS,using = ".references-ReferencesSortingButton__divider>button")
    public Button btnReferencesDetailsSortByList;

    @FindBy(how = How.CSS,using = "#authors-expand-button>button")
    public Button btnAuthorsTileShowDetails;


    @FindBy(how = How.CSS, using = "#Tile_scope_match .tile-Generic__title")
    public Text txtScope_matchTileHeading;

    @FindBy(how = How.CSS, using = "#Tile_trending .tile-Generic__title")
    public Text txtTrendingTileHeading;

    @FindBy(how = How.CSS, using = "#Tile_originality .tile-Generic__title")
    public Text txtOriginalityTileHeading;

    @FindBy(how = How.ID,using = "button_plagiarism")
    public Button btnViewLatestReportPlagiarism;

    @FindBy(how = How.CSS,using = "#references-expand-button>button")
    public Button btnReferencesTileShowDetails;


    @FindBy(how = How.ID,using = "expand-button_scope_match")
    public Button btnScopeMatchTileShowDetails;

    @FindBy(how = How.ID,using = "expand-button_trending")
    public Button btnTrendingTileShowDetails;

    @FindBy(how = How.ID,using = "expand-button_originality")
    public Button btnOriginalityTileShowDetails;

    @FindBy(how = How.CSS,using = ".highcharts-root")
    public WebElement trendingTileGraphCollapsed;

    @FindBy(how = How.CSS,using = "#Tile_scope_match .generic-Score__wrapper")
    public Text txtScopeMatchTileScopeMatchScore;

    @FindBy(how = How.CSS,using = "#Tile_trending .generic-Score__wrapper")
    public Text txtTrendingTileTrendingScore;

    @FindBy(how = How.CSS,using = "#Tile_originality .generic-Score__wrapper")
    public Text txtOriginalityTileAuthorOriginalityScore;

    @FindBy(how = How.CSS,using = "#Tile-tile_references .tile-Generic__body")
    public Text txtReferencesTileBody;

    @FindBy(how = How.CSS,using = "#Tile-tile_plagiarism .tile-Generic__body")
    public Text txtPlagiarismTileBody;

    @FindBy(how = How.CSS,using = "#Tile-tile_authors .tile-Generic__body")
    public Text txtAuthorsTileBody;

}

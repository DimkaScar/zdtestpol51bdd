package BDD_test.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {
    WebDriver driver;

    @FindBy(className = "crayons-story__body")
    public List<WebElement> allPosts;
    @FindBy(css = ".crayons-story__title > a")
    public WebElement postTitles;
    @FindBy(xpath = "//div[contains(@class,'crayons-story__snippet')]")
    public WebElement snippet;

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements( this.driver, this);
    }
}

package BDD_test.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SinglePodcastPage {
    WebDriver driver;

    @FindBy(className = "record")
    public WebElement playPodcast;
    @FindBy(className = "status-message")
    public WebElement initializinig;
    @FindBy(xpath = "//img[contains(@class,'pause-butt')]")
    public WebElement pauseBut;

    public SinglePodcastPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}

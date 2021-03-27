package BDD_test.webtest;

import BDD_test.webtest.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import zdtestpol51.browserUtills.BaseDriver;

import java.util.List;



public class WebTestStepsDefinitions{

    WebDriver driver;
    WebDriverWait wait;
    String firstBlogTitle, firstCastTitle, searchingPhrase;
    MainPage mainPage;
    SingleBlogPage singleBlogPage;
    PodcastListPage podcastListPage;
    SinglePodcastPage singlePodcastPage;
    SearchResultPage searchResultPage;


    @Before
    public void setup(){
        driver = BaseDriver.setHeadlessDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("I go to devTo main page")
    public void i_go_to_devto_main_page() {
        mainPage = new MainPage(driver);
    }

    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        firstBlogTitle = mainPage.firstBlog.getText();
        singleBlogPage = mainPage.selectFirstBlog();
    }

    @Then("I should be redirected to blog page")
    public void i_should_be_redirected_to_blog_page() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));

        String blogTitleText = singleBlogPage.blogTitle.getText();
        System.out.println("to jest tex mojego bloga : " + blogTitleText);
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }

    @When("I go to Podcasts section")
    public void i_go_to_Podcasts_section() {
        mainPage.podcastSection.click();
    }
    @When("I click on first Podcast on the list")
    public void i_click_on_first_podcast_on_the_list() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
        podcastListPage = new PodcastListPage(driver);
        firstCastTitle = podcastListPage.firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast", "");
        podcastListPage.firstCast.click();
    }
    @When("I play the podcast")
    public void i_play_the_podcast() {
        wait.until(ExpectedConditions.titleContains(firstCastTitle));
        singlePodcastPage = new SinglePodcastPage(driver);
        singlePodcastPage.playPodcast.click();
    }
    @Then("Podcast Should be played")
    public void podcast_should_be_played()  {
        wait.until(ExpectedConditions.invisibilityOf(singlePodcastPage.initializinig));
        Boolean isPuaseButtonVisible = singlePodcastPage.pauseBut.isDisplayed();
        Assert.assertTrue(isPuaseButtonVisible);
    }

    @When("I search for {string} phrase")
    public void i_search_for_phrase(String phrase) {
        mainPage.searchBar.sendKeys(phrase);
        searchingPhrase = phrase;
        mainPage.searchBar.sendKeys(Keys.RETURN);
    }

    @Then("Top {int} blogs found should have correct phrase in title")
    public void top_blogs_found_should_have_correct_phrase_in_title(Integer int1){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title"))); //h3
        wait.until(ExpectedConditions.attributeContains(By.id("substories"),"class","search-results-loaded"));

        searchResultPage = new SearchResultPage(driver);

        if(searchResultPage.allPosts.size() >= int1){
            for (int i=0;i<int1;i++){
                WebElement singlePost = searchResultPage.allPosts.get(i);
                String singlePostTitleText = searchResultPage.postTitles.getText().toLowerCase(); // wyciagnij tekst z tytulu
                Boolean isPhraseInTitle = singlePostTitleText.contains(searchingPhrase);
                if(isPhraseInTitle){ // isPhraseInTitle == true
                    Assert.assertTrue(isPhraseInTitle);
                }
                else{
                    String snippetText = searchResultPage.snippet.getText().toLowerCase();
                    Boolean isPhraseInSnippet = snippetText.contains(searchingPhrase);
                    Assert.assertTrue(isPhraseInSnippet);
                }
            }
        }
    }
    @After
    public void tearDown(){         // standardowa metoda zamkniencia testów
        driver.quit();
    }
}



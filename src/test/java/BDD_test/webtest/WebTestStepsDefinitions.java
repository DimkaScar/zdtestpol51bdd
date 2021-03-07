package BDD_test.webtest;

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

import java.util.List;
import java.util.Locale;


public class WebTestStepsDefinitions{

    WebDriver driver;
    WebDriverWait wait;
    String firstBlogTitle, firstCastTitle, searchingPhrase;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("I go to devTo main page")
    public void i_go_to_devto_main_page() {

        driver.get("https://dev.to/");
    }

    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
       //driver.findElement(By.className("crayons-story__body")).click();
        WebElement firstBlog = driver.findElement(By.cssSelector("h2.crayons-story__title > a"));
        firstBlogTitle = firstBlog.getText();
        firstBlog.click();
    }

    @When("I go on Podcasts section")
    public void i_go_on_podcasts_section() {
        WebElement podcastSection = driver.findElement(By.linkText("Podcasts"));
        podcastSection.click();
    }
    @When("I click on first Podcast")
    public void i_click_on_first_podcast() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
        WebElement firstCast = driver.findElement(By.tagName("h3"));
        firstCastTitle = firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast", "");
        firstCast.click();
    }

    @When("I search for {string} phrase")
    public void i_search_for_phrase(String phrase) {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(phrase);
        searchingPhrase = phrase;
        searchBar.sendKeys(Keys.RETURN);
    }

    @Then("I should be redirected to blog page")
    public void i_should_be_redirected_to_blog_page() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));
        WebElement blogTitle = driver.findElement(By.tagName("h1"));
        String blogTitleText = blogTitle.getText();
        System.out.println("to jest tex mojego bloga : " + blogTitleText);
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }
    @Then("I should be redirected to Podcast page")
    public void i_should_be_redirected_to_podcast_page() {
        wait.until(ExpectedConditions.titleContains(firstCastTitle));
        WebElement castTitle = driver.findElement(By.tagName("h1"));
        String castTitleText = castTitle.getText();
        System.out.println("to jest tex mojego castu : " + castTitleText);
        Assert.assertEquals(firstCastTitle, castTitleText);
    }

    @Then("Top {int} blogs found should have correct phrase in title")
    public void top_blogs_found_should_have_correct_phrase_in_title(Integer int1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title")));
        wait.until(ExpectedConditions.attributeContains(By.id("substories"),"class","search-results-loaded"));
        List<WebElement> allPosts = driver.findElements(By.cssSelector(".crayons-story__title > a"));
        if (allPosts.size() >=1 ) {
            for (int i = 0; i < int1; i++) {
                WebElement singlePost = allPosts.get(i);
                String singlePostTitle = singlePost.getText().toLowerCase();  // to lowercase przeprowadza text do małych znaków
                Boolean isTestingInTitle = singlePostTitle.contains(searchingPhrase);
                Assert.assertTrue(isTestingInTitle);
            }
        }
    }

}


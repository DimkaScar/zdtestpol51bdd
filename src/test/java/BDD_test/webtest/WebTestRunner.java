package BDD_test.webtest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/BD_test.webtest"
)
public class WebTestRunner {

}

package BDD_test.calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatorStepsDefinitions {
        // calkulator - puste pole
        Calculator calculator;
        Integer result;
        Double resultF;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
    }
    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {
        result = calculator.addTwoNumbers(int1, int2);
    }

    @When("I subtract {int} and {int}")
    public void i_subtract_and(Integer int1, Integer int2) {
        result = calculator.subtractTwoNum(int1, int2);
    }

    @When("I multiply {int} and {int}")
    public void i_multiply_and(Integer int1, Integer int2) {
        result = calculator.multiplyTwoNum(int1, int2);
    }

    @When("I divide {double} and {double}")
    public void i_divide_and(Double int1, Double int2) {

        resultF = calculator.divideTwoNum(int1, int2);
    }

    @Then("I should get {int}")
    public void i_should_get(Integer int1) {
        // porównanie wyników - assercja(czy zwraca nam to co oczekiwaliśmy)
        Assert.assertEquals(result, int1);
    }
    @Then("Divide result is {double}")
    public void i_should_get(Double f1) {

        // porównanie wyników - assercja(czy zwraca nam to co oczekiwaliśmy)
        Assert.assertEquals(resultF, f1);
    }
}

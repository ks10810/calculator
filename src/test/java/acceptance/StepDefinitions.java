package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private String server = System.getProperty("calculator.url");

    private RestTemplate restTemplate = new RestTemplate();

    private int a, b;
    private String result;

    @Given("I have two numbers: {int} and {int}")
    public void iHaveTwoNumbersAnd(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Then("I receive {int} as a result")
    public void iReceiveAsAResult(int expectedResult) {
        assertEquals(expectedResult, Integer.parseInt(result));
    }

    @When("the calculator sums them")
    public void theCalculatorSumsThem() {

        String url = String.format("%s/sum?a=%s&b=%s", server, a, b);
        result = restTemplate.getForObject(url, String.class);
    }
}

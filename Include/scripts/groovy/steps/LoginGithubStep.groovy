package steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import pages.LoginPage

class LoginGithubStep {

    LoginPage login = new LoginPage()

    @Given("User is on GitHub login page")
    def openLoginPage() {
        login.open()
        login.waitPage()
    }

    @When("User enters username {string} and password {string}")
    def userLogin(String username, String password) {
        login.login(username, password)
    }

    @Then("System should behave as {string}")
    def verifyLogin(String expected) {

        expected = expected?.toLowerCase()

        boolean result

        switch (expected) {

            case "success":
                result = login.isLoginSuccess()
                assert result : "Expected SUCCESS login but user still on login/session page"
                break

            case "invalid_credential":
                result = login.isLoginFailed()
                assert result : "Expected FAILED login but system did not show failure state"
                break

            case "validation_error":
                result = login.isValidationErrorDisplayed()
                assert result : "Expected validation error but not displayed"
                break

            default:
                assert false : "Unknown expected result: " + expected
        }

        println("FINAL RESULT = " + result)
    }
}
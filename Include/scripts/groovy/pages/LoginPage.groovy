package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

class LoginPage {

    def open() {
        WebUI.openBrowser('')
        WebUI.maximizeWindow()
        WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login')
        WebUI.waitForPageLoad(10)
    }

    def waitPage() {
        WebUI.waitForElementVisible(
            findTestObject('github/login/input_username'),
            10
        )
    }

    def login(String username, String password) {

        WebUI.comment("===== LOGIN INPUT =====")
        WebUI.comment("USERNAME: " + username)
        WebUI.comment("PASSWORD: " + password)

        if (username?.trim()) {
            WebUI.setText(findTestObject('github/login/input_username'), username)
        }

        if (password?.trim()) {
            WebUI.setText(findTestObject('github/login/input_password'), password)
        }

        WebUI.click(findTestObject('github/login/input_commit'))

        WebUI.waitForPageLoad(10)
        WebUI.delay(1)
    }

    // =========================
    // SUCCESS CHECK
    // =========================
    def isLoginSuccess() {

        String url = WebUI.getUrl()

        WebUI.comment("===== LOGIN RESULT DEBUG =====")
        WebUI.comment("CURRENT URL: " + url)

        boolean isSession = url.contains("/session")
        boolean isLoginPage = url.contains("/login")

        // GitHub success = keluar dari login/session page
        boolean success = !(isSession || isLoginPage)

        WebUI.comment("IS SUCCESS: " + success)

        return success
    }

    // =========================
    // FAILURE CHECK
    // =========================
    def isLoginFailed() {

        String url = WebUI.getUrl()

        boolean onSession = url.contains("/session")
        boolean onLogin = url.contains("/login")

        WebUI.comment("FAILED CHECK URL: " + url)

        return onSession || onLogin
    }

    // =========================
    // VALIDATION ERROR (HTML5)
    // =========================
    def isValidationErrorDisplayed() {

        String usernameValidation = WebUI.getAttribute(
            findTestObject('github/login/input_username'),
            'validationMessage'
        )

        String passwordValidation = WebUI.getAttribute(
            findTestObject('github/login/input_password'),
            'validationMessage'
        )

        return (usernameValidation?.trim() || passwordValidation?.trim())
    }
}
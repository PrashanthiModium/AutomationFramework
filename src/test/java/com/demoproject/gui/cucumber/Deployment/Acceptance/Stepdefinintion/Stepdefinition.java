package com.demoproject.gui.cucumber.Deployment.Acceptance.Stepdefinintion;

import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.PageInstances;
import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.PageObjects.SignInPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class Stepdefinition extends PageInstances
{
    SignInPage signinpage = PageFactory.initElements(driver, SignInPage.class);

    @Then("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String element) throws Throwable {
        Assert.assertTrue(signinpage.clickOnElement(element));
    }
    @Then("^I click on \"([^\"]*)\" link$")
    public void iClickOnLink(String element) throws Throwable {
        Assert.assertTrue(signinpage.clickOnLink(element));
    }

    @Then("^I enter below fields in create an account page$")
    public void iEnterBelowFieldsInCreateAnAccountPage(DataTable fields) throws Throwable {
        Map<String, String> data = fields.asMap(String.class, String.class);
        signinpage.createAnAccountWithPersonalInfo("Create", data);
    }

    @And("^I enter emailID as \"([^\"]*)\" in email address textbox$")
    public void iEnterEmailIDAsInEmailAddressTextbox(String inputValue) throws Throwable {
        signinpage.enterMailID(inputValue);

    }

    @Then("^I validate \"([^\"]*)\" page should be displayed$")
    public void iValidatePageShouldBeDisplayed(String page) throws Throwable {
        Assert.assertTrue(signinpage.validateLandingPage(page));
    }

    @Then("^I validate FirstName as \"([^\"]*)\" and LastName as \"([^\"]*)\" are displayed$")
    public void iValidateFirstNameAsAndLastNameAsAreDisplayed(String first, String last) throws Throwable {
        signinpage.validatingUserNamenMyAccountPage(first,last);
    }

    @And("^I enter emailID in email address textbox$")
    public void iEnterEmailIDInEmailAddressTextbox() {
        signinpage.enterEmailID();
    }

    @And("^I enter Password as \"([^\"]*)\" in textbox$")
    public void iEnterPasswordAsInTextbox(String pwd) throws Throwable {
        signinpage.enterPassword(pwd);
    }

    @When("^I click on Add To Cart for the first product from list$")
    public void iClickOnAddToCartForTheFirstProductFromList()  {
        signinpage.clickOnAddToCartForFirstProduct();
    }

    @And("^I click on terms and condition checkbox in shipping tab$")
    public void iClickOnTermsAndConditionCheckboxInShippingTab() {
        signinpage.clickOnTermsOfServiceCheckbox();
    }

    @Then("^I validate \"([^\"]*)\" details are displayed in payment page$")
    public void iValidateDetailsAreDisplayedInPaymentPage(String arg0) throws Throwable {
        Assert.assertTrue(signinpage.validatingProductDetailsInPaymentPage());
    }

    @Then("^I validate product is displayed in summary page$")
    public void iValidateProductIsDisplayedInSummaryPage() {
        signinpage.validateProductInSummaryPage();
    }

    @Given("^I Verify \"([^\"]*)\" page is displayed$")
    public void iVerifyPageIsDisplayed(String arg0) throws Throwable {
        signinpage.verifyHomePage();
    }
}


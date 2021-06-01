package com.gui.cucumber.Stepdefinintion;
import com.gui.cucumber.Pages.Base.PageInstances;
import com.gui.cucumber.Pages.PageObjects.CalculatorPage;
import com.gui.cucumber.webdriverwait.WebDriverUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;


public class Stepdefinition extends PageInstances {
    public static String applicantType;

    @Given("^I am in \"([^\"]*)\" page$")
    public void iAmInPage(String barrowpage) {
        if (driver.findElement(CalculatorPage.calculatePageHeader).getText().contains(barrowpage)) {
            Assert.assertTrue("I am not in borrowing calculator page", true);
        } else {
            Assert.assertTrue("I am not in borrowing calculator page", false);
        }
    }

    @When("^I fill my details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iFillMyDetails(String applcntYype, int dependants, String purposeofbuy) throws Throwable {
        applicantType = applcntYype;
        CalculatorPage.selectApplicationtype(applcntYype);
        CalculatorPage.selectNoOfDependants(dependants);
        CalculatorPage.selectPurposeOfBuy(purposeofbuy);
    }

    @And("^I fill my earnings details \"([^\"]*)\" \"([^\"]*)\"$")
    public void iFillMyEarningsDetails(int income, int otherincome) throws Throwable {
        CalculatorPage.enterYourIncome(income);
        CalculatorPage.enterOtherIncome(otherincome);
        if (applicantType.equalsIgnoreCase("Joint")) {
            CalculatorPage.enterJointApplicantIncome(income);
            CalculatorPage.enterJointApplicantOtherIncome(otherincome);
        }
    }

    @And("^I fill my expenses \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iFillMyExpenses(int expense, int homeloanrepayment, int otherloanpayment, int commitements, int creditcardlimit) throws Throwable {
        CalculatorPage.enterLivingExpenses(expense);
        CalculatorPage.enterHomeLoanRepayment(homeloanrepayment);
        CalculatorPage.enterOtherLoanRepayment(otherloanpayment);
        CalculatorPage.enterOtherCommitments(commitements);
        CalculatorPage.enterTotalCreditCardLimit(creditcardlimit);
    }

    @And("^I click on work out borrow button$")
    public void iClickOnWorkOutBorrowButton() {
        driver.findElement(CalculatorPage.borrowButton).click();
        System.out.println("Clicked on Work out Borrow button");
    }

    @Then("^I should get borrowing estimate of \"([^\"]*)\"$")
    public void iShouldGetBorrowingEstimateOf(int estimate) throws Throwable {
        WebDriverUtils.waitForElementLoading(2);
        String est = driver.findElement(CalculatorPage.borrowAmount).getText().replace("$", "").replace(",", "");
        System.out.println("Estimation borrow: " + est);
        if (Integer.parseInt(est) == estimate) {
            System.out.println("Expected estimation: " + estimate + ", Actual estimation: " + est);
        } else {
            System.out.println("Expected estimation: " + estimate + ", Actual estimation: " + est);
            Assert.assertTrue("Borrowing estimation is wrong, Expected estimation: " + estimate + " Actual estimation: " + est, false);
        }
    }

    @And("^I click on start over button$")
    public void i_click_on_start_over_button() throws Throwable {
        driver.findElement(CalculatorPage.startOverButton).click();
        System.out.println("Clicked on Start over button");
    }

    @Then("^All the fields gets cleared$")
    public void all_the_fields_gets_cleared() throws Throwable {
        Assert.assertTrue("Application type field is cleared", driver.findElement(CalculatorPage.singleApplicantRadioButton).getAttribute("class").equalsIgnoreCase("selected"));
        Select dependantDropDownField = new Select(driver.findElement(CalculatorPage.noOfDependantsDropDown));
        Assert.assertTrue("No of dependants field is cleared", dependantDropDownField.getFirstSelectedOption().getText().equalsIgnoreCase("0"));
        Assert.assertTrue("Purpose of buy fields is cleared", driver.findElement(CalculatorPage.livingPurposeRadioButton).getAttribute("class").equalsIgnoreCase("selected"));
        Assert.assertTrue("Income field is cleared", driver.findElement(CalculatorPage.incomeField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Other Income field is cleared", driver.findElement(CalculatorPage.otherIncomeField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Joint Income field is displayed after start over", !(driver.findElement(CalculatorPage.jointApplicantIncomeField).isDisplayed()));
        Assert.assertTrue("Expense field is cleared", driver.findElement(CalculatorPage.livingExpenseField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Home loan repayment field is cleared", driver.findElement(CalculatorPage.homeLoanRepaymentField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Other loan repayment field is cleared", driver.findElement(CalculatorPage.otherLoanRepaymentField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Commitments field is cleared", driver.findElement(CalculatorPage.otherCommitmentsField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Total credit card limit field is cleared", driver.findElement(CalculatorPage.creditCardLimitsField).getAttribute("value").equalsIgnoreCase("0"));
        Assert.assertTrue("Work out borrow button is not displayed after clear", driver.findElement(CalculatorPage.borrowButton).isDisplayed());
        System.out.println("All the fields are cleared after clicking on start over button");
    }

    @And("^I fill only living expenses \"([^\"]*)\"$")
    public void i_fill_only_living_expenses_something(int expense) throws Throwable {
        CalculatorPage.enterLivingExpenses(expense);
    }

    @Then("^I should not get estimation and get valid proper \"([^\"]*)\"$")
    public void i_should_not_get_estimation_and_get_valid_proper(String errorMessage) throws Throwable {
        String errorMsg = driver.findElement(CalculatorPage.borrowErrorMessage).getText();
        if (errorMsg.equalsIgnoreCase(errorMessage)) {
            System.out.println("Displayed proper error message");
            System.out.println("Expected Message: " + errorMessage);
            System.out.println("Actual Message: " + errorMsg);
        } else {
            System.out.println("Proper error message is not displayed");
            System.out.println("Expected Message: " + errorMessage);
            System.out.println("Actual Message: " + errorMsg);
            Assert.assertTrue("Proper error message is not displayed, /n Expected Message: " + errorMessage + "/n Actual Message: " + errorMsg, false);
        }
    }
}



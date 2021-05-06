package com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.PageObjects;


import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.BasePage;
import com.demoproject.gui.cucumber.Deployment.Acceptance.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Random;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//div[@id='header_logo']/a/img")
    private WebElement yourLogo;
    @FindBy(xpath = "//a[@class='login']")
    private WebElement SignInButton;
    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private WebElement createAccount;
    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailAddress;
    @FindBy(xpath = "//input[@id='id_gender1']")
    private WebElement title;
    @FindBy(xpath = "//input[@id='customer_firstname']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@id='customer_lastname']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;
    @FindBy(xpath = "//div[@id='uniform-days']")
    private WebElement days;
    @FindBy(xpath = "//div[@id='uniform-months']")
    private WebElement months;
    @FindBy(xpath = "//div[@id='uniform-years']")
    private WebElement years;
    @FindBy(xpath = "//input[@name='address1']")
    private WebElement address;
    @FindBy(xpath = "//input[@name='city']")
    private WebElement city;
    @FindBy(xpath = "//div[@id='uniform-id_state']")
    private WebElement state;
    @FindBy(xpath = "//input[@id='postcode']")
    private WebElement postalCode;
    @FindBy(xpath = "//div[@id='uniform-id_country']")
    private WebElement country;
    @FindBy(xpath = "//input[@id='phone_mobile']")
    private WebElement phone;
    @FindBy(xpath = "//input[@id='alias']")
    private WebElement alias;
    @FindBy(xpath = "//button[@id='submitAccount']")
    private WebElement registerButton;
    @FindBy(xpath = "//span[text()='My account']")
    private WebElement myAccount;
    @FindBy(xpath = "//a[@class='account']/span")
    private WebElement userNameInMyAccountPage;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement signOutButton;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInSignInPage;
    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordInSignInPage;
    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement submitButton;
    @FindBy(xpath = "//div[@id='block_top_menu']//a[@title='Women']")
    private WebElement womenLink;
    @FindBy(xpath = "//ul[@class='product_list grid row']/li[1]/div")
    private WebElement firstProduct;
    @FindBy(xpath = "//ul[@class='product_list grid row']/li[1]//a[@title='Add to cart']")
    private WebElement addToCart;
    //    @FindBy(xpath = "//span[text()='Add to cart']")
//    private WebElement addToCart;
//    @FindBy(xpath = "//a[@title='Proceed to checkout']")
//    private WebElement proceedToCheckOut;
    @FindBy(xpath = "//a[@title='Proceed to checkout']/span")
    private WebElement proceedToCheckOutInPopUp;
    @FindBy(xpath = "//div[@class='order_delivery clearfix row']/..//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckOutInSummaryTab;
    @FindBy(xpath = "//button/span[contains(.,'Proceed to checkout')]")
    private WebElement proceedChkOutInAddressAndShippingTab;
    @FindBy(xpath = "//div[@id='uniform-cgv']/span")
    private WebElement termsOfServiceChkbox;
    @FindBy(xpath = "//span[@class='product-name']")
    private WebElement selectedProductName;
    //    @FindBy(xpath = "//span[@id='layer_cart_product_title']")
//    private WebElement selectedProductInPopup;
    @FindBy(xpath = "//table[@id='cart_summary']//p/a")
    private WebElement selectedProductNameInPaymentPage;
    @FindBy(xpath = "//td[@class='cart_description']/p[@class='product-name']/a")
    private WebElement productNameInSummaryPage;

    public static String randomMailId = "";
    public static String productName = "";
    JavascriptExecutor js = (JavascriptExecutor)driver;

    public SignInPage() throws Exception {
        super();
    }

    public boolean clickOnElement(String element) {
        WebDriverUtils.waitForElementLoading(2);
        switch (element.toLowerCase()) {
            case "sign in":
                js.executeScript("arguments[0].click();", SignInButton);
//                SignInButton.click();
                break;
            case "create an account":
                createAccount.click();
                break;
            case "register":
                registerButton.click();
                break;
            case "sign out":
                signOutButton.click();
                break;
            case "submit":
                submitButton.click();
                break;
            case "proceed to checkout in summary tab":
                proceedToCheckOutInSummaryTab.click();
                break;
            case "proceed to checkout in address tab":
                proceedChkOutInAddressAndShippingTab.click();
                break;
            case "proceed to checkout in shipping tab":
                proceedChkOutInAddressAndShippingTab.click();
                break;
        }
        return true;
    }

    public String enterMailID(String inputValue) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        randomMailId = inputValue + randomInt + "@helloo.com";
        emailAddress.sendKeys(randomMailId);
        System.out.println("Random mail id while creating account:" + randomMailId);
        return randomMailId;
    }

    public boolean createAnAccountWithPersonalInfo(String createAccount, Map<String, String> fields) {

        WebDriverUtils.waitForElementLoading(4);
        String applicationFieldName = "";

        switch (createAccount.toLowerCase()) {
            case "create":
                for (String key : fields.keySet()) {
                    switch (key.toLowerCase()) {
                        case "title":
                            title.click();
//                            driver.findElement(By.xpath("//input[@id="+fields.get(key)+"])"));
                            break;
                        case "first name":
                            firstName.sendKeys(fields.get(key));
                            break;
                        case "last name":
                            lastName.sendKeys(fields.get(key));
                            break;
                        case "password":
                            password.sendKeys(fields.get(key));
                            break;
                        case "days":
                            days.click();
                            Select select = new Select(driver.findElement(By.id("days")));
                            select.selectByValue(fields.get(key));
                            break;
                        case "month":
                            months.click();
                            Select month = new Select(driver.findElement(By.id("months")));
                            month.selectByValue(fields.get(key));
                            break;
                        case "year":
                            years.click();
                            Select year = new Select(driver.findElement(By.id("years")));
                            year.selectByValue(fields.get(key));
                            break;
                        case "address":
                            address.sendKeys(fields.get(key));
                            break;
                        case "city":
                            city.sendKeys(fields.get(key));
                            break;
                        case "state":
                            state.click();
                            Select state = new Select(driver.findElement(By.id("id_state")));
                            state.selectByVisibleText(fields.get(key));
                            break;
                        case "postal code":
                            postalCode.sendKeys(fields.get(key));
                            break;
                        case "country":
                            country.click();
                            Select country = new Select(driver.findElement(By.id("id_country")));
                            country.selectByVisibleText(fields.get(key));
                            break;
                        case "mobile phone":
                            phone.sendKeys(fields.get(key));
                            break;
                        case "assign an address":
                            alias.clear();
                            alias.sendKeys(fields.get(key));
                            break;
                        default:
                            return false;
                    }
                }
                break;

            default:
                return false;

        }
        return true;

    }

    public boolean validateLandingPage(String page) {
        boolean flag = false;
        if (myAccount.getText().equalsIgnoreCase(page)) {
            System.out.println(myAccount.getText() + " page is displayed");
            flag = true;
        }
        return flag;
    }

    public boolean validatingUserNamenMyAccountPage(String first, String last) {

        boolean flag = false;
        if (userNameInMyAccountPage.getText().equalsIgnoreCase(first + " " + last)) {
            System.out.println("User name is displayed in my account page:" + userNameInMyAccountPage.getText());
            flag = true;
        }
        return flag;
    }

    public void enterEmailID() {
        emailInSignInPage.sendKeys(randomMailId);
        System.out.println("Random mail id while sign in:" + randomMailId);
    }

    public void enterPassword(String pwd) {
        passwordInSignInPage.sendKeys(pwd);
    }

    public boolean clickOnLink(String link) {
        boolean flag = false;
        if (womenLink.getText().trim().equalsIgnoreCase(link)) {
            womenLink.click();
            flag = true;
        }
        return flag;
    }

    public void clickOnAddToCartForFirstProduct()  {
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProduct).click().build().perform();
        addToCart.click();
        js.executeScript("arguments[0].click();", proceedToCheckOutInPopUp);
    }

    public void clickOnTermsOfServiceCheckbox() {
        termsOfServiceChkbox.click();
    }

    public boolean validatingProductDetailsInPaymentPage() {
        boolean flag = false;
        System.out.println("Product in payment page is:"+selectedProductNameInPaymentPage.getText());
        if (selectedProductNameInPaymentPage.getText().equalsIgnoreCase(productName)) {
            System.out.println("Selected Prodcut is displayed in payment page");
            flag = true;
        }
        return flag;
    }

    public String validateProductInSummaryPage() {

        if(productNameInSummaryPage.isDisplayed()){
            productName=productNameInSummaryPage.getText();
            System.out.println("Product in summary:"+productName);
        }
        return productName;
    }

    public void verifyHomePage() {
        if(yourLogo.isDisplayed()){
            System.out.println("Home page is displayed");
        }
    }
}

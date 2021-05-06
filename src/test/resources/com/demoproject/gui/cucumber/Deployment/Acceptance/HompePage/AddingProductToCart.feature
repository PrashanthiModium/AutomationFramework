@Feature
Feature: verify the product details in cart

  @Test050521 @AddToCart
  Scenario: Register On webpage and add prodcut to the cart
    Given I Verify "your logo" page is displayed
    When I click on "Sign In" button
    And I enter emailID as "username" in email address textbox
    And I click on "Create an account" button
    And I enter below fields in create an account page
      | Title             | Mr.              |
      | First name        | abc              |
      | Last name         | xyz              |
      | Password          | password         |
      | days              | 10               |
      | month             | 2                |
      | year              | 2012             |
      | Address           | 123-4,abc colony |
      | City              | city             |
      | State             | Florida          |
      | Postal Code       | 12345            |
      | Country           | United States    |
      | Mobile phone      | 123456           |
      | Assign an address | 123456789        |
    And I click on "Register" button
    Then I validate "My account" page should be displayed
    Then I validate FirstName as "abc" and LastName as "xyz" are displayed
    And I click on "Sign Out" button
    And I click on "Sign In" button
    And I enter emailID in email address textbox
    And I enter Password as "password" in textbox
    And I click on "Submit" button
    And I click on "Women" link
    And I click on Add To Cart for the first product from list
    Then I validate product is displayed in summary page
    And I click on "Proceed to checkout In Summary Tab" button
    And I click on "Proceed to checkout In Address Tab" button
    And I click on terms and condition checkbox in shipping tab
    And I click on "Proceed to checkout In Shipping Tab" button
    Then I validate "selected product" details are displayed in payment page

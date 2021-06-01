# This feature file consists of Borrowing Calculator scenarios
# @Author: Prashanthi

@Calculate
Feature: Borrowing calculator

  @EstimateBorrow
  Scenario Outline: To estimate how much you may be able to borrow based on my current income and existing financial commitments
    Given I am in "<borrowing_calculator>" page
    When I fill my details "<Applicant_Type>" "<Dependants>" "<Purpose_Of_Buy>"
    And I fill my earnings details "<Income>" "<Other_Income>"
    And I fill my expenses "<Expense>" "<Home_Loan_Repayment>" "<Other_Loan_Payment>" "<Commitments>" "<CreditCard_Limit>"
    And I click on work out borrow button
    Then I should get borrowing estimate of "<Estimate>"

    Examples:
      | borrowing_calculator    | Applicant_Type | Dependants | Purpose_Of_Buy | Income | Other_Income | Expense | Home_Loan_Repayment | Other_Loan_Payment | Commitments | CreditCard_Limit | Estimate |
      | How much could I borrow | Single         | 0          | Live           | 80000  | 10000        | 500     | 0                   | 100                | 0           | 10000            | 470000   |

  @clear_all_loan_details
  Scenario Outline: To clear all the entered loan details
    Given I am in "<borrowing_calculator>" page
    When I fill my details "<Applicant_Type>" "<Dependants>" "<Purpose_Of_Buy>"
    And I fill my earnings details "<Income>" "<Other_Income>"
    And I fill my expenses "<Expense>" "<Home_Loan_Repayment>" "<Other_Loan_Payment>" "<Commitments>" "<CreditCard_Limit>"
    And I click on work out borrow button
    And I click on start over button
    Then All the fields gets cleared

    Examples:
      | borrowing_calculator    | Applicant_Type | Dependants | Purpose_Of_Buy | Income | Other_Income | Expense | Home_Loan_Repayment | Other_Loan_Payment | Commitments | CreditCard_Limit | Estimate |
      | How much could I borrow | Joint          | 2          | Investment     | 80000  | 10000        | 500     | 100                 | 100                | 100         | 10000            | 479000   |

  @livingExpenses
  Scenario Outline: To estimate borrow by filling only living expenses and leaving all other fields as zero
    Given I am in "<borrowing_calculator>" page
    And I fill only living expenses "<Expense>"
    And I click on work out borrow button
    Then I should not get estimation and get valid proper "<error_message>"

    Examples:
      | borrowing_calculator    | Expense | error_message                                                                                                                                                   |
      | How much could I borrow | 1       | Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500. |
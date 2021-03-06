Feature: basic calculator functions
  Scenario: adding two numbers
    Given I have a calculator
    When I add 2 and 3
    Then I should get 5

  Scenario:  subtractonion of two numbers
    Given I have a calculator
    When I subtract 7 and 3
    Then I should get 4

   Scenario: multiplication of two numbers
     Given I have a calculator
     When I multiply 3 and 5
     Then I should get 15

    Scenario: dividing two numbers
      Given I have a calculator
      When I divide 8 and 3
      Then Divide result is 2,666666666666667
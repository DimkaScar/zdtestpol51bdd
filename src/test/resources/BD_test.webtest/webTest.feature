Feature: devTo basic features
  Scenario: Open first seeing blog
    Given I go to devTo main page
    When I click on first blog displayed
    Then I should be redirected to blog page

  Scenario:  Open first podcast
    Given I go to devTo main page
    When I go on Podcasts section
    And I click on first Podcast
    Then I should be redirected to Podcast page

  Scenario: Search the testing phrase
    Given I go to devTo main page
    When I search for testing phrase
    Then Top 3 blogs found should have testing in title
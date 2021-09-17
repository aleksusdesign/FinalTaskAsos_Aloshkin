Feature: Filters
  As a user
  I want to test main filters functional
  So that I can be sure that filters works correctly

  Scenario Outline: Check list with price filter
    Given User opens '<homePage>' page
    And User clicks men floor button
    And User clicks shoes button
    And User clicks boots button
    And User clicks price range button
    When User drags 'Min price' indicator <170> points
    Then User checks that prices of products in list are in range

    Examples:
      | homePage             |
      | https://www.asos.com |

  Scenario Outline: Check sort by price low to high
    Given User opens '<homePage>' page
    And User clicks men floor button
    And User clicks shoes button
    And User clicks boots button
    And User checks sort button visible
    And User clicks sort button
    And User checks low to high button visible
    When User clicks low to high button
    Then User checks that prices of products in list are low to high

    Examples:
      | homePage             |
      | https://www.asos.com |

  Scenario Outline: Check sort by brands
    Given User opens '<homePage>' page
    And User clicks men floor button
    And User clicks shoes button
    And User clicks boots button
    And User checks brands button filter visible
    And User clicks 'Brands Sort' button
    And User checks 'Asos' and 'Base London' labels are visible
    When User clicks 'Asos' and 'Base London' labels
    Then User checks that descriptions of products contains brand`s name

    Examples:
      | homePage             |
      | https://www.asos.com |




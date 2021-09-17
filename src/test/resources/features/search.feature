Feature: Search
  As a user
  I want to test main search functional
  So that I can be sure that search works correctly

  Scenario Outline: Check page for 0 search results
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks that text of title are '<textOfTitle>'
    And User checks that 'Filters region' are invisible
    And User checks that 'Shop New in' section are visible
    Then User checks that 'Shop New in' section consist <4> articles

    Examples:
      | homePage             | keyword     | textOfTitle                 |
      | https://www.asos.com | qasdafdsdfs | NOTHING MATCHES YOUR SEARCH |

  Scenario Outline: Check are results contains keyword
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that results contains '<keyword>'

    Examples:
      | homePage             | keyword     |
      | https://www.asos.com | ok          |


  Scenario Outline: Check load more and load previous search results
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks that amount of products is <72>
    And User checks that 'Load More' button visibility
    And User clicks 'Load More' button
    Then User checks that amount of products is <144>
    When User reload a page
    And User checks that 'Load Previous' button visibility
    And User clicks 'Load Previous' button
    And User checks that amount of products is <144>
    Then User checks that current url contains 'page=2'

    Examples:
      | homePage             | keyword |
      | https://www.asos.com | shorts  |


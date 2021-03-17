Feature: Smoke
  As a user
  I want to test all main site functional
  So that I can be sure that site works correctly

  Scenario Outline: Check add product to wishlist
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks save on first product
    And User clicks on the saved items button
    Then User checks that amount of products in saved list are '<amountOfProducts>'

    Examples:
      | homePage             | keyword | amountOfProducts |
      | https://www.asos.com | shorts  | 1                |

  Scenario Outline: Check site main functions
    Given User opens '<homePage>' page
    And User checks that language switcher is '<languageSwitcher>'
    And User checks header visibility
    And User checks men and floor button visibility
    And User checks footer visibility
    When User clicks 'My Account' button
    And User checks my account popup visibility
    And User checks 'Sign In' and 'Sign Up' buttons visibility on my account popup
    And User checks cart visibility
    And User checks country selector visibility
    And User clicks country selector button
    And User checks country and currency select visibility
    And User select '<country>'
    Then User checks that current url contains '<country>'

    Examples:
      | homePage              | languageSwitcher | country |
      | https://www.asos.com  | Ukraine          | ES      |

  Scenario Outline: Check add product to cart
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks on the first product
    And User checks that title of product is visible
    And User checks that price is visible
    And User checks that 'Star Rating' button is visible
    And User checks that 'Add to Cart' button is visible
    And User clicks 'Add to Cart' button on product
    And User clicks 'Cart' button
    And User checks item in shopping cart visibility
    And User checks 'Checkout' button visibility
    When User clicks 'Checkout' button
    Then User checks that current url contains 'signin'

    Examples:
      | homePage             | keyword |
      | https://www.asos.com | Bagpack |

  Scenario Outline: Check sign in errors
    Given User opens '<homePage>' page
    When User clicks 'My Account' button
    And User checks my account popup visibility
    And User checks 'Sign In' and 'Sign Up' buttons visibility on my account popup
    And User clicks 'Sign In' popup button
    And User checks that email and password inputs are visible
    And User clicks 'Sign In' button
    And User checks that email error is visible
    And User checks that password error is visible
    And User insert '<incorrectEmail>' and '<password>'
    And User checks that email error is visible
    And User insert '<anotherEmail>' and '<password>'
    And User clicks 'Sign In' button
    Then User checks that login error is visible
    #success sign in on the asos.com is not supported for automation

    Examples:
      | homePage              | incorrectEmail | anotherEmail            | email                  | password               |
      | https://www.asos.com  | asdafsdfs      | aleksus201203@gmail.com |aleksus20103@gmail.com  | haveanicedayAnastasiia |
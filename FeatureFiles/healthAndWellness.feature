Feature: Health and Wellness

  @smoke
  Scenario Outline: verify if the submit button is disabled with incorrect details on the Health and Wellness Page
    Given user navigates to Health and Wellness Plan page
    When user inputs "<Name>" in name
    And user inputs "<Organization>" in organization name
    And user inputs "<Contact Number>" in Contact Number
    And user inputs "<Email>" in Official Email ID
    And user selects Organization size: "<Organization Size>"
    And user selects "<Interested in>" in Interested in
    Then verify Schedule a demo button is disabled

    Examples: 
      | Name      | Organization | Contact Number | Email            | Organization Size | Interested in |
      | Sri       | DemoOrg      |          86420 | name@website.com | 1001-5000         | Taking a demo |
      | Harsha     | DemoOrg      |     9876543310 | name@website     | <500              | Taking a demo |
      | Sriharsha | DemoOrg      |     9876553210 | name@site.com    | 5001-10000        |               |

  @smoke
  Scenario Outline: verify if submit button enabled with correct details on the Health and Wellness Plan page
    Given user navigates to Health and Wellness Plan page
    When user inputs "<Name>" in name
    And user inputs "<Organization>" in organization name
    And user inputs "<Contact Number>" in Contact Number
    And user inputs "<Email>" in Official Email ID
    And user selects Organization size: "<Organization Size>"
    And user selects "<Interested in>" in Interested in
    Then verify Schedule a demo button is enabled

    Examples: 
      | Name      | Organization | Contact Number | Email            | Organization Size | Interested in |
      | Sriharsha | DemoOrg      |     9876543210 | name@website.com | 501-1000          | Taking a demo |

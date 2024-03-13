Feature: Search

  @regression
  Scenario Outline: Filter doctors by patient stories on the Search page
    Given user clicks on "<Navigation Item>"
    When user selects location as "<Location>"
    And user inputs "<Specialization>" in search
    And fetch the number of doctors displayed
    And user filters by patient stories: "<Stories Count>"
    Then verify the number of doctors is changed

    Examples: 
      | Navigation Item | Stories Count       | Location | Specialization |
      | Find Doctors    | 80+ Patient Stories | Chennai  | Dentist        |

  @regression
  Scenario Outline: Filter doctors by fees on the Search page
    Given user clicks on "<Navigation Item>"
    When user selects location as "<Location>"
    And user inputs "<Specialization>" in search
    And user clicks on All filters
    And user selects: "<Fee range>" under fees
    Then verify if the listed doctors have the right fee requirement "<Fee range>"

    Examples: 
      | Navigation Item | Fee range | Location | Specialization |
      | Find Doctors    | 0-500     | Chennai  | Dentist        |
      | Find Doctors    | Above 500 | Chennai  | Dentist        |

  @regression
  Scenario Outline: Filter doctors by availability on the Search page
    Given user clicks on "<Navigation Item>"
    When user selects location as "<Location>"
    And user inputs "<Specialization>" in search
    And user clicks on All filters
    And user selects: "<Availability>" under availability
    Then verify if the availability displayed above book clinic visit matches "<Availability>"

    Examples: 
      | Navigation Item | Availability       | Location | Specialization |
      | Find Doctors    | Available Tomorrow | Chennai  | Dentist        |

  @regression
  Scenario Outline: Filter doctors by video consult availability on the Search page
    Given user clicks on "<Navigation Item>"
    When user selects location as "<Location>"
    And user inputs "<Specialization>" in search
    And user clicks on All filters
    And user selects "<Consult type>" under Consult type
    Then verify if results displayed are of video consult

    Examples: 
      | Navigation Item | Consult type  | Location | Specialization |
      | Find Doctors    | Video Consult | Chennai  | Dentist        |

  @regression
  Scenario Outline: Filter doctors location in Chennai on the Search page
    Given user clicks on "<Navigation Item>"
    When user selects location as "<Location>"
    And user inputs "<Specialization>" in search
    And user clicks "<Area>" under location
    Then verify if the area is selected: "<Message>"

    Examples: 
      | Navigation Item | Location | Specialization | Area       | Message                                                      |
      | Find Doctors    | Chennai  | Dentist        | Anna Nagar | You are seeing results from anna nagar. See results near you |

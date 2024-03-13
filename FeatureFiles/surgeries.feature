Feature: Surgeries

  @smoke
  Scenario Outline: Verify consultation form submission with correct details on the Surgeries page
    Given user navigates to Surgeries from home page
    When user selects "<City>" from the city dropdown on surgeries
    And user selects "<Ailment>" from the ailment dropdown
    And user inputs "<Name>" in name on surgeries
    And user inputs "<Contact Number>" in Contact Number on surgeries
    And click Book Appointment button
    Then capture mobile number in otp popup
    
    Examples:
      | City      | Ailment                | Contact Number | Name  |
      | Bangalore | Carpal Tunnel Syndrome |     9879879877 | Harsha   |

  @smoke
  Scenario Outline: Verify consultation form submission with wrong name on the Surgeries page
    Given user navigates to Surgeries from home page
    When user selects "<City>" from the city dropdown on surgeries
    And user selects "<Ailment>" from the ailment dropdown
    And user inputs "<Name>" in name on surgeries
    And user inputs "<Contact Number>" in Contact Number on surgeries
    Then capture error message displayed below name
    
    Examples:
      | City    | Ailment    | Contact Number | Name |
      | Chennai | ACL Repair |     8123123123 | US   |

  @smoke
  Scenario Outline: Verify consultation form submission with wrong contact number on the Surgeries page
    Given user navigates to Surgeries from home page
    When user selects "<City>" from the city dropdown on surgeries
    And user selects "<Ailment>" from the ailment dropdown
    And user inputs "<Name>" in name on surgeries
    And user inputs "<Contact Number>" in Contact Number on surgeries
    Then capture error message displayed below contact number
    
    Examples:
      | City      | Ailment                | Contact Number | Name  |
      | Bangalore | Carpal Tunnel Syndrome |     1231231231 | SRI   |
      | Chennai   | Carpal Tunnel Syndrome |      898989898 | HARSHA |

  @smoke
  Scenario Outline: Verify consultation form submission with wrong name and contact number on the Surgeries page
    Given user navigates to Surgeries from home page
    When user selects "<City>" from the city dropdown on surgeries
    And user selects "<Ailment>" from the ailment dropdown
    And user inputs "<Name>" in name on surgeries
    And user inputs "<Contact Number>" in Contact Number on surgeries
    Then capture error message displayed below name
    Then capture error message displayed below contact number
    
    Examples:
      | City      | Ailment                | Contact Number | Name |
      | Bangalore | Carpal Tunnel Syndrome |     1231231231 | SR   |

  @regression
  Scenario Outline: Verify surgeries form popup message on the Surgeries page
    Given user navigates to Surgeries from home page
    When user clicks on "<Surgery Name>" under Popular Surgeries
    Then verify the message displayed above the surgery form contains "<Surgery Name>"
    
    Examples:
      | Surgery Name |
      | Kidney Stone |

  @regression
  Scenario Outline: Verify surgeries form submission with correct details on the Surgeries page
    Given user navigates to Surgeries from home page
    When user clicks on "<Surgery Name>" under Popular Surgeries
    And enter "<Name>" in name in popular surgery form
    And enter "<Contact Number>" in contact number in popular surgery form
    And select "<City>" in the city dropdown in popular surgery form
    And user clicks on Book Appointment button in popular surgery form
    Then capture mobile number in otp popup
    
    Examples:
      | Surgery Name | Name | Contact Number | City      |
      | Cataract     | Sri  |     9879879877 | Bangalore |

  @regression
  Scenario Outline: Verify surgeries form submission with incorrect details on the Surgeries page
    Given user navigates to Surgeries from home page
    When user clicks on "<Surgery Name>" under Popular Surgeries
    And enter "<Name>" in name in popular surgery form
    And enter "<Contact Number>" in contact number in popular surgery form
    And select "<City>" in the city dropdown in popular surgery form
    Then capture error message displayed below name
    Then capture error message displayed below contact number
    
    Examples:
      | Surgery Name | Name | Contact Number | City      |
      | Cataract     | Sr   |       98798798 | Bangalore |

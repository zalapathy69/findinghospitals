Feature: Home Page

  @smoke
  Scenario Outline: Verify navigation items on the Home Page
    Given user opens practo home page
    When user clicks on "<Navigation Item>"
    Then verify page title "<Page Title>"

    Examples: 
      | Navigation Item | Page Title                                                                                   |
      | Find Doctors    | Practo \| Book Doctor Appointments Online, Order Medicine, Diagnostic Tests, Consult         |
      | Video Consult   | Online Doctor Consultation \| Ask Top Doctor's Advice 24*7 \| Practo                         |
      | Medicines       | Buy Medicines,Health Products Online \| India's Most Reliable Online Medical Store \| Practo |
      | Surgeries       | Practo Care Surgeries \| End to end care from top surgeons in your city                      |
      | Lab Tests       | Blood Tests \| Book Diagnostic Tests from Home at Best Prices \| Practo                      |

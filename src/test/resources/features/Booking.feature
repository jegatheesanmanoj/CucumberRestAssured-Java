@restful-booker
Feature: restful-booker API Functionality

  @Registrationtest @Sanity @Regression
  Scenario: Booking - CreateBooking
    Given Prepare booking request with the necessary details
    |firstname|lastname|totalprice|depositpaid|checkin|checkout|
    |Dummyname|Dummyname|1000|500|10-05-2025|12-05-2025|
    When User post the booking request
    Then User should be able to receive "200" response code
 
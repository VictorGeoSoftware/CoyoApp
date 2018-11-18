Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, retrieve user information without favourite store, and go to Store view
    Given a user launch the app for first time
    When profiles list is requested
    Then list is fulfilled



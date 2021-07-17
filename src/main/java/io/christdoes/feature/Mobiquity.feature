@UATTesting

Feature: For this task, imagine you are a part of the team that performs quality assurance for a user blog,
  the frontend design is not yet developed, but the API has already been published here:

  Scenario: Search for a user
    When Search for the user with username “Delphine”.
    And Use the details fetched to make a search for the posts written by the user.
    And For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
    Then Think of various scenarios for the test workflow, all the things that can go wrong. Add them to test coverage

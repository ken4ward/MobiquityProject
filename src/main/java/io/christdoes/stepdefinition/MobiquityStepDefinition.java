package io.christdoes.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MobiquityStepDefinition {
    @When("^Search for the user with username “Delphine”\\.$")
    public void searchForTheUserWithUsernameDelphine() {
    }

    @And("^Use the details fetched to make a search for the posts written by the user\\.$")
    public void useTheDetailsFetchedToMakeASearchForThePostsWrittenByTheUser() {
    }

    @And("^For each post, fetch the comments and validate if the emails in the comment section are in the proper format\\.$")
    public void forEachPostFetchTheCommentsAndValidateIfTheEmailsInTheCommentSectionAreInTheProperFormat() {
    }

    @And("^Think of various scenarios for the test workflow, all the things that can go wrong\\. Add them to test coverage$")
    public void thinkOfVariousScenariosForTheTestWorkflowAllTheThingsThatCanGoWrongAddThemToTestCoverage() {
    }

    @Then("^teardown test as it is completed\\.$")
    public void teardownTestAsItIsCompleted() {
    }
}

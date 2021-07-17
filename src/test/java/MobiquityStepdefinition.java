import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.christdoes.test.testcases.TestCases;

public class MobiquityStepdefinition {
    @When("^Search for the user with username “Delphine”\\.$")
    public void searchForTheUserWithUsernameDelphine() throws Throwable {
       TestCases.searchForUsername();
    }

    @And("^Use the details fetched to make a search for the posts written by the user\\.$")
    public void useTheDetailsFetchedToMakeASearchForThePostsWrittenByTheUser() throws Throwable {
        TestCases.searchForUserPost();
    }

    @And("^For each post, fetch the comments and validate if the emails in the comment section are in the proper format\\.$")
    public void forEachPostFetchTheCommentsAndValidateIfTheEmailsInTheCommentSectionAreInTheProperFormat() throws Throwable {
        TestCases.searchForUserCommentinnPostAndVlidateEmail();
    }

    @Then("^Think of various scenarios for the test workflow, all the things that can go wrong\\. Add them to test coverage$")
    public void thinkOfVariousScenariosForTheTestWorkflowAllTheThingsThatCanGoWrongAddThemToTestCoverage() throws Throwable {
        TestCases.otherScenariosThatCouldGoWrong();
    }
}

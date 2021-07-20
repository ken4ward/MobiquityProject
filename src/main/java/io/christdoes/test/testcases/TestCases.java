package io.christdoes.test.testcases;

import io.christdoes.test.init.Init;
import io.christdoes.utility.MyLogger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.christdoes.test.init.Init.getProperties;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TestCases extends MyLogger {
    static int userId;
   static java.util.List<Integer> idList;

    @Test(priority = 1)
    public static void searchForUsername( ) throws Throwable {
        Response response = RestAssured.given().queryParam(getProperties().getProperty("project.query"), getProperties().getProperty("project.username"))
                .get(Init.initProperties() +getProperties().getProperty("users.pathURL"));
        response.then().contentType(ContentType.JSON).statusCode(200).log().all();
        userId = (Integer) response.jsonPath().getList("id").get(0);
        System.out.println("This is the user ID" +userId);
    }

    /***
     * This method gets the userId from the previous mehthod of the user and used it to query the post URL
     * and returns the total number of posts made by the user whose userId is passed to the URL. the returned list is stored in
     * idList.
     * @throws Throwable
     */
    @Test( priority = 2)
    public static void searchForUserPost( ) throws Throwable {
        Response response = myRequest().get(Init.initProperties() + getProperties().getProperty("post.pathURL") + userId);
        // Get Response Body
        ResponseBody body = response.getBody();
        String bodyStringValue = body.asString();
        idList = response.jsonPath().getList("id");
        MyLogger.info("This is the user post IDs" +idList);
        response.then().contentType(ContentType.JSON).statusCode(200).log().all();
        MyLogger.info("Response returns a valid code of  " +response.statusCode());
        Assert.assertTrue(bodyStringValue.contains("id"));
        MyLogger.info("It validates the IDs of the post are returned" +bodyStringValue.contains("id"));
    }

    /****
     * This method gets the list of post ids returned from the previous method and query the comment API getting all the
     * comments that has been made on each post. it as well validate that all emails attached to the returned body are valid
     * emails using Matcher class.
     * @throws Throwable
     */
    @Test( priority = 3)
    public static void searchForUserCommentinnPostAndVlidateEmail( ) throws Throwable {
        for ( Integer e: idList) {
            MyLogger.info("Iteration through the post IDs passed " + e);
            Response response = myRequest().get( Init.initProperties()+getProperties().getProperty("user.comment.pathURL") +e);
            List<String> email = response.jsonPath().getList("email");
            MyLogger.info( "The emails are retrieved using the IDs" + email );
            for (String s: email) {
                MyLogger.info("email validations are carried out here " +s);
                Assert.assertTrue(validate(s));
            }
        }
    }

    /***
     * This method queries the
     * @throws Throwable
     */
    @Test(priority = 4)
    public static void otherScenariosThatCouldGoWrong() throws Throwable {
        Response response = myRequest().get(Init.initProperties() + getProperties().getProperty("user.todo.pathURL") + userId);
        // Get Response Body
        ResponseBody body = response.getBody();
        String bodyStringValue = body.asString();
        idList = response.jsonPath().getList("title");
        Assert.assertTrue(bodyStringValue.contains("title"));
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static RequestSpecification myRequest(){
        RequestSpecification httpRequest = given();
        httpRequest.header("Content-Type", "application/json");
        return httpRequest;
    }

    public static void negativeTestCases(){

    }

}

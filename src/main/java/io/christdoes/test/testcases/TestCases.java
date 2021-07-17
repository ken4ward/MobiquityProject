package io.christdoes.test.testcases;

import io.christdoes.test.init.Init;
import io.restassured.RestAssured;
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

public class TestCases {
    static int userId;
   static List<Integer> idList;

   /**
    * This method is to check for the username @Delphine, passed it as a query parameter and pass the userId to the next method
    * to query the posts user @Delphine has made. the user id is stored in @userId.
    * **/
    @Test(priority = 1)
    public static void searchForUsername( ) throws Throwable {
        Response res = RestAssured.given().queryParam(getProperties().getProperty("project.query"), getProperties().getProperty("project.username"))
                .get(Init.initProperties() +getProperties().getProperty("users.pathURL"));
         userId = (Integer) res.jsonPath().getList("id").get(0);
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
        System.out.println("This is the user post IDs" +idList);
        Assert.assertTrue(bodyStringValue.contains("id"));
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
            Response response = myRequest().get( Init.initProperties()+getProperties().getProperty("user.comment.pathURL") +e);
            List<String> email = response.jsonPath().getList("email");
            for (String s: email) {
                System.out.println("This validates email " +s);
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

}

package io.christdoes.test.testcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.christdoes.pojo.comments.CommentsItem;
import io.christdoes.pojo.posts.PostsItem;
import io.christdoes.pojo.todo.Todo;
import io.christdoes.pojo.todo.TodoItem;
import io.christdoes.pojo.users.UserItem;
import io.christdoes.test.init.Init;
import io.christdoes.utility.MyLogger;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.christdoes.test.init.Init.getProperties;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;

public class TestCases extends MyLogger {
    static int userId;
    static java.util.List<Integer> idList;

    @Test(priority = 1)
    public static void searchForUsername( ) throws Throwable {
        Response response = RestAssured.given().queryParam(getProperties().getProperty("project.query"), getProperties().getProperty("project.username"))
                .get(Init.initProperties() +getProperties().getProperty("users.pathURL"));
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/users.json"))
                .body(getProperties().getProperty("project.query"), equalTo(Arrays.asList(getProperties().getProperty("project.username"))))
                .contentType(ContentType.JSON).statusCode(200).extract().as(UserItem[].class);
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.time( Matchers.both( Matchers.greaterThanOrEqualTo(1000L)).and( Matchers.lessThanOrEqualTo(2000L)) );
        List<UserItem> userItems = response.as(new TypeRef<>() {});
        for ( UserItem u: userItems ) {
            if ( (Integer) response.jsonPath().getList("id").get(0) == u.getId() ){
                userId = u.getId();
                Assert.assertTrue(u.getAddress().getGeo().getLat().matches(floatMatch));
                MyLogger.debug("Assertion for info " +u.getAddress().getGeo().getLat().matches(floatMatch) );
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
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
        idList = response.jsonPath().getList("id");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/posts.json")).contentType(ContentType.JSON).statusCode(200).extract().as(PostsItem[].class);
        List<PostsItem> postsItems = response.as(new TypeRef<>() {});
        for ( PostsItem p : postsItems) {
            Assert.assertTrue(p.getUserId() == userId);
            MyLogger.info( "" +p.getBody().matches( anyString ) );
        }
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
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/comments.json")).contentType(ContentType.JSON).statusCode(200).extract().as(CommentsItem[].class);;
            List<CommentsItem> commentsItems = response.as(new TypeRef<>() {});
            MyLogger.info( "The emails are retrieved using the IDs" + commentsItems );
            for (CommentsItem s: commentsItems) {
                MyLogger.info("email validations are carried out here " +s.getEmail());
                Assert.assertTrue(validate(s.getEmail()));
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
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/todos.json")).contentType(ContentType.JSON).statusCode(200).extract().as(TodoItem[].class);
        List<TodoItem> todoItems = response.as(new TypeRef<>() {});
        Assert.assertNotNull(response.body());
        for (TodoItem s: todoItems) {
            Assert.assertTrue(s.getUserId() == userId);
            MyLogger.info(s.toString());
        }
    }

    static  String emailMatch = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    static String floatMatch = "^([+-]?\\d*\\.?\\d*)$";
    static String anyString = "[a-z]+";

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( emailMatch, Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_ANY_SENTENCE_ADDRESS_REGEX = Pattern.compile( anyString, Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean matchSentence(String sentence) {
        Matcher matcher = VALID_ANY_SENTENCE_ADDRESS_REGEX.matcher(sentence);
        return matcher.find();
    }

    private static RequestSpecification myRequest(){
        RequestSpecification httpRequest = given();
        httpRequest.header("Content-Type", "application/json");
        return httpRequest;
    }

}

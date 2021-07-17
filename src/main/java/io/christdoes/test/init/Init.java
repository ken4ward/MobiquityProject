package io.christdoes.test.init;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.christdoes.utility.PropertiesFileReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.Properties;

public class Init {
    static RequestSpecBuilder requestSpecBuilder;

    static PropertiesFileReader propertiesFileReader = new PropertiesFileReader();

    public static Properties getProperties() throws Throwable {
        Properties properties = propertiesFileReader.getProperties();
        return properties;
    }

    @Before
    public static String initProperties() throws Throwable {
         return RestAssured.baseURI = getProperties().getProperty("base.baseURL");
    }

}

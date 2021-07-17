package io.christdoes.testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.christdoes.utility.PropertiesFileReader;
import org.testng.annotations.*;

import java.util.Properties;

@CucumberOptions(
        features = "src/main/java/io/christdoes/feature/Mobiquity.feature",
        glue = {"io.christdoes.stepdefinition"},
        tags = {"@UATTesting"},
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        monochrome = true )
public class TestRunner {
    private static TestNGCucumberRunner testNGCucumberRunner;
    static PropertiesFileReader propertiesFileReader = new PropertiesFileReader();

    public TestRunner(){}

    @BeforeClass( alwaysRun=true )
    public void setUpClass( ) throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    public static Properties getProperties() throws Throwable {
        Properties properties = propertiesFileReader.getProperties();
        return properties;
    }

    @Test( dataProvider = "features")
    public static void feature(CucumberFeatureWrapper cucumberFeatureWrapper) throws Throwable {
        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true )
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}

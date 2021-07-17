package io.christdoes.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load( new FileInputStream("src/main/resources/unique-property.properties") );
        } catch (Exception e) {
            System.out.println(e);
        }
        return properties;
    }
}

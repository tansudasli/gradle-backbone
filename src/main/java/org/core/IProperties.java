package org.core;

import java.io.*;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * Reads application.properties as config file, then
 * loads it.
 * <p>
 * - Leverages Properties <p>
 * - then, .class.getClassLoader.getResourceAsStream(file)
 */
public class IProperties {

    //read app.properties
    private final static String file = "application.properties";
    private final static Properties properties = new Properties();

    private IProperties() {}

    public static Supplier<Properties> of = () -> {
        try {

            properties.load(IProperties.class.getClassLoader().getResourceAsStream(file));

        } catch (IOException e) { throw new RuntimeException(e); }

        return properties;
    };

}

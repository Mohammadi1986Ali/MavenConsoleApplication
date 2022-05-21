package com.javalab.tutorial;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ApplicationTest {
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationTest.class);
    private static Properties PROPERTIES = new Properties();

    @BeforeAll
    public static void setup() {
        LOGGER.info("Setting test environment");
        try (InputStream resource = Application.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            LOGGER.info("Error loading application properties", e);
        }
    }

    @Test
    void givenApplicationProperties_whenGettingProjectVersion_thenItMustEqualToArtifactVersion() {
        LOGGER.info("ApplicationTest#givenApplicationProperties_whenGettingProjectVersion_thenItMustEqualToArtifactVersion");
        LOGGER.info("Project version: {}", PROPERTIES.getProperty("project.version"));
        Assertions.assertThat(PROPERTIES.getProperty("project.version")).contains("SNAPSHOT");
    }

}
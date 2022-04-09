package com.example.safetynetalert.tests.bdd;

import com.example.safetynetalert.SafetyNetAlertApplication;
import com.example.safetynetalert.core.domain.persons.command.PersonAggregatorRepository;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest {

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ContextConfiguration(classes = {SafetyNetAlertApplication.class,
        RestTemplateConfig.class}, loader = SpringBootContextLoader.class)
    public static class CucumberSpringContextConfiguration {

        @Autowired
        private PersonAggregatorRepository repository;


        private void resetDatabase() {

        }

        @Before
        public void cleanUp() {
            resetDatabase();
        }
    }

    @Configuration
    public static class RestTemplateConfig {

        @Bean
        public RestTemplate restTemplate(Environment environment) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setUriTemplateHandler(new LocalHostUriTemplateHandler(environment));
            restTemplate.setErrorHandler(new NoOpResponseErrorHandler());
            return restTemplate;
        }
    }

    private static class NoOpResponseErrorHandler
        extends DefaultResponseErrorHandler {

        @Override
        public void handleError(ClientHttpResponse response) {
        }
    }
}

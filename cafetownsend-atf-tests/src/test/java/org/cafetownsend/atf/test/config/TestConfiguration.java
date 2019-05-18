package org.cafetownsend.atf.test.config;

import org.cafetownsend.atf.config.BaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@Import(BaseConfiguration.class)
public class TestConfiguration {
}

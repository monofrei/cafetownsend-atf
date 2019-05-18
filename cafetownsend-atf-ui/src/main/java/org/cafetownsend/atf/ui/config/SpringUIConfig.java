package org.cafetownsend.atf.ui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("ui-application.properties")
@ComponentScan("org.cafetownsend.atf.ui")
public class SpringUIConfig {

}

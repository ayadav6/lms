package edu.depaul.cdm.se452.discussionforum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("discussion-forum")
                .pathsToMatch("/api/**")
                .build();
    }
}

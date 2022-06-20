package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class ApplicationConfig extends AbstractReactiveMongoConfiguration {
    
    @Override
    protected String getDatabaseName(){
        return "Quiz";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo();
    }
}
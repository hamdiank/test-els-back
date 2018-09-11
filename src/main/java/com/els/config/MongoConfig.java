package com.els.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {
	@Autowired
	AppProperties appProperties;

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), appProperties.getDbName());
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongtemplate = new MongoTemplate(mongoDbFactory());
		return mongtemplate;
	}
}

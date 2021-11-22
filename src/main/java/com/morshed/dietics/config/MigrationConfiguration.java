package com.morshed.dietics.config;

import com.github.mongobee.Mongobee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MigrationConfiguration {

  private Environment environment;
  private MongoTemplate mongoTemplate;

  public MigrationConfiguration(Environment environment, MongoTemplate mongoTemplate) {
    this.environment = environment;
    this.mongoTemplate = mongoTemplate;
  }

  @Bean
  public Mongobee mongobee(){
    String mongoUri = environment.getProperty("spring.data.mongodb.uri");
    Mongobee runner = new Mongobee(mongoUri);
    runner.setEnabled(true);
    runner.setChangeLogsScanPackage("com.morshed.dietics.migration");
    runner.setChangelogCollectionName("migrations");
    runner.setLockCollectionName("migration_lock");
    runner.setMongoTemplate(mongoTemplate);
    return runner;
  }
}

package com.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
public class AppConfig {

  /*
   * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
   */
	@Bean
   public MongoClient mongoClient() {
	   
//   	CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
//       CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClientOptions mco = MongoClientOptions.builder()
				.writeConcern(WriteConcern.ACKNOWLEDGED)
				.connectionsPerHost(100)
				.threadsAllowedToBlockForConnectionMultiplier(5)
				.maxWaitTime(120000).connectTimeout(10000).build();

	   MongoClient client = new MongoClient(new ServerAddress("120.78.154.33", 27022), mco);
       return client;
   }
}

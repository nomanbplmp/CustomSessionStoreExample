package com.nk.test.spring.db;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
@EntityScan(value="com.nk.test.spring.db")
@EnableJpaRepositories(
	    basePackages = "com.nk.test.spring.db", 
	    entityManagerFactoryRef = "entityManagerFactory", 
	    transactionManagerRef = "transactionManager"
	)
public class DBConfig {
	
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	  public DataSource mySqlDataSource() {
	    return DataSourceBuilder.create().build();
	  }
	
	@Bean(name = "entityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  entityManagerFactory(EntityManagerFactoryBuilder builder,
	    DataSource dataSource
	  ) {

		HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.id.new_generator_mappings",
          "false");
        
		return
	      builder
	        .dataSource(dataSource)
	        .packages("com.nk.test.spring.dbconfig")
	        .persistenceUnit("item").properties(properties)
	        .build();
	  }
	@Primary
	@Bean
	  public EntityManagerFactory transactionManager(
	    @Qualifier("entityManagerFactory") EntityManagerFactory 	emf
	  ) {
		  
	    return new JpaTransactionManager(emf).getEntityManagerFactory();
	  }
}

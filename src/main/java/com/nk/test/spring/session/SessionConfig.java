package com.nk.test.spring.session;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;




@EnableJdbcHttpSession
class SessionConfig { 
	
	
	@Bean("secondary")
	@ConfigurationProperties(prefix="h2.datasource")
	public DataSource  h2DataSource(DataSourceProperties properties){
		return DataSourceBuilder.create().url(properties.getUrl()).username(properties.getUsername()).
				  password(properties.getPassword()).driverClassName(properties.getDriverClassName()).build();
	}
	
	
	
	
	@Bean
	public JdbcOperationsSessionRepository sessionRepository(@Qualifier("secondary") DataSource ds){
	 return	  new SessionRepo(ds,new DataSourceTransactionManager(ds));
	 
	}
}


class SessionRepo extends JdbcOperationsSessionRepository  {

	public SessionRepo(DataSource dataSource, PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
		
	}
	
	
}


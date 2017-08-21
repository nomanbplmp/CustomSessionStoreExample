package com.nk.test.spring.session;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJdbcHttpSession
class SessionConfig { 
	@Bean
	public JdbcOperationsSessionRepository sessionRepository(){
	  DataSource ds =	DataSourceBuilder.create().driverClassName("org.h2.Driver").username("sa").url("jdbc:h2:file:~/test").build();
	 return	  new SessionRepo(ds,new DataSourceTransactionManager(ds));
	 
	}
}


class SessionRepo extends JdbcOperationsSessionRepository  {

	public SessionRepo(DataSource dataSource, PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
		
	}
	
	
}


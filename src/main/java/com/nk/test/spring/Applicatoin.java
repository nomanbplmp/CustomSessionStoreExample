package com.nk.test.spring;

import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nk.test.spring.db.Item;
import com.nk.test.spring.db.ItemRepository;



@SpringBootApplication
@ComponentScan("com.nk.test.spring")
public class Applicatoin {
	
	public static void main(String[] args) {
		SpringApplication.run(Applicatoin.class, args);
	}
	
	  
	
}


@RestController
@RequestMapping("/")
class HelloController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(HttpServletRequest req) {
		HttpSession session =  req.getSession(true);
	   System.out.println(session.getId());
	   itemRepository.save(new Item("item"+new Random().nextInt(100)));
	
	  return "session id : "  +session.getId();
	}
	
	
}

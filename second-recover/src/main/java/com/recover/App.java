package com.recover;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {
	private static Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) {
    	PropertyConfigurator.configure("log4j.properties");
    	SpringApplication.run(App.class, args);
    	log.info("启动 RECOVER REST");
    }
}

package com.stackroute.keepnote.config;


import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.NoteDAOImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.*;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/*This class will contain the application-context for the application.
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * */
@Configuration
@EnableTransactionManagement
//@EnableAspectJAutoProxy
public class ApplicationContextConfig {



	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */

	@Bean
	public DataSource dataSource() throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/NotesDB");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Root@123");

        return basicDataSource;
	}


	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */


@Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException, IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.stackroute.keepnote.model");

        Properties properties = new Properties();
     //   properties.put("hibernate.show_sql", "true");
    //  properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.afterPropertiesSet();

        return localSessionFactoryBean;


    }



	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */


    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }


}

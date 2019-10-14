package com.stackroute.keepnote.config;

import com.stackroute.keepnote.controller.NoteController;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.NoteDAOImpl;
import com.stackroute.keepnote.model.Note;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.lang.InstantiationException;
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
public class ApplicationContextConfig {



	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */


	@Bean
	public DataSource dataSource() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//		String driver = "com.mysql.cj.jdbc.Driver";
//		Class.forName(driver).newInstance();
//		String url = "jdbc:mysql://localhost:3306/users";
//		String user = "root";
//		String password = "Root@123";
//		Connection myConn = DriverManager.getConnection(url,user,password);
//		String query = "SELECT * FROM Student";
//		Statement myStmt = myConn.createStatement();
//		// PreparedStatement myStmt = myConn.prepareStatement("SELECT * FROM Student");
//		ResultSet rs = myStmt.executeQuery(query);
//
//		while (rs.next()){
//			System.out.println(rs.getString("sno") + " "+ rs.getString("sname")+" "+rs.getString("marks"));
//		}
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/NotesDB");
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("Root@123");

		return basicDataSource;

	}


	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */
	//	org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
//	SessionFactory sessionFactory = configuration.buildSessionFactory();
//	Session session = sessionFactory.getCurrentSession();


	LocalSessionFactoryBean localSessionFactoryBean =new LocalSessionFactoryBean();

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource){

		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setPackagesToScan("com.stackroute.keepnote.model");
		Properties pt = new Properties();
		pt.setProperty("dialect","org.hibernate.dialect.MySQLDialect");
		localSessionFactoryBean.setHibernateProperties(pt);

		return localSessionFactoryBean.getObject();
//		return new SessionFactory() {
//			@Override
//			public SessionFactoryOptions getSessionFactoryOptions() {
//				return null;
//			}
//
//			@Override
//			public SessionBuilder withOptions() {
//				return null;
//			}
//
//			@Override
//			public Session openSession() throws HibernateException {
//				return null;
//			}
//
//			@Override
//			public Session getCurrentSession() throws HibernateException {
//				return null;
//			}
//
//			@Override
//			public StatelessSessionBuilder withStatelessOptions() {
//				return null;
//			}
//
//			@Override
//			public StatelessSession openStatelessSession() {
//				return null;
//			}
//
//			@Override
//			public StatelessSession openStatelessSession(Connection connection) {
//				return null;
//			}
//
//			@Override
//			public Statistics getStatistics() {
//				return null;
//			}
//
//			@Override
//			public void close() throws HibernateException {
//
//			}
//
//			@Override
//			public boolean isClosed() {
//				return false;
//			}
//
//			@Override
//			public Cache getCache() {
//				return null;
//			}
//
//			@Override
//			public Set getDefinedFilterNames() {
//				return null;
//			}
//
//			@Override
//			public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
//				return null;
//			}
//
//			@Override
//			public boolean containsFetchProfileDefinition(String name) {
//				return false;
//			}
//
//			@Override
//			public TypeHelper getTypeHelper() {
//				return null;
//			}
//
//			@Override
//			public ClassMetadata getClassMetadata(Class entityClass) {
//				return null;
//			}
//
//			@Override
//			public ClassMetadata getClassMetadata(String entityName) {
//				return null;
//			}
//
//			@Override
//			public CollectionMetadata getCollectionMetadata(String roleName) {
//				return null;
//			}
//
//			@Override
//			public Map<String, ClassMetadata> getAllClassMetadata() {
//				return null;
//			}
//
//			@Override
//			public Map getAllCollectionMetadata() {
//				return null;
//			}
//
//			@Override
//			public Reference getReference() throws NamingException {
//				return null;
//			}
//
//			@Override
//			public <T> List<EntityGraph<? super T>> findEntityGraphsByType(Class<T> entityClass) {
//				return null;
//			}
//
//			@Override
//			public Metamodel getMetamodel() {
//				return null;
//			}
//
//			@Override
//			public EntityManager createEntityManager() {
//				return null;
//			}
//
//			@Override
//			public EntityManager createEntityManager(Map map) {
//				return null;
//			}
//
//			@Override
//			public EntityManager createEntityManager(SynchronizationType synchronizationType) {
//				return null;
//			}
//
//			@Override
//			public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
//				return null;
//			}
//
//			@Override
//			public CriteriaBuilder getCriteriaBuilder() {
//				return null;
//			}
//
//			@Override
//			public boolean isOpen() {
//				return false;
//			}
//
//			@Override
//			public Map<String, Object> getProperties() {
//				return null;
//			}
//
//			@Override
//			public PersistenceUnitUtil getPersistenceUnitUtil() {
//				return null;
//			}
//
//			@Override
//			public void addNamedQuery(String name, javax.persistence.Query query) {
//
//			}
//
//			@Override
//			public <T> T unwrap(Class<T> cls) {
//				return null;
//			}
//
//			@Override
//			public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
//
//			}
//		};

	}


	@Bean
	public NoteDAO noteDAOImpl(){
		return new NoteDAOImpl(localSessionFactoryBean.getObject());
	}


	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */






}

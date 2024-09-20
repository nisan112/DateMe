package reproject.Configurations;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.DateMe.DAO.userDAO;
import com.DateMe.Service.FriendRequestService;
import com.DateMe.Service.UserPersonalService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.DateMe.Models")


public class BeansConfiguration {
	@Bean
	public userDAO getUserDAO(SessionFactory sf) {
		userDAO userdao = new userDAO(sf);
		return userdao;
	}
	
	@Bean
	public UserPersonalService getuserpersonalservice(userDAO userdao) {
		UserPersonalService ups = new UserPersonalService(userdao);
		return ups;
	}
	 @Bean
	    public DataSource dataSource() {
		 HikariConfig hikariConfig = new HikariConfig();
	        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/hibo");
	        hikariConfig.setUsername("root");
	        hikariConfig.setPassword("");
	        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        hikariConfig.setMaximumPoolSize(10);
	        hikariConfig.setMinimumIdle(5);
	        hikariConfig.setIdleTimeout(30000);
	        hikariConfig.setConnectionTimeout(30000);
	        hikariConfig.setPoolName("HikariCP-Pool");

	        return new HikariDataSource(hikariConfig);
	    }
	 @Bean
	 public Properties getProperties() {
		 Properties properties = new Properties();
		 properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		 properties.put("hibernate.show_sql", "true");
		    properties.put("hibernate.format_sql", "true"); // Optional for better readability
		    properties.put("hibernate.use_sql_comments", "true"); // Optional for SQL comments
		    properties.put("hibernate.hbm2ddl.auto", "update");
		    return properties;
	 }
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource datasource) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(datasource);
		sf.setPackagesToScan("com.DateMe.Models");
		sf.setHibernateProperties(getProperties());
		
		return sf;
	}
	@Bean
	public PlatformTransactionManager getPlatformTransactionManager(SessionFactory sessionfactory) {
		HibernateTransactionManager transactionmanager = new HibernateTransactionManager();
		transactionmanager.setSessionFactory(sessionfactory);
		return transactionmanager;
	}
	@Bean
	public FriendRequestService getfriendrequestservice(userDAO userdao) {
		return new FriendRequestService(userdao);
	}
	
}

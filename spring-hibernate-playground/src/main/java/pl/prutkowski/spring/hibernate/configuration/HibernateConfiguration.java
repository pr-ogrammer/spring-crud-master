package pl.prutkowski.spring.hibernate.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by programmer on 10/7/16.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"pl.prutkowski.spring.hibernate.configuration"})
@PropertySource({"classpath:application.properties"})
public class HibernateConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(generateDataSource());
        sessionFactoryBean.setPackagesToScan("pl.prutkowski.spring.hibernate.model");
        sessionFactoryBean.setHibernateProperties(generateProperties());
        return sessionFactoryBean;
    }

    @Bean
    public DataSource generateDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverclassname"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    public Properties generateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}

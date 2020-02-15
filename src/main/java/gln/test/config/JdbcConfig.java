package gln.test.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:config/datasource.properties")
@EnableTransactionManagement
public class JdbcConfig {
    @Value("${spring.datasource.username}")
    private String dataSourceUser;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceDriver;

    @Value("${spring.datasource.schema}")
    private String dataSourceSchema;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${hibernate.c3p0.min_size}")
    private String hibernateC3p0MinSize;

    @Value("${hibernate.c3p0.max_size}")
    private String hibernateC3p0MaxSize;

    @Value("${hibernate.c3p0.timeout}")
    private String hibernateC3p0Timout;

    @Value("${hibernate.c3p0.max_statements}")
    private String hibernateC3p0MaxStmts;

    @Value("${hibernate.c3p0.idle_test_period}")
    private String hibernateC3p0IdleTestPeriod;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUser);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean setSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("gln.test.*");
        return factoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.c3p0.min_size", hibernateC3p0MinSize);
        properties.put("hibernate.c3p0.max_size", hibernateC3p0MaxSize);
        properties.put("hibernate.c3p0.timeout", hibernateC3p0Timout);
        properties.put("hibernate.c3p0.max_statements", hibernateC3p0MaxStmts);
        properties.put("hibernate.c3p0.idle_test_period", hibernateC3p0IdleTestPeriod);
        properties.put("hibernate.default_schema", dataSourceSchema);
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}

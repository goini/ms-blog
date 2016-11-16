package com.ms.configuration;

import com.ms.interceptor.HibernateInterceptor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.*;


/**
 * Created by mshemet on 1/11/2016.
 */
@Configuration
public class HibernateConfig {

    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(defaultIfBlank(url, "jdbc:mysql://localhost:3306/ms_blog?createDatabaseIfNotExist=true&serverTimezone=UTC"));
        dataSource.setUsername(defaultIfBlank(user, "root"));
        dataSource.setPassword(defaultIfBlank(password, "password"));
        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        //properties.put("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Autowired
    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource, HibernateInterceptor interceptor) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.scanPackages("com.ms.entity");
        sessionBuilder.setInterceptor(interceptor);
        return sessionBuilder.buildSessionFactory();
    }

//    @Autowired
//    @Bean
//    public HibernateTransactionManager getTransactionManager(
//            SessionFactory sessionFactory) {
//        return new HibernateTransactionManager(sessionFactory);
//    }

    @Autowired
    @Bean
    public JpaTransactionManager getTransactionManager(
            LocalContainerEntityManagerFactoryBean managerFactoryBean) {
        return new JpaTransactionManager(managerFactoryBean.getObject());
    }

    @Autowired
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.ms.entity");
        lef.setJpaProperties(getHibernateProperties());
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }
}

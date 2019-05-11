package com.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author ：Yimyl
 * @date ：Created in 2019/4/27 22:40
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryGym",
        transactionManagerRef="transactionManagerGym",
        basePackages= {"com.repositoryGym"}) //设置Repository所在位置
public class GymDataSourceConfig {
    @Autowired
    @Qualifier("gymDataSource")
    private DataSource gymDataSource;

    @Bean(name = "entityManagerFactoryGym")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryGym() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(gymDataSource);
        em.setPackagesToScan(new String[] { "com.modelGym" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "transactionManagerGym")
    public PlatformTransactionManager transactionManagerGym(DataSource gymDataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryGym().getObject());

        return transactionManager;
    }


}

package com.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

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
        entityManagerFactoryRef = "entityManagerFactoryUser",
        transactionManagerRef = "transactionManagerUser",
        basePackages = {"com.repositoryUser"}) //设置Repository所在位置
public class UserDataSourceConfig {

    @Autowired
    @Qualifier("userDataSource")
    private DataSource userDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerUser")
    public EntityManager entityManagerUser(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryUser(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryUser")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUser(EntityManagerFactoryBuilder builder) {
        Map<String, String> properties = jpaProperties.getProperties();
//        properties.forEach((k,v)->log.info("key:"+k+" ##### "+"value"+v));
//        log.info("userrrrrrrrrrrrrrr"+jpaProperties.getDatabasePlatform());
        return builder
                .dataSource(userDataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.modelUser") //设置实体类所在位置
                .persistenceUnit("UserPersistenceUnit")
                .build();
    }


    @Primary
    @Bean(name = "transactionManagerUser")
//    @Resource
    public PlatformTransactionManager transactionManagerUser(DataSource userDataSource) {

        return new DataSourceTransactionManager(userDataSource);
    }

}

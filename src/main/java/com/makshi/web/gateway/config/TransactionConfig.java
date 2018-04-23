package com.makshi.web.gateway.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Configuration
public class TransactionConfig {
    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        properties.setProperty("load*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
        properties.setProperty("*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager, properties);

        return tsi;
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}

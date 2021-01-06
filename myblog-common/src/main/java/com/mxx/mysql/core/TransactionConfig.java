package com.mxx.mysql.core;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;


@Getter
@Setter
@Aspect
@EnableAspectJAutoProxy
@Configuration
@ConfigurationProperties(prefix="mysql.core")
@PropertySource("classpath:mysql-core-jdbc.properties")
public class TransactionConfig {
    private String txScanPackage;

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
    @Bean("mysqlTxAdvice")
    public TransactionInterceptor mysqlTxAdvice(@Qualifier("dataSourceTransactionManager") DataSourceTransactionManager dataSourceTransactionManager){
        DefaultTransactionAttribute defAttr =  new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        DefaultTransactionAttribute queryAttr = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        queryAttr.setReadOnly(true);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("save*",defAttr);
        source.addTransactionalMethod("insert*", defAttr);
        source.addTransactionalMethod("delete*", defAttr);
        source.addTransactionalMethod("update*", defAttr);
        source.addTransactionalMethod("exec*", defAttr);
        source.addTransactionalMethod("set*", defAttr);
        source.addTransactionalMethod("add*", defAttr);
        source.addTransactionalMethod("get*", queryAttr);
        source.addTransactionalMethod("query*", queryAttr);
        source.addTransactionalMethod("find*", queryAttr);
        source.addTransactionalMethod("list*", queryAttr);
        source.addTransactionalMethod("count*", queryAttr);
        source.addTransactionalMethod("is*", queryAttr);

        return new TransactionInterceptor(dataSourceTransactionManager,source);
    }

    @Bean("txAdvisor")
    public Advisor txAdvisor(@Qualifier("mysqlTxAdvice") TransactionInterceptor interceptor){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(this.getTxScanPackage());
        return new DefaultPointcutAdvisor(pointcut,interceptor);
    }

}

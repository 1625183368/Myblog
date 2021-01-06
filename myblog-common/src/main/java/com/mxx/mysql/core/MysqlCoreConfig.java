package com.mxx.mysql.core;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mysql.core")
@PropertySource("classpath:mysql-core-jdbc.properties")
@MapperScan(basePackages = "com.mxx.models.mappers",sqlSessionFactoryRef = "")
public class MysqlCoreConfig {
    private String jdbcUsername;
    private String jdbcPassword;
    private String jdbcDriver;
    private String jdbcUrl;
    private String rootMapper;
    private String aliasesPackage;

    @Bean("mysqlDataSource")
    public DataSource mysqlDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.getJdbcDriver());
        dataSource.setUrl(this.getJdbcUrl());
        dataSource.setUsername(this.getJdbcUsername());
        dataSource.setPassword(this.getJdbcPassword());
        dataSource.setMaxActive(50);
        dataSource.setMinIdle(5);
        return dataSource;
    }

    @Bean("mysqlSqlSessionfactory")
    public SqlSessionFactoryBean mysqlSqlSessionfactory(@Qualifier("mysqlDataSource") DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(this.getAliasesPackage());
        org.apache.ibatis.session.Configuration configuration= new org.apache.ibatis.session.Configuration();
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }

    public String getMapperFilePath(){
        return new StringBuffer("classpath:").append(this.getRootMapper()).append("/**/*.xml").toString();
    }
}

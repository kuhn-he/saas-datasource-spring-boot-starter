package com.github.kuhn_he.saas.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spel.DynamicDataSourceSpelParser;
import com.baomidou.dynamic.datasource.spel.DynamicDataSourceSpelResolver;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidDynamicDataSourceConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureAfter(DynamicDataSourceAutoConfiguration.class)
@Import(DruidDynamicDataSourceConfiguration.class)
public class DynamicDataSourcePlusAutoConfiguration {
	
	@Autowired
    private DataSource dynamicRoutingDataSource;
	@Autowired
	private DynamicDataSourcePlusProvider dynamicDataSourceProvider;
	
	@Bean
    @ConditionalOnMissingBean
    public DynamicDataSourcePlusAnnotationAdvisor dynamicDataSourcePlusAnnotationAdvisor(DynamicDataSourceSpelParser dynamicDataSourceSpelParser, DynamicDataSourceSpelResolver dynamicDataSourceSpelResolver,DynamicDataSourceProperties properties) {
        DynamicDataSourcePlusAnnotationInterceptor interceptor = new DynamicDataSourcePlusAnnotationInterceptor();
        DynamicDataSourcePlusAnnotationAdvisor advisor = new DynamicDataSourcePlusAnnotationAdvisor(interceptor);
        interceptor.setDynamicDataSourceSpelParser(dynamicDataSourceSpelParser);
        interceptor.setDynamicDataSourceSpelResolver(dynamicDataSourceSpelResolver);
        interceptor.setDynamicDataSourceProvider(dynamicDataSourceProvider);
        interceptor.setDynamicRoutingDataSource((DynamicRoutingDataSource)dynamicRoutingDataSource);
        advisor.setOrder(properties.getOrder());
        return advisor;
    }
	
	@Bean
	@ConditionalOnMissingBean
	public DynamicDataSourcePlusCreator dynamicDataSourcePlusCreator(DynamicDataSourceProperties properties){
		return new DynamicDataSourcePlusCreator(properties);
	}
}

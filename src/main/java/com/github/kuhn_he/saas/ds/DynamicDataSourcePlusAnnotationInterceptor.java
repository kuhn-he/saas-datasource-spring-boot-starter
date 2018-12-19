package com.github.kuhn_he.saas.ds;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.dynamic.datasource.DynamicDataSourceClassResolver;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spel.DynamicDataSourceSpelParser;
import com.baomidou.dynamic.datasource.spel.DynamicDataSourceSpelResolver;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.github.kuhn_he.saas.ds.annotation.SAAS;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源AOP核心拦截器
 *
 * @author TaoYu
 * @since 1.2.0
 */
@Slf4j
public class DynamicDataSourcePlusAnnotationInterceptor implements MethodInterceptor {

	@Setter
    private DynamicDataSourcePlusProvider dynamicDataSourceProvider;
    
	@Setter
    private DynamicRoutingDataSource dynamicRoutingDataSource;
    
    @Setter
    private DynamicDataSourceSpelResolver dynamicDataSourceSpelResolver;

    @Setter
    private DynamicDataSourceSpelParser dynamicDataSourceSpelParser;

    private DynamicDataSourceClassResolver dynamicDataSourceClassResolver = new DynamicDataSourceClassResolver();
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
        	String headerKey=determineDatasource(invocation);
        	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        	String dbKey=request.getHeader(headerKey);
        	
        	if(StringUtils.isNotBlank(dbKey)){
        		switchDatasource(dbKey);
                DynamicDataSourceContextHolder.setDataSourceLookupKey(dbKey);
        	}
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceLookupKey();
        }
    }
    
    /**
     * 获取数据源key
     * @param invocation
     * @return
     * @throws Throwable
     */
    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> declaringClass = dynamicDataSourceClassResolver.targetClass(invocation);
        SAAS ds = method.isAnnotationPresent(SAAS.class) ? method.getAnnotation(SAAS.class)
                : AnnotationUtils.findAnnotation(declaringClass, SAAS.class);
        String value = ds.value();
        return value;
    }

    /**
     * 安装数据源
     * @param dbKey
     * @throws Throwable
     */
    private void switchDatasource(String dbKey) {
    	Map<String,DataSource> dsMap=dynamicRoutingDataSource.getCurrentDataSources();
    	if(dsMap!=null && dsMap.containsKey(dbKey)){
    		return;
    	}
    	
    	//生成数据源
    	DataSource ds=dynamicDataSourceProvider.createDataSource(dbKey);
    	dynamicRoutingDataSource.addDataSource(dbKey, ds);;
    }
}
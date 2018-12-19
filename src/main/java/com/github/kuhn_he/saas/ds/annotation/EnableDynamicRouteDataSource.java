package com.github.kuhn_he.saas.ds.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.github.kuhn_he.saas.ds.DynamicDataSourcePlusAutoConfiguration;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DynamicDataSourcePlusAutoConfiguration.class})
public @interface EnableDynamicRouteDataSource {

}

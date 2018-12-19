package com.github.kuhn_he.saas.ds;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.github.kuhn_he.saas.ds.annotation.SAAS;

import lombok.NonNull;

/**
 * 动态数据源AOP织入
 *
 * @author TaoYu
 * @since 1.2.0
 */
public class DynamicDataSourcePlusAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;

    private Pointcut pointcut;

    public DynamicDataSourcePlusAnnotationAdvisor(@NonNull DynamicDataSourcePlusAnnotationInterceptor dynamicDataSourceAnnotationInterceptor) {
        this.advice = dynamicDataSourceAnnotationInterceptor;
        this.pointcut = buildPointcut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut() {
        Pointcut cpc = new AnnotationMatchingPointcut(SAAS.class, true);
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(SAAS.class);
        return new ComposablePointcut(cpc).union(mpc);
    }
}

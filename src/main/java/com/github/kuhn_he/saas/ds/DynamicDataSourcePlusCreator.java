package com.github.kuhn_he.saas.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;

@Component
public class DynamicDataSourcePlusCreator{

	@Autowired
	 private DynamicDataSourceProperties properties;
	
	/**
     * 创建DRUID数据源
     *
     * @param dataSourceProperty 数据源参数
     * @return 数据源
     */
    public DataSource createDruidDataSource(DataSourceProperty dataSourceProperty) {
    	DynamicDataSourceCreator dynamicDataSourceCreator=new DynamicDataSourceCreator();
    	dynamicDataSourceCreator.setDruidGlobalConfig(properties.getDruid());
    	dataSourceProperty.setDruid(properties.getDruid());
        return dynamicDataSourceCreator.createDruidDataSource(dataSourceProperty);
    }
}

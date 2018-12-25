# saas-datasource-spring-boot-starter
spring boot starter for Software-as-a-Service datasource base on mybatis-plus(https://github.com/baomidou/mybatis-plus)

### How to publish saas spring boot starter

+ add Dependencies:

```xml
<dependency>
    <groupId>com.github.kuhn-he</groupId>
    <artifactId>saas-datasource-spring-boot-starter</artifactId>
    <version>3.0.6.3</version>
</dependency>
```

+ add saas datasource configuration in application.properties, demo:

```
spring.autoconfigure.exclude=com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
spring.datasource.dynamic.primary=mysql
spring.datasource.dynamic.datasource.mysql.url = jdbc:mysql://127.0.0.1:3306/saas?characterEncoding=utf-8
spring.datasource.dynamic.datasource.mysql.username = root
spring.datasource.dynamic.datasource.mysql.password = root
spring.datasource.dynamic.datasource.mysql.driver-class-name = com.mysql.jdbc.Driver
```

### How to switch business datasources

+ custom dynamic datasource provider for switch business datasources, demo:

```java
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.github.kuhn_he.saas.ds.DynamicDataSourcePlusCreator;
import com.github.kuhn_he.saas.ds.DynamicDataSourcePlusProvider;

@Component
public class SampleDynamicDataSourceProvider implements DynamicDataSourcePlusProvider {

	@Autowired
	private DynamicDataSourcePlusCreator dynamicDataSourcePlusCreator;
	
	@Override
	public DataSource createDataSource(String dsKey) {
		//...
		DataSourceProperty dataSourceProperty=new DataSourceProperty();
		//...
		return dynamicDataSourcePlusCreator.createDruidDataSource(dataSourceProperty);
	}
}
```

+ then add @EnableDynamicRouteDataSource on Spring Boot Application, indicates that saas is enabled.

```java
import com.github.kuhn_he.saas.ds.annotation.EnableDynamicRouteDataSource;

@SpringBootApplication
@EnableDynamicRouteDataSource
public class ApplicationBootstrap {
 //...
}
```

+ code your database Mapper, add @SAAS(import com.github.kuhn_he.mapper.SAAS) on your Mapper interface(non-annotation @SAAS is default datasource which is saas datasouce configuration in application.properties).

```java
import com.github.kuhn_he.saas.ds.annotation.SAAS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@SAAS //default is x-datasource-key which datasource key put in header or session. 
@Mapper
public interface SampleMapper extends BaseMapper<TSample>{
 //...
}
```

### Reference

sample see: [saas-springboot-sample](https://github.com/kuhn-he/saas-datasource-spring-boot-starter/tree/master/saas-springboot-sample)
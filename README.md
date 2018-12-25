# saas-datasource-spring-boot-starter
spring boot starter for Software-as-a-Service datasource base on mybatis-plus(https://github.com/baomidou/mybatis-plus)

-----------------------------------------------------------------------------------------------------------------------

### How to publish saas spring boot starter

+ add Dependencies:

```
<dependency>
    <groupId>com.github.kuhn-he</groupId>
    <artifactId>saas-datasource-spring-boot-starter</artifactId>
    <version>3.0.6.2</version>
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
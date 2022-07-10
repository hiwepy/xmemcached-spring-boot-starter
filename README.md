# xmemcached-spring-boot-starter

Spring Boot Starter For Memcached 2.x

### 组件简介

Memcached 是一个高性能的分布式内存对象缓存系统

> 基于 Xmemcached 2.x 的 Spring Boot Starter 实现

官方网站：http://memcached.org/

学习教程：https://www.runoob.com/Memcached/Memcached-tutorial.html

网络资料：http://www.javashuo.com/article/p-kliwumfk-kb.html

### 使用说明

##### 1、Spring Boot 项目添加 Maven 依赖

``` xml
<dependency>
	<groupId>com.github.hiwepy</groupId>
	<artifactId>xmemcached-spring-boot-starter</artifactId>
	<version>${project.version}</version>
</dependency>
```

##### 2、在`application.yml`文件中增加如下配置

```yaml
################################################################################################################
###xmemcached基本配置：
################################################################################################################
spring:
  memcached:
    addresses: 101.35.55.147:11211
    weights: 100
    connection-pool-size: 8
    connect-timeout: 60s
    failure-mode: true
    sanitize-keys: false
    op-timeout: 5s
    enable-heal-session: true
    heal-session-interval: 2000
    resolve-inet-addresses: true
```

##### 3、使用示例

```java

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.Counter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XmemcachedApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootApplication
@Slf4j
public class XmemcachedApplicationTests {

    @Autowired
    private XmemcachedOperationTemplate memcachedOperation;

    @Test
    public void testCounter() throws Exception {
        Counter counter = memcachedOperation.counter("test");
        log.info("counter++ : {}", counter.incrementAndGet());
    }

    @Test
    public void testIncr() throws Exception {
        long counter = memcachedOperation.incr("test2", 12);
        log.info("counter incr : {}", counter);
    }

    @Test
    public void testDecr() throws Exception {
        long counter = memcachedOperation.decr("test2", 10);
        log.info("counter decr : {}", counter);
    }

    public static void main(String[] args) {
        SpringApplication.run(XmemcachedApplicationTests.class, args);
    }

}
```

## Jeebiz 技术社区

Jeebiz 技术社区 **微信公共号**、**小程序**，欢迎关注反馈意见和一起交流，关注公众号回复「Jeebiz」拉你入群。

|公共号|小程序|
|---|---|
| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/qrcode_for_gh_1d965ea2dfd1_344.jpg)| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/gh_09d7d00da63e_344.jpg)|


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
xmemcached:
  connect-timeout: 10s
  read-timeout: 30s
  write-timeout: 20s
  pool:
    # 最大空闲连接梳数量，超出该值后，连接用完后会被关闭，最多只会保留idleConnectionCount个连接数量
    max-idle-connections: 256
    # 最大瞬时处理连接数量
    max-requests: 128
    # 每个请求地址最大瞬时处理连接数量
    max-requests-per-host: 24
```

##### 3、使用示例

```java

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	@Autowired
	private OkHttpClient okHttpClient;
	
	@PostConstruct
	public void test() throws IOException {
		
		//调用ok的get请求
       	Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
       	//同步请求方式
	   	Response theResponse = okHttpClient.newCall(newRequest).execute();
	   	// 解析响应内容
	   	ResponseBody body = theResponse.body();
	   	// 响应头信息
	   	Headers headers = theResponse.headers();
	   	// 响应类型
	   	MediaType mediaType = body.contentType();
	   	// 成功状态
		if( theResponse.isSuccessful()) {
			// do something
		} 
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
```

## Jeebiz 技术社区

Jeebiz 技术社区 **微信公共号**、**小程序**，欢迎关注反馈意见和一起交流，关注公众号回复「Jeebiz」拉你入群。

|公共号|小程序|
|---|---|
| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/qrcode_for_gh_1d965ea2dfd1_344.jpg)| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/gh_09d7d00da63e_344.jpg)|


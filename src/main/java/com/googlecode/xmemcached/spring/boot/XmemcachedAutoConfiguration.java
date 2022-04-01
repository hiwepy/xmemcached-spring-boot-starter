package com.googlecode.xmemcached.spring.boot;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ConditionalOnClass({ XMemcachedClient.class })
@EnableConfigurationProperties({ XmemcachedProperties.class})
public class XmemcachedAutoConfiguration {

	@Bean(destroyMethod = "shutdown")
	public XMemcachedClient xMemcachedClient( XmemcachedProperties xMemcachedProperties) throws IOException {

		XMemcachedClientBuilder builder = new XMemcachedClientBuilder (
				AddrUtil.getAddresses("localhost:11211"));

		// 宕机报警
		builder.setFailureMode(true);
		// 使用二进制文件
		builder.setCommandFactory(new BinaryCommandFactory());

		builder.setSessionLocator(new KetamaMemcachedSessionLocator());

		/**
		 * 设置连接池大小，即客户端个数
		 * In a high concurrent enviroment,you may want to pool memcached clients.
		 * But a xmemcached client has to start a reactor thread and some thread pools,
		 * if you create too many clients,the cost is very large.
		 * Xmemcached supports connection pool instreadof client pool.
		 * you can create more connections to one or more memcached servers,
		 * and these connections share the same reactor and thread pools,
		 * it will reduce the cost of system.
		 *  默认的pool size是1。设置这一数值不一定能提高性能，请依据你的项目的测试结果为准。初步的测试表明只有在大并发下才有提升。
		 *  设置连接池的一个不良后果就是，同一个memcached的连接之间的数据更新并非同步的
		 *  因此你的应用需要自己保证数据更新的原子性（采用CAS或者数据之间毫无关联）。
		 */
		builder.setConnectionPoolSize(10);
		return (XMemcachedClient) builder.build();
	}

	@Bean
	public XmemcachedOperationTemplate xmemcachedOperationTemplate(XMemcachedClient xMemcachedClient, XmemcachedProperties xMemcachedProperties) {
		return new XmemcachedOperationTemplate(xMemcachedClient, xMemcachedProperties);
	}

}

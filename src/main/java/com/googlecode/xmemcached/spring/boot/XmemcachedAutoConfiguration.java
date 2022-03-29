package com.googlecode.xmemcached.spring.boot;

import net.rubyeye.xmemcached.XMemcachedClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ XMemcachedClient.class })
@EnableConfigurationProperties({ XmemcachedProperties.class})
public class XmemcachedAutoConfiguration {

	@Bean
	public XmemcachedOperationTemplate xmemcachedOperationTemplate(XmemcachedProperties properties) {
		return new XmemcachedOperationTemplate(properties);
	}

}

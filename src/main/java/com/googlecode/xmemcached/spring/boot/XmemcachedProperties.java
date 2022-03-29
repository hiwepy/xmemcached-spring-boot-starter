package com.googlecode.xmemcached.spring.boot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = XmemcachedProperties.PREFIX)
@Data
public class XmemcachedProperties {

	/**
     * The prefix of the property of {@link XmemcachedProperties}.
     */
    public static final String PREFIX = "spring.xmemcached";

	/**
	 * AccessKey, 用于标识、校验用户身份
	 */
	private String accessKey;
	/**
	 * SecretKey, 用于标识、校验用户身份
	 */
	private String secretKey;

}

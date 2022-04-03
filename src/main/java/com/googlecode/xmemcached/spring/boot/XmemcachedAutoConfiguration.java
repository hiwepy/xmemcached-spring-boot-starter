package com.googlecode.xmemcached.spring.boot;

import com.google.code.yanf4j.core.impl.StandardSocketOption;
import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.DefaultKeyProvider;
import net.rubyeye.xmemcached.impl.IndexMemcachedSessionComparator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Configuration
@ConditionalOnClass({ XMemcachedClient.class })
@EnableConfigurationProperties({ XmemcachedProperties.class})
public class XmemcachedAutoConfiguration {

	@Bean(destroyMethod = "shutdown")
	public XMemcachedClient xMemcachedClient(
			ObjectProvider<KeyProvider> keyProvider,
			ObjectProvider<AuthInfoProvider> authInfoProvider,
			ObjectProvider<CommandFactory> commandFactoryProvider,
			ObjectProvider<MemcachedSessionComparator> sessionComparatorProvider,
			ObjectProvider<MemcachedSessionLocator> sessionLocatorProvider,
			XmemcachedProperties xMemcachedProperties) throws IOException {

		XMemcachedClientBuilder builder;
		Objects.requireNonNull(xMemcachedProperties.getAddresses());
		if(StringUtils.hasText(xMemcachedProperties.getWeights())){
			int[] weights = Stream.of(StringUtils.tokenizeToStringArray(xMemcachedProperties.getWeights(), ",")).mapToInt(weight -> Integer.parseInt(weight)).toArray();
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getAddresses()), weights);
		} else {
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getAddresses()));
		}

		builder.setAuthInfoMap(authInfoProvider.getIfAvailable(() -> new AuthInfoProvider(){}).getAuthInfoMap());
		// 宕机报警
		builder.setFailureMode(xMemcachedProperties.isFailureMode());
		// 使用二进制文件
		builder.setCommandFactory(commandFactoryProvider.getIfAvailable(() -> new BinaryCommandFactory()));
		builder.setConnectionPoolSize(xMemcachedProperties.getConnectionPoolSize());
		builder.setConnectTimeout(xMemcachedProperties.getConnectTimeout().toMillis());
		builder.setEnableHealSession(xMemcachedProperties.isEnableHealSession());
		builder.setHealSessionInterval(xMemcachedProperties.getHealSessionInterval());
		builder.setKeyProvider(keyProvider.getIfAvailable(() -> DefaultKeyProvider.INSTANCE));
		builder.setMaxQueuedNoReplyOperations(xMemcachedProperties.getMaxQueuedNoReplyOperations());
		builder.setOpTimeout(xMemcachedProperties.getOpTimeout().toMillis());
		builder.setResolveInetAddresses(xMemcachedProperties.isResolveInetAddresses());
		builder.setSessionComparator(sessionComparatorProvider.getIfAvailable(() -> new IndexMemcachedSessionComparator()));
		builder.setSessionLocator(sessionLocatorProvider.getIfAvailable(() -> new KetamaMemcachedSessionLocator()));
		builder.setSanitizeKeys(xMemcachedProperties.isSanitizeKeys());

		XmemcachedProperties.Networking networking = xMemcachedProperties.getNetworking();
		if(Objects.nonNull(networking)){
			final com.google.code.yanf4j.config.Configuration configuration = new com.google.code.yanf4j.config.Configuration();
			configuration.setCheckSessionTimeoutInterval(networking.getCheckSessionTimeoutInterval());
			configuration.setDispatchMessageThreadCount(networking.getDispatchMessageThreadCount());
			configuration.setHandleReadWriteConcurrently(networking.isHandleReadWriteConcurrently());
			configuration.setReadThreadCount(networking.getReadThreadCount());
			configuration.setSessionReadBufferSize(networking.getSessionReadBufferSize());
			configuration.setSessionIdleTimeout(networking.getSessionIdleTimeout());
			configuration.setSelectorPoolSize(networking.getSelectorPoolSize());
			configuration.setSoTimeout(networking.getSoTimeout());
			configuration.setStatisticsInterval(networking.getStatisticsInterval());
			configuration.setStatisticsServer(networking.isStatisticsServer());
			configuration.setWriteThreadCount(networking.getWriteThreadCount());
			builder.setConfiguration(configuration);
		}
		XmemcachedProperties.SocketOptions socketOptions = xMemcachedProperties.getSocketOptions();
		if(Objects.nonNull(socketOptions)){
			builder.setSocketOption(StandardSocketOption.TCP_NODELAY, socketOptions.isTcpNodelay());
			builder.setSocketOption(StandardSocketOption.SO_RCVBUF, socketOptions.getSoRcvbuf());
			builder.setSocketOption(StandardSocketOption.SO_KEEPALIVE, socketOptions.isSoKeepalive());
			builder.setSocketOption(StandardSocketOption.SO_SNDBUF, socketOptions.getSoSndbuf());
			builder.setSocketOption(StandardSocketOption.SO_LINGER, socketOptions.getSoLinger());
			builder.setSocketOption(StandardSocketOption.SO_REUSEADDR, socketOptions.isSoReuseaddr());
		}
		return (XMemcachedClient) builder.build();
	}

	@Bean
	public XmemcachedOperationTemplate xmemcachedOperationTemplate(XMemcachedClient xMemcachedClient, XmemcachedProperties xMemcachedProperties) {
		return new XmemcachedOperationTemplate(xMemcachedClient, xMemcachedProperties);
	}

}

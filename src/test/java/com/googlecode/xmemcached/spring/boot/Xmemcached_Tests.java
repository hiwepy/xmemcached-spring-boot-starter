package com.googlecode.xmemcached.spring.boot;

import com.google.code.yanf4j.config.Configuration;
import com.google.code.yanf4j.core.impl.StandardSocketOption;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.IndexMemcachedSessionComparator;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class Xmemcached_Tests {

	private XmemcachedOperationTemplate memcachedOperation;

	public XMemcachedClient xMemcachedClient(XmemcachedProperties xMemcachedProperties) throws IOException {
		XMemcachedClientBuilder builder;
		Objects.requireNonNull(xMemcachedProperties.getAddresses());
		if(StringUtils.hasText(xMemcachedProperties.getWeights())){
			int[] weights = Stream.of(StringUtils.tokenizeToStringArray(xMemcachedProperties.getWeights(), ",")).mapToInt(weight -> Integer.parseInt(weight)).toArray();
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getAddresses()), weights);
		} else {
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getAddresses()));
		}

		builder.setAuthInfoMap(xMemcachedProperties.getAuthInfoMap());
		// 宕机报警
		builder.setFailureMode(xMemcachedProperties.isFailureMode());
		// 使用二进制文件
		builder.setCommandFactory(new BinaryCommandFactory());
		builder.setConnectionPoolSize(xMemcachedProperties.getConnectionPoolSize());
		builder.setConnectTimeout(xMemcachedProperties.getConnectTimeout().toMillis());
		builder.setEnableHealSession(xMemcachedProperties.isEnableHealSession());
		builder.setHealSessionInterval(xMemcachedProperties.getHealSessionInterval());
		builder.setMaxQueuedNoReplyOperations(xMemcachedProperties.getMaxQueuedNoReplyOperations());
		builder.setOpTimeout(xMemcachedProperties.getOpTimeout().toMillis());
		builder.setResolveInetAddresses(xMemcachedProperties.isResolveInetAddresses());
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		builder.setSanitizeKeys(xMemcachedProperties.isSanitizeKeys());
		builder.setSessionComparator(new IndexMemcachedSessionComparator());

		XmemcachedProperties.Networking networking = xMemcachedProperties.getNetworking();
		if(Objects.nonNull(networking)){
			final Configuration configuration = new Configuration();
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

	@Before
	public void start()  throws IOException{
		XmemcachedProperties xMemcachedProperties = new XmemcachedProperties();
		xMemcachedProperties.setAddresses("101.35.55.147:11211");
		XMemcachedClient xMemcachedClient = this.xMemcachedClient(xMemcachedProperties);
		memcachedOperation = new XmemcachedOperationTemplate(xMemcachedClient, xMemcachedProperties);
	}

    @Test
    public void testCounter() throws Exception {
		Counter counter = memcachedOperation.counter("counter");
		log.info("counter++ : {}", counter.incrementAndGet());
		log.info("counter-- : {}", counter.decrementAndGet());
		log.info("counter+5 : {}", counter.addAndGet(5));
		log.info("counter-1 : {}", counter.addAndGet(-1));
    }

	@Test
	public void testIncr() throws Exception {
		long counter = memcachedOperation.incr("testIncr", 12);
		log.info("counter incr : {}", counter);
	}

	@Test
	public void testDecr() throws Exception {
		long counter = memcachedOperation.decr("testDecr", 10);
		log.info("counter decr : {}", counter);
	}

	@Test
	public void testSetIfAbsent() throws Exception {
		boolean setIfAbsent = memcachedOperation.setIfAbsent("testSetIfAbsent", 10);
		log.info("setIfAbsent : {}", setIfAbsent);
	}

	@Test
	public void testCas() throws Exception {
		boolean cas = memcachedOperation.cas("testCas", 10);
		log.info("cas : {}", cas);
	}
}

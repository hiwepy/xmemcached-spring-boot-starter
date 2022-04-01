package com.googlecode.xmemcached.spring.boot;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class XmemcachedApplicationTests {

	private XmemcachedOperationTemplate memcachedOperation;

	public XMemcachedClient xMemcachedClient(XmemcachedProperties xMemcachedProperties) throws IOException {

		XMemcachedClientBuilder builder = new XMemcachedClientBuilder (
				AddrUtil.getAddresses("localhost:11211"));

		// 宕机报警
		builder.setFailureMode(true);
		// 使用二进制文件
		builder.setCommandFactory(new BinaryCommandFactory());

		builder.setSessionLocator(new KetamaMemcachedSessionLocator());

		builder.setConnectionPoolSize(10);
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

}

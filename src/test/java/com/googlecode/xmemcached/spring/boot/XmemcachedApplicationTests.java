package com.googlecode.xmemcached.spring.boot;

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

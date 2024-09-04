package com.googlecode.xmemcached.spring.boot;

import com.google.code.yanf4j.config.Configuration;
import com.google.code.yanf4j.util.SystemUtils;
import lombok.Data;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = XmemcachedProperties.PREFIX)
@Data
public class XmemcachedProperties {

	/**
     * The prefix of the property of {@link XmemcachedProperties}.
     */
    public static final String PREFIX = "spring.memcached";

	/**
	 * Memcached server addresses.   Example: 127.0.0.1:11211
	 */
	private String addresses;

	private String weights;

	/**
	 * Connection timeout.
	 */
	private Duration connectTimeout = Duration.ofMillis(MemcachedClient.DEFAULT_CONNECT_TIMEOUT);
	/**
	 * 连接池大小，即客户端个数
	 * In a high concurrent enviroment,you may want to pool memcached clients.
	 * But a xmemcached client has to start a reactor thread and some thread pools,
	 * if you create too many clients,the cost is very large.
	 * Xmemcached supports connection pool instreadof client pool.
	 * you can create more connections to one or more memcached servers,
	 * and these connections share the same reactor and thread pools,
	 * it will reduce the cost of system.
	 *  默认的pool size是1。设置这一数值不一定能提高性能，请依据你的项目的测试结果为准。初步的测试表明只有在大并发下才有提升。
	 *  设置连接池的一个不良后果就是，同一个memcached的连接之间的数据更新并非同步的因此你的应用需要自己保证数据更新的原子性（采用CAS或者数据之间毫无关联）。
	 */
	private int connectionPoolSize = MemcachedClient.DEFAULT_CONNECTION_POOL_SIZE;

	private boolean failureMode;

	private boolean sanitizeKeys;

	/**
	 * Operation timeout, if the operation is not returned in 5 second,throw TimeoutException..
	 */
	private Duration opTimeout = Duration.ofMillis(MemcachedClient.DEFAULT_OP_TIMEOUT);

	private int maxQueuedNoReplyOperations = MemcachedClient.DEFAULT_MAX_QUEUED_NOPS;

	private long healSessionInterval = MemcachedClient.DEFAULT_HEAL_SESSION_INTERVAL;

	private boolean enableHealSession = true;

	private boolean resolveInetAddresses = true;

	private Networking networking;

	private SocketOptions socketOptions;

	/**
	 * Networking properties.
	 */
	@Data
	public static class Networking {

		/**
		 * Read buffer size per connection
		 */
		private int sessionReadBufferSize = MemcachedClient.DEFAULT_SESSION_READ_BUFF_SIZE;

		private long sessionIdleTimeout = MemcachedClient.DEFAULT_SESSION_IDLE_TIMEOUT;

		/**
		 * Socket SO_TIMEOUT option
		 */
		private int soTimeout = 0;

		/**
		 * Thread count for processing WRITABLE event
		 */
		private int writeThreadCount = 0;

		/**
		 * Whether to enable statistics
		 */
		private boolean statisticsServer = false;

		protected long statisticsInterval = 5 * 60 * 1000L;

		/**
		 * Whether to handle read write concurrently,default is true
		 */
		private boolean handleReadWriteConcurrently = true;

		/**
		 * Thread coount for processing message dispatching
		 */
		private int dispatchMessageThreadCount = 0;

		/**
		 * THread count for processing READABLE event
		 */
		private int readThreadCount = 1;

		private int selectorPoolSize = System.getProperty(Configuration.XMEMCACHED_SELECTOR_POOL_SIZE) == null ? SystemUtils.getSystemThreadCount() : Integer.parseInt(System.getProperty(Configuration.XMEMCACHED_SELECTOR_POOL_SIZE));

		/**
		 * check session idle interval
		 */
		private long checkSessionTimeoutInterval = 1000L;
	}


	/**
	 * Socket properties.
	 */
	@Data
	public static class SocketOptions {

		private boolean tcpNodelay = MemcachedClient.DEFAULT_TCP_NO_DELAY;

		private int soRcvbuf = MemcachedClient.DEFAULT_TCP_RECV_BUFF_SIZE;

		private boolean soKeepalive = MemcachedClient.DEFAULT_TCP_KEEPLIVE;

		private int soSndbuf = MemcachedClient.DEFAULT_TCP_SEND_BUFF_SIZE;

		private int soLinger = 0;

		private boolean soReuseaddr = true;

	}


}

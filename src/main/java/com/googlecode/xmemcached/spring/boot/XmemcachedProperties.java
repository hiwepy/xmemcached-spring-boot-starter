package com.googlecode.xmemcached.spring.boot;

import lombok.Data;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = XmemcachedProperties.PREFIX)
@Data
public class XmemcachedProperties {

	/**
     * The prefix of the property of {@link XmemcachedProperties}.
     */
    public static final String PREFIX = "spring.xmemcached";

	/**
	 * Connection URL. Overrides host, port, and password. User is ignored. Example:
	 * memcached://user:password@example.com:6379
	 */
	private String url;

	/**
	 * Memcached server host.
	 */
	private String host = "localhost";

	/**
	 * Login password of the memcached server.
	 */
	private String password;

	/**
	 * Memcached server port.
	 */
	private int port = 6379;

	/**
	 * Whether to enable SSL support.
	 */
	private boolean ssl;

	/**
	 * Connection timeout.
	 */
	private Duration timeout = Duration.ofMillis(MemcachedClient.DEFAULT_CONNECT_TIMEOUT);

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


	/**
	 * Lettuce pool configuration.
	 */
	private Pool pool;

	/**
	 * Pool properties.
	 */
	public static class Pool {

		/**
		 * Maximum number of "idle" connections in the pool. Use a negative value to
		 * indicate an unlimited number of idle connections.
		 */
		private int maxIdle = 8;

		/**
		 * Target for the minimum number of idle connections to maintain in the pool. This
		 * setting only has an effect if both it and time between eviction runs are
		 * positive.
		 */
		private int minIdle = 0;

		/**
		 * Maximum number of connections that can be allocated by the pool at a given
		 * time. Use a negative value for no limit.
		 */
		private int maxActive = 8;

		/**
		 * Maximum amount of time a connection allocation should block before throwing an
		 * exception when the pool is exhausted. Use a negative value to block
		 * indefinitely.
		 */
		private Duration maxWait = Duration.ofMillis(-1);

		/**
		 * Time between runs of the idle object evictor thread. When positive, the idle
		 * object evictor thread starts, otherwise no idle object eviction is performed.
		 */
		private Duration timeBetweenEvictionRuns;

		public int getMaxIdle() {
			return this.maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public int getMinIdle() {
			return this.minIdle;
		}

		public void setMinIdle(int minIdle) {
			this.minIdle = minIdle;
		}

		public int getMaxActive() {
			return this.maxActive;
		}

		public void setMaxActive(int maxActive) {
			this.maxActive = maxActive;
		}

		public Duration getMaxWait() {
			return this.maxWait;
		}

		public void setMaxWait(Duration maxWait) {
			this.maxWait = maxWait;
		}

		public Duration getTimeBetweenEvictionRuns() {
			return this.timeBetweenEvictionRuns;
		}

		public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
			this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
		}

	}
}

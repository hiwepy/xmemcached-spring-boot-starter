package com.googlecode.xmemcached.spring.boot;

import com.google.code.yanf4j.config.Configuration;
import com.google.code.yanf4j.util.SystemUtils;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Configuration properties for XMemcached.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = XmemcachedProperties.PREFIX)
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
	 * Returns the addresses.
	 *
	 * @return the addresses
	 */
	public String getAddresses() {
		return addresses;
	}

	/**
	 * Sets the addresses.
	 *
	 * @param addresses the addresses
	 */
	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	/**
	 * Returns the weights.
	 *
	 * @return the weights
	 */
	public String getWeights() {
		return weights;
	}

	/**
	 * Sets the weights.
	 *
	 * @param weights the weights
	 */
	public void setWeights(String weights) {
		this.weights = weights;
	}

	/**
	 * Returns the connect timeout.
	 *
	 * @return the connect timeout
	 */
	public Duration getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * Sets the connect timeout.
	 *
	 * @param connectTimeout the connect timeout
	 */
	public void setConnectTimeout(Duration connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * Returns the connection pool size.
	 *
	 * @return the connection pool size
	 */
	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	/**
	 * Sets the connection pool size.
	 *
	 * @param connectionPoolSize the connection pool size
	 */
	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	/**
	 * Returns the failure mode.
	 *
	 * @return the failure mode
	 */
	public boolean isFailureMode() {
		return failureMode;
	}

	/**
	 * Sets the failure mode.
	 *
	 * @param failureMode the failure mode
	 */
	public void setFailureMode(boolean failureMode) {
		this.failureMode = failureMode;
	}

	/**
	 * Returns the sanitize keys.
	 *
	 * @return the sanitize keys
	 */
	public boolean isSanitizeKeys() {
		return sanitizeKeys;
	}

	/**
	 * Sets the sanitize keys.
	 *
	 * @param sanitizeKeys the sanitize keys
	 */
	public void setSanitizeKeys(boolean sanitizeKeys) {
		this.sanitizeKeys = sanitizeKeys;
	}

	/**
	 * Returns the op timeout.
	 *
	 * @return the op timeout
	 */
	public Duration getOpTimeout() {
		return opTimeout;
	}

	/**
	 * Sets the op timeout.
	 *
	 * @param opTimeout the op timeout
	 */
	public void setOpTimeout(Duration opTimeout) {
		this.opTimeout = opTimeout;
	}

	/**
	 * Returns the max queued no reply operations.
	 *
	 * @return the max queued no reply operations
	 */
	public int getMaxQueuedNoReplyOperations() {
		return maxQueuedNoReplyOperations;
	}

	/**
	 * Sets the max queued no reply operations.
	 *
	 * @param maxQueuedNoReplyOperations the max queued no reply operations
	 */
	public void setMaxQueuedNoReplyOperations(int maxQueuedNoReplyOperations) {
		this.maxQueuedNoReplyOperations = maxQueuedNoReplyOperations;
	}

	/**
	 * Returns the heal session interval.
	 *
	 * @return the heal session interval
	 */
	public long getHealSessionInterval() {
		return healSessionInterval;
	}

	/**
	 * Sets the heal session interval.
	 *
	 * @param healSessionInterval the heal session interval
	 */
	public void setHealSessionInterval(long healSessionInterval) {
		this.healSessionInterval = healSessionInterval;
	}

	/**
	 * Returns the enable heal session.
	 *
	 * @return the enable heal session
	 */
	public boolean isEnableHealSession() {
		return enableHealSession;
	}

	/**
	 * Sets the enable heal session.
	 *
	 * @param enableHealSession the enable heal session
	 */
	public void setEnableHealSession(boolean enableHealSession) {
		this.enableHealSession = enableHealSession;
	}

	/**
	 * Returns the resolve inet addresses.
	 *
	 * @return the resolve inet addresses
	 */
	public boolean isResolveInetAddresses() {
		return resolveInetAddresses;
	}

	/**
	 * Sets the resolve inet addresses.
	 *
	 * @param resolveInetAddresses the resolve inet addresses
	 */
	public void setResolveInetAddresses(boolean resolveInetAddresses) {
		this.resolveInetAddresses = resolveInetAddresses;
	}

	/**
	 * Returns the networking.
	 *
	 * @return the networking
	 */
	public Networking getNetworking() {
		return networking;
	}

	/**
	 * Sets the networking.
	 *
	 * @param networking the networking
	 */
	public void setNetworking(Networking networking) {
		this.networking = networking;
	}

	/**
	 * Returns the socket options.
	 *
	 * @return the socket options
	 */
	public SocketOptions getSocketOptions() {
		return socketOptions;
	}

	/**
	 * Sets the socket options.
	 *
	 * @param socketOptions the socket options
	 */
	public void setSocketOptions(SocketOptions socketOptions) {
		this.socketOptions = socketOptions;
	}

	@Override
	/**
	 * to String.
	 *
	 * @return the result
	 */
	public String toString() {
		return "XmemcachedProperties{addresses='" + addresses + "', connectionPoolSize=" + connectionPoolSize + "}";
	}

	/**
	 * Networking properties.
	 */
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

		/**
		 * Returns the session read buffer size.
		 *
		 * @return the session read buffer size
		 */
		public int getSessionReadBufferSize() {
			return sessionReadBufferSize;
		}

		/**
		 * Sets the session read buffer size.
		 *
		 * @param sessionReadBufferSize the session read buffer size
		 */
		public void setSessionReadBufferSize(int sessionReadBufferSize) {
			this.sessionReadBufferSize = sessionReadBufferSize;
		}

		/**
		 * Returns the session idle timeout.
		 *
		 * @return the session idle timeout
		 */
		public long getSessionIdleTimeout() {
			return sessionIdleTimeout;
		}

		/**
		 * Sets the session idle timeout.
		 *
		 * @param sessionIdleTimeout the session idle timeout
		 */
		public void setSessionIdleTimeout(long sessionIdleTimeout) {
			this.sessionIdleTimeout = sessionIdleTimeout;
		}

		/**
		 * Returns the so timeout.
		 *
		 * @return the so timeout
		 */
		public int getSoTimeout() {
			return soTimeout;
		}

		/**
		 * Sets the so timeout.
		 *
		 * @param soTimeout the so timeout
		 */
		public void setSoTimeout(int soTimeout) {
			this.soTimeout = soTimeout;
		}

		/**
		 * Returns the write thread count.
		 *
		 * @return the write thread count
		 */
		public int getWriteThreadCount() {
			return writeThreadCount;
		}

		/**
		 * Sets the write thread count.
		 *
		 * @param writeThreadCount the write thread count
		 */
		public void setWriteThreadCount(int writeThreadCount) {
			this.writeThreadCount = writeThreadCount;
		}

		/**
		 * Returns the statistics server.
		 *
		 * @return the statistics server
		 */
		public boolean isStatisticsServer() {
			return statisticsServer;
		}

		/**
		 * Sets the statistics server.
		 *
		 * @param statisticsServer the statistics server
		 */
		public void setStatisticsServer(boolean statisticsServer) {
			this.statisticsServer = statisticsServer;
		}

		/**
		 * Returns the statistics interval.
		 *
		 * @return the statistics interval
		 */
		public long getStatisticsInterval() {
			return statisticsInterval;
		}

		/**
		 * Sets the statistics interval.
		 *
		 * @param statisticsInterval the statistics interval
		 */
		public void setStatisticsInterval(long statisticsInterval) {
			this.statisticsInterval = statisticsInterval;
		}

		/**
		 * Returns the handle read write concurrently.
		 *
		 * @return the handle read write concurrently
		 */
		public boolean isHandleReadWriteConcurrently() {
			return handleReadWriteConcurrently;
		}

		/**
		 * Sets the handle read write concurrently.
		 *
		 * @param handleReadWriteConcurrently the handle read write concurrently
		 */
		public void setHandleReadWriteConcurrently(boolean handleReadWriteConcurrently) {
			this.handleReadWriteConcurrently = handleReadWriteConcurrently;
		}

		/**
		 * Returns the dispatch message thread count.
		 *
		 * @return the dispatch message thread count
		 */
		public int getDispatchMessageThreadCount() {
			return dispatchMessageThreadCount;
		}

		/**
		 * Sets the dispatch message thread count.
		 *
		 * @param dispatchMessageThreadCount the dispatch message thread count
		 */
		public void setDispatchMessageThreadCount(int dispatchMessageThreadCount) {
			this.dispatchMessageThreadCount = dispatchMessageThreadCount;
		}

		/**
		 * Returns the read thread count.
		 *
		 * @return the read thread count
		 */
		public int getReadThreadCount() {
			return readThreadCount;
		}

		/**
		 * Sets the read thread count.
		 *
		 * @param readThreadCount the read thread count
		 */
		public void setReadThreadCount(int readThreadCount) {
			this.readThreadCount = readThreadCount;
		}

		/**
		 * Returns the selector pool size.
		 *
		 * @return the selector pool size
		 */
		public int getSelectorPoolSize() {
			return selectorPoolSize;
		}

		/**
		 * Sets the selector pool size.
		 *
		 * @param selectorPoolSize the selector pool size
		 */
		public void setSelectorPoolSize(int selectorPoolSize) {
			this.selectorPoolSize = selectorPoolSize;
		}

		/**
		 * Returns the check session timeout interval.
		 *
		 * @return the check session timeout interval
		 */
		public long getCheckSessionTimeoutInterval() {
			return checkSessionTimeoutInterval;
		}

		/**
		 * Sets the check session timeout interval.
		 *
		 * @param checkSessionTimeoutInterval the check session timeout interval
		 */
		public void setCheckSessionTimeoutInterval(long checkSessionTimeoutInterval) {
			this.checkSessionTimeoutInterval = checkSessionTimeoutInterval;
		}

		@Override
		/**
		 * to String.
		 *
		 * @return the result
		 */
		public String toString() {
			return "Networking{sessionReadBufferSize=" + sessionReadBufferSize + ", readThreadCount=" + readThreadCount + "}";
		}

	}


	/**
	 * Socket properties.
	 */
	public static class SocketOptions {

		private boolean tcpNodelay = MemcachedClient.DEFAULT_TCP_NO_DELAY;

		private int soRcvbuf = MemcachedClient.DEFAULT_TCP_RECV_BUFF_SIZE;

		private boolean soKeepalive = MemcachedClient.DEFAULT_TCP_KEEPLIVE;

		private int soSndbuf = MemcachedClient.DEFAULT_TCP_SEND_BUFF_SIZE;

		private int soLinger = 0;

		private boolean soReuseaddr = true;

		/**
		 * Returns the tcp nodelay.
		 *
		 * @return the tcp nodelay
		 */
		public boolean isTcpNodelay() {
			return tcpNodelay;
		}

		/**
		 * Sets the tcp nodelay.
		 *
		 * @param tcpNodelay the tcp nodelay
		 */
		public void setTcpNodelay(boolean tcpNodelay) {
			this.tcpNodelay = tcpNodelay;
		}

		/**
		 * Returns the so rcvbuf.
		 *
		 * @return the so rcvbuf
		 */
		public int getSoRcvbuf() {
			return soRcvbuf;
		}

		/**
		 * Sets the so rcvbuf.
		 *
		 * @param soRcvbuf the so rcvbuf
		 */
		public void setSoRcvbuf(int soRcvbuf) {
			this.soRcvbuf = soRcvbuf;
		}

		/**
		 * Returns the so keepalive.
		 *
		 * @return the so keepalive
		 */
		public boolean isSoKeepalive() {
			return soKeepalive;
		}

		/**
		 * Sets the so keepalive.
		 *
		 * @param soKeepalive the so keepalive
		 */
		public void setSoKeepalive(boolean soKeepalive) {
			this.soKeepalive = soKeepalive;
		}

		/**
		 * Returns the so sndbuf.
		 *
		 * @return the so sndbuf
		 */
		public int getSoSndbuf() {
			return soSndbuf;
		}

		/**
		 * Sets the so sndbuf.
		 *
		 * @param soSndbuf the so sndbuf
		 */
		public void setSoSndbuf(int soSndbuf) {
			this.soSndbuf = soSndbuf;
		}

		/**
		 * Returns the so linger.
		 *
		 * @return the so linger
		 */
		public int getSoLinger() {
			return soLinger;
		}

		/**
		 * Sets the so linger.
		 *
		 * @param soLinger the so linger
		 */
		public void setSoLinger(int soLinger) {
			this.soLinger = soLinger;
		}

		/**
		 * Returns the so reuseaddr.
		 *
		 * @return the so reuseaddr
		 */
		public boolean isSoReuseaddr() {
			return soReuseaddr;
		}

		/**
		 * Sets the so reuseaddr.
		 *
		 * @param soReuseaddr the so reuseaddr
		 */
		public void setSoReuseaddr(boolean soReuseaddr) {
			this.soReuseaddr = soReuseaddr;
		}

		@Override
		/**
		 * to String.
		 *
		 * @return the result
		 */
		public String toString() {
			return "SocketOptions{tcpNodelay=" + tcpNodelay + ", soRcvbuf=" + soRcvbuf + ", soSndbuf=" + soSndbuf + "}";
		}

	}


}

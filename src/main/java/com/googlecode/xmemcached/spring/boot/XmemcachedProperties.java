package com.googlecode.xmemcached.spring.boot;

import com.google.code.yanf4j.config.Configuration;
import com.google.code.yanf4j.util.SystemUtils;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Configuration properties for XMemcached.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
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

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public String getWeights() {
		return weights;
	}

	public void setWeights(String weights) {
		this.weights = weights;
	}

	public Duration getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Duration connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public boolean isFailureMode() {
		return failureMode;
	}

	public void setFailureMode(boolean failureMode) {
		this.failureMode = failureMode;
	}

	public boolean isSanitizeKeys() {
		return sanitizeKeys;
	}

	public void setSanitizeKeys(boolean sanitizeKeys) {
		this.sanitizeKeys = sanitizeKeys;
	}

	public Duration getOpTimeout() {
		return opTimeout;
	}

	public void setOpTimeout(Duration opTimeout) {
		this.opTimeout = opTimeout;
	}

	public int getMaxQueuedNoReplyOperations() {
		return maxQueuedNoReplyOperations;
	}

	public void setMaxQueuedNoReplyOperations(int maxQueuedNoReplyOperations) {
		this.maxQueuedNoReplyOperations = maxQueuedNoReplyOperations;
	}

	public long getHealSessionInterval() {
		return healSessionInterval;
	}

	public void setHealSessionInterval(long healSessionInterval) {
		this.healSessionInterval = healSessionInterval;
	}

	public boolean isEnableHealSession() {
		return enableHealSession;
	}

	public void setEnableHealSession(boolean enableHealSession) {
		this.enableHealSession = enableHealSession;
	}

	public boolean isResolveInetAddresses() {
		return resolveInetAddresses;
	}

	public void setResolveInetAddresses(boolean resolveInetAddresses) {
		this.resolveInetAddresses = resolveInetAddresses;
	}

	public Networking getNetworking() {
		return networking;
	}

	public void setNetworking(Networking networking) {
		this.networking = networking;
	}

	public SocketOptions getSocketOptions() {
		return socketOptions;
	}

	public void setSocketOptions(SocketOptions socketOptions) {
		this.socketOptions = socketOptions;
	}

	@Override
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

		public int getSessionReadBufferSize() {
			return sessionReadBufferSize;
		}

		public void setSessionReadBufferSize(int sessionReadBufferSize) {
			this.sessionReadBufferSize = sessionReadBufferSize;
		}

		public long getSessionIdleTimeout() {
			return sessionIdleTimeout;
		}

		public void setSessionIdleTimeout(long sessionIdleTimeout) {
			this.sessionIdleTimeout = sessionIdleTimeout;
		}

		public int getSoTimeout() {
			return soTimeout;
		}

		public void setSoTimeout(int soTimeout) {
			this.soTimeout = soTimeout;
		}

		public int getWriteThreadCount() {
			return writeThreadCount;
		}

		public void setWriteThreadCount(int writeThreadCount) {
			this.writeThreadCount = writeThreadCount;
		}

		public boolean isStatisticsServer() {
			return statisticsServer;
		}

		public void setStatisticsServer(boolean statisticsServer) {
			this.statisticsServer = statisticsServer;
		}

		public long getStatisticsInterval() {
			return statisticsInterval;
		}

		public void setStatisticsInterval(long statisticsInterval) {
			this.statisticsInterval = statisticsInterval;
		}

		public boolean isHandleReadWriteConcurrently() {
			return handleReadWriteConcurrently;
		}

		public void setHandleReadWriteConcurrently(boolean handleReadWriteConcurrently) {
			this.handleReadWriteConcurrently = handleReadWriteConcurrently;
		}

		public int getDispatchMessageThreadCount() {
			return dispatchMessageThreadCount;
		}

		public void setDispatchMessageThreadCount(int dispatchMessageThreadCount) {
			this.dispatchMessageThreadCount = dispatchMessageThreadCount;
		}

		public int getReadThreadCount() {
			return readThreadCount;
		}

		public void setReadThreadCount(int readThreadCount) {
			this.readThreadCount = readThreadCount;
		}

		public int getSelectorPoolSize() {
			return selectorPoolSize;
		}

		public void setSelectorPoolSize(int selectorPoolSize) {
			this.selectorPoolSize = selectorPoolSize;
		}

		public long getCheckSessionTimeoutInterval() {
			return checkSessionTimeoutInterval;
		}

		public void setCheckSessionTimeoutInterval(long checkSessionTimeoutInterval) {
			this.checkSessionTimeoutInterval = checkSessionTimeoutInterval;
		}

		@Override
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

		public boolean isTcpNodelay() {
			return tcpNodelay;
		}

		public void setTcpNodelay(boolean tcpNodelay) {
			this.tcpNodelay = tcpNodelay;
		}

		public int getSoRcvbuf() {
			return soRcvbuf;
		}

		public void setSoRcvbuf(int soRcvbuf) {
			this.soRcvbuf = soRcvbuf;
		}

		public boolean isSoKeepalive() {
			return soKeepalive;
		}

		public void setSoKeepalive(boolean soKeepalive) {
			this.soKeepalive = soKeepalive;
		}

		public int getSoSndbuf() {
			return soSndbuf;
		}

		public void setSoSndbuf(int soSndbuf) {
			this.soSndbuf = soSndbuf;
		}

		public int getSoLinger() {
			return soLinger;
		}

		public void setSoLinger(int soLinger) {
			this.soLinger = soLinger;
		}

		public boolean isSoReuseaddr() {
			return soReuseaddr;
		}

		public void setSoReuseaddr(boolean soReuseaddr) {
			this.soReuseaddr = soReuseaddr;
		}

		@Override
		public String toString() {
			return "SocketOptions{tcpNodelay=" + tcpNodelay + ", soRcvbuf=" + soRcvbuf + ", soSndbuf=" + soSndbuf + "}";
		}

	}


}

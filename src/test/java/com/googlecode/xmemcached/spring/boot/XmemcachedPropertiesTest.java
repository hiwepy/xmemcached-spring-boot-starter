package com.googlecode.xmemcached.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link XmemcachedProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("XmemcachedProperties Tests")
class XmemcachedPropertiesTest {

    private XmemcachedProperties props;

    @BeforeEach
    void setUp() {
        props = new XmemcachedProperties();
    }

    @Nested
    @DisplayName("Default values")
    class DefaultValues {

        @Test void prefixConstant() { assertThat(XmemcachedProperties.PREFIX).isEqualTo("spring.memcached"); }
        @Test void defaultAddresses() { assertThat(props.getAddresses()).isNull(); }
        @Test void defaultWeights() { assertThat(props.getWeights()).isNull(); }
        @Test void defaultConnectTimeout() { assertThat(props.getConnectTimeout()).isEqualTo(Duration.ofMillis(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_CONNECT_TIMEOUT)); }
        @Test void defaultConnectionPoolSize() { assertThat(props.getConnectionPoolSize()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_CONNECTION_POOL_SIZE); }
        @Test void defaultFailureMode() { assertThat(props.isFailureMode()).isFalse(); }
        @Test void defaultSanitizeKeys() { assertThat(props.isSanitizeKeys()).isFalse(); }
        @Test void defaultOpTimeout() { assertThat(props.getOpTimeout()).isEqualTo(Duration.ofMillis(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_OP_TIMEOUT)); }
        @Test void defaultMaxQueuedNoReplyOperations() { assertThat(props.getMaxQueuedNoReplyOperations()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_MAX_QUEUED_NOPS); }
        @Test void defaultHealSessionInterval() { assertThat(props.getHealSessionInterval()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_HEAL_SESSION_INTERVAL); }
        @Test void defaultEnableHealSession() { assertThat(props.isEnableHealSession()).isTrue(); }
        @Test void defaultResolveInetAddresses() { assertThat(props.isResolveInetAddresses()).isTrue(); }
        @Test void defaultNetworking() { assertThat(props.getNetworking()).isNull(); }
        @Test void defaultSocketOptions() { assertThat(props.getSocketOptions()).isNull(); }

    }

    @Nested
    @DisplayName("Setters and getters")
    class SettersAndGetters {

        @Test void setAddresses() { props.setAddresses("127.0.0.1:11211"); assertThat(props.getAddresses()).isEqualTo("127.0.0.1:11211"); }
        @Test void setWeights() { props.setWeights("1,2,3"); assertThat(props.getWeights()).isEqualTo("1,2,3"); }
        @Test void setConnectTimeout() { Duration d = Duration.ofSeconds(5); props.setConnectTimeout(d); assertThat(props.getConnectTimeout()).isEqualTo(d); }
        @Test void setConnectionPoolSize() { props.setConnectionPoolSize(5); assertThat(props.getConnectionPoolSize()).isEqualTo(5); }
        @Test void setFailureMode() { props.setFailureMode(true); assertThat(props.isFailureMode()).isTrue(); }
        @Test void setSanitizeKeys() { props.setSanitizeKeys(true); assertThat(props.isSanitizeKeys()).isTrue(); }
        @Test void setOpTimeout() { Duration d = Duration.ofSeconds(10); props.setOpTimeout(d); assertThat(props.getOpTimeout()).isEqualTo(d); }
        @Test void setMaxQueuedNoReplyOperations() { props.setMaxQueuedNoReplyOperations(100); assertThat(props.getMaxQueuedNoReplyOperations()).isEqualTo(100); }
        @Test void setHealSessionInterval() { props.setHealSessionInterval(5000); assertThat(props.getHealSessionInterval()).isEqualTo(5000); }
        @Test void setEnableHealSession() { props.setEnableHealSession(false); assertThat(props.isEnableHealSession()).isFalse(); }
        @Test void setResolveInetAddresses() { props.setResolveInetAddresses(false); assertThat(props.isResolveInetAddresses()).isFalse(); }
        @Test void setNetworking() { XmemcachedProperties.Networking n = new XmemcachedProperties.Networking(); props.setNetworking(n); assertThat(props.getNetworking()).isSameAs(n); }
        @Test void setSocketOptions() { XmemcachedProperties.SocketOptions s = new XmemcachedProperties.SocketOptions(); props.setSocketOptions(s); assertThat(props.getSocketOptions()).isSameAs(s); }

    }

    @Test
    void toStringContainsKeyFields() {
        props.setAddresses("127.0.0.1:11211");
        assertThat(props.toString()).contains("127.0.0.1:11211");
    }

    @Nested
    @DisplayName("Networking inner class")
    class NetworkingTests {

        private XmemcachedProperties.Networking networking;

        @BeforeEach
        void setUp() {
            networking = new XmemcachedProperties.Networking();
        }

        @Test void defaultSessionReadBufferSize() { assertThat(networking.getSessionReadBufferSize()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_SESSION_READ_BUFF_SIZE); }
        @Test void defaultSessionIdleTimeout() { assertThat(networking.getSessionIdleTimeout()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_SESSION_IDLE_TIMEOUT); }
        @Test void defaultSoTimeout() { assertThat(networking.getSoTimeout()).isEqualTo(0); }
        @Test void defaultWriteThreadCount() { assertThat(networking.getWriteThreadCount()).isEqualTo(0); }
        @Test void defaultStatisticsServer() { assertThat(networking.isStatisticsServer()).isFalse(); }
        @Test void defaultHandleReadWriteConcurrently() { assertThat(networking.isHandleReadWriteConcurrently()).isTrue(); }
        @Test void defaultDispatchMessageThreadCount() { assertThat(networking.getDispatchMessageThreadCount()).isEqualTo(0); }
        @Test void defaultReadThreadCount() { assertThat(networking.getReadThreadCount()).isEqualTo(1); }
        @Test void defaultCheckSessionTimeoutInterval() { assertThat(networking.getCheckSessionTimeoutInterval()).isEqualTo(1000L); }

        @Test void setSessionReadBufferSize() { networking.setSessionReadBufferSize(2048); assertThat(networking.getSessionReadBufferSize()).isEqualTo(2048); }
        @Test void setSessionIdleTimeout() { networking.setSessionIdleTimeout(5000); assertThat(networking.getSessionIdleTimeout()).isEqualTo(5000); }
        @Test void setSoTimeout() { networking.setSoTimeout(3000); assertThat(networking.getSoTimeout()).isEqualTo(3000); }
        @Test void setWriteThreadCount() { networking.setWriteThreadCount(2); assertThat(networking.getWriteThreadCount()).isEqualTo(2); }
        @Test void setStatisticsServer() { networking.setStatisticsServer(true); assertThat(networking.isStatisticsServer()).isTrue(); }
        @Test void setHandleReadWriteConcurrently() { networking.setHandleReadWriteConcurrently(false); assertThat(networking.isHandleReadWriteConcurrently()).isFalse(); }
        @Test void setDispatchMessageThreadCount() { networking.setDispatchMessageThreadCount(4); assertThat(networking.getDispatchMessageThreadCount()).isEqualTo(4); }
        @Test void setReadThreadCount() { networking.setReadThreadCount(3); assertThat(networking.getReadThreadCount()).isEqualTo(3); }
        @Test void setSelectorPoolSize() { networking.setSelectorPoolSize(8); assertThat(networking.getSelectorPoolSize()).isEqualTo(8); }
        @Test void setCheckSessionTimeoutInterval() { networking.setCheckSessionTimeoutInterval(2000); assertThat(networking.getCheckSessionTimeoutInterval()).isEqualTo(2000); }
        @Test void setStatisticsInterval() { networking.setStatisticsInterval(10000); assertThat(networking.getStatisticsInterval()).isEqualTo(10000); }

        @Test
        void toStringContainsFields() {
            assertThat(networking.toString()).contains("sessionReadBufferSize").contains("readThreadCount");
        }

    }

    @Nested
    @DisplayName("SocketOptions inner class")
    class SocketOptionsTests {

        private XmemcachedProperties.SocketOptions socketOptions;

        @BeforeEach
        void setUp() {
            socketOptions = new XmemcachedProperties.SocketOptions();
        }

        @Test void defaultTcpNodelay() { assertThat(socketOptions.isTcpNodelay()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_TCP_NO_DELAY); }
        @Test void defaultSoRcvbuf() { assertThat(socketOptions.getSoRcvbuf()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_TCP_RECV_BUFF_SIZE); }
        @Test void defaultSoKeepalive() { assertThat(socketOptions.isSoKeepalive()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_TCP_KEEPLIVE); }
        @Test void defaultSoSndbuf() { assertThat(socketOptions.getSoSndbuf()).isEqualTo(net.rubyeye.xmemcached.MemcachedClient.DEFAULT_TCP_SEND_BUFF_SIZE); }
        @Test void defaultSoLinger() { assertThat(socketOptions.getSoLinger()).isEqualTo(0); }
        @Test void defaultSoReuseaddr() { assertThat(socketOptions.isSoReuseaddr()).isTrue(); }

        @Test void setTcpNodelay() { socketOptions.setTcpNodelay(true); assertThat(socketOptions.isTcpNodelay()).isTrue(); }
        @Test void setSoRcvbuf() { socketOptions.setSoRcvbuf(4096); assertThat(socketOptions.getSoRcvbuf()).isEqualTo(4096); }
        @Test void setSoKeepalive() { socketOptions.setSoKeepalive(true); assertThat(socketOptions.isSoKeepalive()).isTrue(); }
        @Test void setSoSndbuf() { socketOptions.setSoSndbuf(4096); assertThat(socketOptions.getSoSndbuf()).isEqualTo(4096); }
        @Test void setSoLinger() { socketOptions.setSoLinger(5); assertThat(socketOptions.getSoLinger()).isEqualTo(5); }
        @Test void setSoReuseaddr() { socketOptions.setSoReuseaddr(false); assertThat(socketOptions.isSoReuseaddr()).isFalse(); }

        @Test
        void toStringContainsFields() {
            assertThat(socketOptions.toString()).contains("tcpNodelay").contains("soRcvbuf");
        }

    }

}

package com.googlecode.xmemcached.spring.boot;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.transcoders.IntegerTranscoder;
import net.rubyeye.xmemcached.transcoders.LongTranscoder;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
public class XmemcachedOperationTemplate {

    public static final IntegerTranscoder TO_INTEGER = new IntegerTranscoder();
    public static final LongTranscoder TO_LONG = new LongTranscoder();
    public static final StringTranscoder TO_STRING = new StringTranscoder();

    XMemcachedClient memcachedClient;
    public XmemcachedOperationTemplate(XMemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            memcachedClient.set(key, -1, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new XMemcachedOperationException(e.getMessage());
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param seconds 时间(秒) time要&gt;=0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, int seconds) {
        try {
            if (seconds > 0) {
                return memcachedClient.set(key, seconds, value);
            } else {
                return set(key, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new XMemcachedOperationException(e.getMessage());
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param timeout 时间
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, Duration timeout) {
        if (Objects.isNull(timeout) || timeout.isNegative()) {
            return false;
        }
        try {
            memcachedClient.set(key, timeout.getSeconds(), value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new XMemcachedOperationException(e.getMessage());
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        try {
            return memcachedClient.get(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new XMemcachedOperationException(e.getMessage());
        }
    }

    public String getString(String key) {
        return getFor(key, TO_STRING);
    }

    public String getString(String key, String defaultVal) {
        String rtVal = getString(key);
        return Objects.nonNull(rtVal) ? rtVal : defaultVal;
    }

    public Long getLong(String key) {
        return getFor(key, TO_LONG);
    }

    public Long getLong(String key, long defaultVal) {
        Long rtVal = getLong(key);
        return Objects.nonNull(rtVal) ? rtVal : defaultVal;
    }

    public Integer getInteger(String key) {
        return getFor(key, TO_INTEGER);
    }

    public Integer getInteger(String key, int defaultVal) {
        Integer rtVal = getInteger(key);
        return Objects.nonNull(rtVal) ? rtVal : defaultVal;
    }

    public <T> T getFor(String key, Class<T> clazz) {
        //return getFor(key, member -> clazz.cast(member));
        return null;
    }

    /**
     * 根据key获取值，并按Function函数进行转换
     *
     * @param key    键
     * @param transcoder 对象转换函数
     * @param <T>   指定的类型
     * @return xx
     */
    public <T> T getFor(String key, Transcoder<T> transcoder) {
        try {
            return memcachedClient.get(key, transcoder);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new XMemcachedOperationException(e.getMessage());
        }
    }


}

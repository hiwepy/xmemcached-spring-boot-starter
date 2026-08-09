package com.googlecode.xmemcached.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link XMemcachedOperationException}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("XMemcachedOperationException Tests")
class XMemcachedOperationExceptionTest {

    @Test
    void constructorWithMessage() {
        XMemcachedOperationException ex = new XMemcachedOperationException("test error");
        assertThat(ex.getMessage()).isEqualTo("test error");
        assertThat(ex).isInstanceOf(RuntimeException.class);
    }

    @Test
    void constructorWithMessageAndCause() {
        RuntimeException cause = new RuntimeException("root cause");
        XMemcachedOperationException ex = new XMemcachedOperationException("test error", cause);
        assertThat(ex.getMessage()).isEqualTo("test error");
        assertThat(ex.getCause()).isSameAs(cause);
    }

}

package com.googlecode.xmemcached.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AuthInfoProvider}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("AuthInfoProvider Tests")
class AuthInfoProviderTest {

    @Test
    void defaultGetAuthInfoMapReturnsEmptyMap() {
        AuthInfoProvider provider = new AuthInfoProvider() {};
        assertThat(provider.getAuthInfoMap()).isEmpty();
    }

}

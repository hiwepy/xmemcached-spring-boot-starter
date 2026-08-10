package com.googlecode.xmemcached.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link XmemcachedKeyConstant}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("XmemcachedKeyConstant Tests")
class XmemcachedKeyConstantTest {

    @Test
    void userGeoLocationKey() {
        assertThat(XmemcachedKeyConstant.USER_GEO_LOCATION_KEY).isEqualTo("user:geo:location");
    }

}

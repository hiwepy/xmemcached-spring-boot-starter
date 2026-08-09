package com.googlecode.xmemcached.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link XmemcachedKey}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("XmemcachedKey Tests")
class XmemcachedKeyTest {

    @Nested
    @DisplayName("Constants")
    class Constants {

        @Test void redisPrefix() { assertThat(XmemcachedKey.REDIS_PREFIX).isEqualTo("rds"); }
        @Test void delimiter() { assertThat(XmemcachedKey.DELIMITER).isEqualTo(":"); }

    }

    @Nested
    @DisplayName("getKeyStr")
    class GetKeyStr {

        @Test void singleArg() {
            String key = XmemcachedKey.getKeyStr("test");
            assertThat(key).isEqualTo("rds:test");
        }

        @Test void multipleArgs() {
            String key = XmemcachedKey.getKeyStr("a", "b", "c");
            assertThat(key).isEqualTo("rds:a:b:c");
        }

        @Test void nullArgIsSkipped() {
            String key = XmemcachedKey.getKeyStr("a", null, "c");
            assertThat(key).isEqualTo("rds:a:c");
        }

        @Test void emptyArgIsSkipped() {
            String key = XmemcachedKey.getKeyStr("a", "", "c");
            assertThat(key).isEqualTo("rds:a:c");
        }

        @Test void numericArg() {
            String key = XmemcachedKey.getKeyStr(233);
            assertThat(key).isEqualTo("rds:233");
        }

    }

    @Nested
    @DisplayName("getThreadKeyStr")
    class GetThreadKeyStr {

        @Test void withPrefixAndArgs() {
            String key = XmemcachedKey.getThreadKeyStr("prefix", "a", "b");
            assertThat(key).startsWith("prefix:");
            assertThat(key).contains(":a:b");
        }

        @Test void nullArgIsSkipped() {
            String key = XmemcachedKey.getThreadKeyStr("prefix", null, "b");
            assertThat(key).contains(":b");
        }

    }

    @Nested
    @DisplayName("Enum values")
    class EnumValues {

        @Test void userGeoLocation() {
            XmemcachedKey key = XmemcachedKey.USER_GEO_LOCATION;
            assertThat(key).isNotNull();
            assertThat(key.getDesc()).isEqualTo("用户坐标");
        }

        @Test void getKeyReturnsNonNull() {
            String key = XmemcachedKey.USER_GEO_LOCATION.getKey();
            assertThat(key).isNotNull().isNotEmpty();
        }

        @Test void getKeyWithParamReturnsNonNull() {
            String key = XmemcachedKey.USER_GEO_LOCATION.getKey("user123");
            assertThat(key).isNotNull().isNotEmpty();
        }

    }

    @Test
    void mainDoesNotThrow() {
        XmemcachedKey.main(new String[]{});
    }

}

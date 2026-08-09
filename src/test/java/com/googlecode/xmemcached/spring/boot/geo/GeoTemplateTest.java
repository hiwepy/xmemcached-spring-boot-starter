package com.googlecode.xmemcached.spring.boot.geo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link GeoTemplate}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("GeoTemplate Tests")
class GeoTemplateTest {

    private GeoTemplate geoTemplate;

    @BeforeEach
    void setUp() {
        geoTemplate = new GeoTemplate();
    }

    @Test
    void getDistanceReturnsPositiveValue() {
        // Beijing to Shanghai approximately 1068 km
        double distance = geoTemplate.getDistance(39.9042, 116.4074, 31.2304, 121.4737);
        assertThat(distance).isGreaterThan(1000000); // > 1000 km in meters
    }

    @Test
    void getDistanceSamePointReturnsZero() {
        double distance = geoTemplate.getDistance(39.9042, 116.4074, 39.9042, 116.4074);
        assertThat(distance).isCloseTo(0.0, within(1.0));
    }

    @Test
    void getSphereDistanceReturnsPositiveValue() {
        double distance = geoTemplate.getSphereDistance(39.9042, 116.4074, 31.2304, 121.4737);
        assertThat(distance).isGreaterThan(1000000);
    }

    @Test
    void getWGS84DistanceReturnsPositiveValue() {
        double distance = geoTemplate.getWGS84Distance(39.9042, 116.4074, 31.2304, 121.4737);
        assertThat(distance).isGreaterThan(1000000);
    }

}

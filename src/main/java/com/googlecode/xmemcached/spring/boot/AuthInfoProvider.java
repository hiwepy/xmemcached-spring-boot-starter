package com.googlecode.xmemcached.spring.boot;

import net.rubyeye.xmemcached.auth.AuthInfo;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**\n * Auto-configuration for AuthInfoProvider.\n *\n * @author [@Loong Wan](https://github.com/loong10k)\n * @since 1.0.0\n */
public interface AuthInfoProvider {

    default Map<InetSocketAddress, AuthInfo> getAuthInfoMap(){
        return new HashMap<>();
    };

}

package com.googlecode.xmemcached.spring.boot;

import net.rubyeye.xmemcached.auth.AuthInfo;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public interface AuthInfoProvider {

    default Map<InetSocketAddress, AuthInfo> getAuthInfoMap(){
        return new HashMap<>();
    };

}

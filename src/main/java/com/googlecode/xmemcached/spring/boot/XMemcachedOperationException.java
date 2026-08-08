package com.googlecode.xmemcached.spring.boot;

/**
 * @author [@Loong Wan](https://github.com/loong10k)
 */
@SuppressWarnings("serial")
public class XMemcachedOperationException extends RuntimeException {

	public XMemcachedOperationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public XMemcachedOperationException(String msg) {
		super(msg);
	}

}

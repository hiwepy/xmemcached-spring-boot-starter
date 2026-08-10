package com.googlecode.xmemcached.spring.boot;

/**
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
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

/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.spring.webmvc;

/**
 * 提供执行代理的方法接口.
 */
public interface Action {
    
    /**
     * 用于实现的方法执行体.
     */
    void invoke(PostResult postResult);
}

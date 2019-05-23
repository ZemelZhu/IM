package com.im.zemel.inf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IComponent {
    static final Logger logger = LoggerFactory.getLogger(IComponent.class);
    /**
     * 获取组件名称
     *
     * @return
     */
    String getName();

    /**
     * 启动组件
     *
     * @return
     */
    boolean start();

}

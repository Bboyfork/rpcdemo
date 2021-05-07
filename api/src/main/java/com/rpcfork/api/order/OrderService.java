package com.rpcfork.api.order;

/**
 * 订单
 * @author Bboy_fork
 * @date 2021年5月6日10:33:57
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderContent 订单内容
     */
    void create(String orderContent);
}
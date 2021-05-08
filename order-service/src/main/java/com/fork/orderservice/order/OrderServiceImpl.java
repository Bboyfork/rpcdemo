package com.fork.orderservice.order;

import com.rpcfork.api.order.OrderService;
import com.rpcfork.api.sms.SmsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bboy_fork
 * @date 2021年5月6日10:49:59
 * */
public class OrderServiceImpl implements OrderService {

    @DubboReference
    SmsService smsService;

    @Override
    public void create(String orderContent) {
        smsService.send("66",orderContent);
    }
}

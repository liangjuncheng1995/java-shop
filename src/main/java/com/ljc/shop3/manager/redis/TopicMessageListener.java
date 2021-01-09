package com.ljc.shop3.manager.redis;

import com.ljc.shop3.bo.OrderMessageBO;
import com.ljc.shop3.service.CouponBackService;
import com.ljc.shop3.service.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageListener implements MessageListener {
    @Autowired
    private OrderCancelService orderCancelService;

    @Autowired
    private CouponBackService couponBackService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String expiredKey = new String(body);
        String topic = new String(channel);

        OrderMessageBO messageBO = new OrderMessageBO(expiredKey);
        orderCancelService.cancel(messageBO);
        couponBackService.returnBack(messageBO);
    }
}

package com.ljc.shop3.service;

import com.github.wxpay.sdk.LinWxPayConfigSample;
import com.github.wxpay.sdk.WXPay;
import com.ljc.shop3.core.LocalUser;
import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.exception.http.ServerErrorException;
import com.ljc.shop3.model.Order;
import com.ljc.shop3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WxPaymentService {
    @Autowired
    private OrderRepository orderRepository;


    private static LinWxPayConfigSample linWxPayConfigSample = new LinWxPayConfigSample();

    public Map<String, String> preOrder(Long oid) {

        Long uid = LocalUser.getUser().getId();
        Optional<Order> optionalOrder = this.orderRepository.findFirstByUserIdAndId(uid, oid);
        Order order = optionalOrder.orElseThrow(
                () -> new NotFoundException(50009));
        if(order.needCancel()) {
            throw new ForbiddenException(50010);
        }
        WXPay wxPay = this.assembleWxPayConfig();
//        wxPay.unifiedOrder()
        return null;
    }

    private Map<String, String> makePreOrderParams() {
        Map<String, String> data = new HashMap<>();
        return null;
    }

    private WXPay assembleWxPayConfig() {
        WXPay wxPay;
        try {
            wxPay = new WXPay(WxPaymentService.linWxPayConfigSample);
        } catch(Exception ex) {
           throw new ServerErrorException(9999);
        }
        return wxPay;
    }


}


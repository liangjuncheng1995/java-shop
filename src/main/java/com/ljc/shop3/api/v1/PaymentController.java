package com.ljc.shop3.api.v1;


import com.github.wxpay.sdk.LinWxPayConfigSample;
import com.ljc.shop3.core.LocalUser;
import com.ljc.shop3.core.interceptors.ScopeLevel;
import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.model.Order;
import com.ljc.shop3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Positive;
import java.util.Map;
import java.util.Optional;

@RequestMapping("payment")
@RestController
@Validated
public class PaymentController {



    @PostMapping("/pay/order/{id}")
    @ScopeLevel
    public Map<String, String> preWxOrder(@PathVariable(name = "id") @Positive Long oid) {


        return null;
    }

    @RequestMapping("/wx/notify")
    public String payCallback(HttpServletRequest request,
                              HttpServletResponse response) {
        return null;
    }

//    private
}

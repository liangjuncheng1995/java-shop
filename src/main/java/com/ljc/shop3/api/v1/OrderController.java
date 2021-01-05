package com.ljc.shop3.api.v1;

import com.ljc.shop3.bo.PageCounter;
import com.ljc.shop3.core.LocalUser;
import com.ljc.shop3.core.interceptors.ScopeLevel;
import com.ljc.shop3.dto.OrderDTO;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.logic.OrderChecker;
import com.ljc.shop3.model.Order;
import com.ljc.shop3.service.OrderService;
import com.ljc.shop3.util.CommonUtil;
import com.ljc.shop3.vo.OrderIdVO;
import com.ljc.shop3.vo.OrderPureVO;
import com.ljc.shop3.vo.OrderSimplifyVO;
import com.ljc.shop3.vo.PagingDozer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("order")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Value("${shop3.order.pay-time-limit}")
    private Long payTimeLimit;

    @PostMapping("")
    @ScopeLevel()
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();

        OrderChecker orderChecker = this.orderService.isOk(uid, orderDTO);

        long oid =  this.orderService.placeOrder(uid, orderDTO, orderChecker);

        return new OrderIdVO(oid);

    }

    @ScopeLevel
    @GetMapping("/detail/{id}")
    public OrderPureVO getOrderDetail(@PathVariable(name = "id") Long oid) {
        Optional<Order> orderOptional = this.orderService.getOrderDetail(oid);
        return orderOptional.map((o) -> new OrderPureVO(o, payTimeLimit))
                .orElseThrow(() -> new NotFoundException(50009));
    }


    @GetMapping("/status/unpaid")
    @ScopeLevel
    public PagingDozer getUnpaid(@RequestParam(defaultValue = "0") Integer start,
                                            @RequestParam(defaultValue = "10") Integer count) {
        PageCounter page = CommonUtil.coverToPageParameter(start, count);
        Page<Order> orderPage = this.orderService.getUnpaid(page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach((o) ->((OrderSimplifyVO)o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

    @GetMapping("/by/status/{status}")
    @ScopeLevel
    public PagingDozer getByStatus(@PathVariable int status,
                                   @RequestParam(defaultValue = "0") Integer start,
                                   @RequestParam(defaultValue = "10") Integer count) {
        PageCounter page = CommonUtil.coverToPageParameter(start, count);
        Page<Order> orderPage = this.orderService.getByStatus(status,page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach(o ->((OrderSimplifyVO)o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

}

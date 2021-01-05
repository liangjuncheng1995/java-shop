package com.ljc.shop3.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ljc.shop3.dto.OrderAddressDTO;
import com.ljc.shop3.util.GenericAndJson;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
@Table(name = "`Order`")
public class Order extends BaseEntity{
//    全局的ID生成器
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalCount;



    private String snapImg;
    private String snapTitle;
    private String snapItems;
    private String snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;

    private Date expiredTime;
    private Date placedTime;


    public List<OrderSku> getSnapItems() {
        if (this.snapItems == null) {
            return null;
        }
        List<OrderSku> orderSkuList = GenericAndJson.jsonToObject(this.snapItems,
                new TypeReference<List<OrderSku>>() {
                });
        return orderSkuList;
    }

    public void setSnapItems(List<OrderSku> orderSkuList) {
        this.snapItems = GenericAndJson.objectToJson(orderSkuList);
    }

    public OrderAddressDTO getSnapAddress() {
        if(this.snapAddress == null) {
            return null;
        }
        OrderAddressDTO o = GenericAndJson.jsonToObject(this.snapAddress,
                new TypeReference<OrderAddressDTO>() {
                });
        return o;
    }

    public void setSnapAddress(OrderAddressDTO address) {
        this.snapAddress = GenericAndJson.objectToJson(address);
    }

}

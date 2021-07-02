package xyz.linin.bookstore_backend.dto;

import lombok.Data;
import xyz.linin.bookstore_backend.constants.OrderState;

import java.util.Date;

@Data
public class OrderDto {
    private Integer id;

    private Integer userId;

    private Date time;

    private OrderState state;

    private String address;
}

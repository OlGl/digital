package by.glavdel.digital.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private String username;
    private BigDecimal summa;
    private LocalDate orderDate;
}
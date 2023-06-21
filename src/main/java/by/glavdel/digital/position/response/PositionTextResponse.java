package by.glavdel.digital.controller.position.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PositionTextResponse {
    private String name;
    private BigDecimal price;
    private String message;
}
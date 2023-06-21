package by.glavdel.digital.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ItemTextResponse {
    private String name;
    private BigDecimal price;
    private String message;
}
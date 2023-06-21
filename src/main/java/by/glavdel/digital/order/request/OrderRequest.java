package by.glavdel.digital.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderRequest {

    private Long userId;

    private Map<Long, Double> itemsInOrderWithCount;
}

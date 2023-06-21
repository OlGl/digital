package by.glavdel.digital.mapper;

import by.glavdel.digital.item.entity.Item;
import by.glavdel.digital.order.entity.Order;
import by.glavdel.digital.order.response.OrderResponse;
import by.glavdel.digital.order.request.OrderRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    public OrderResponse entityToResponse(Order entity) {
        return OrderResponse.builder()
                .orderId(entity.getId())
                .userId(entity.getUser().getId())
                .summa(entity.getSumma())
                .orderDate(entity.getOrderDate())
                .itemsId(entity.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public Order requestToEntity(@NotNull OrderRequest request) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setIsRemove(false);
        return order;
    }
}


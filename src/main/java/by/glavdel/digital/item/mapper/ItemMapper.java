package by.glavdel.digital.mapper;

import by.glavdel.digital.entity.Item;
import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.request.ItemRequest;
import by.glavdel.digital.response.ItemResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    public ItemResponse entityToResponse(Item entity) {
        return ItemResponse.builder()
                .itemId(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }

    public Item requestToEntity(@NotNull ItemRequest request) {
        Item item = new Item();
        item.setName(request.getName());
        if (Double.parseDouble(request.getPrice()) < 0) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_PRICE);
        } else {
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
        }
        item.setDescription(request.getDescription());
        return item;
    }
}


package by.glavdel.digital.controller.position.mapper;

import by.glavdel.digital.controller.position.entity.Position;
import by.glavdel.digital.controller.position.response.PositionResponse;
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
public class PositionMapper {
    public PositionResponse entityToResponse(Position entity) {
        return PositionResponse.builder()
                .positionId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .actualCount(entity.getActualCount())
                .build();
    }

    public Item requestToEntity(@NotNull PositionRequest request) {
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


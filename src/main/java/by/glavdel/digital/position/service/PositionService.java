package by.glavdel.digital.controller.position.service;

import by.glavdel.digital.controller.position.mapper.PositionMapper;
import by.glavdel.digital.controller.position.repository.PositionRepository;
import by.glavdel.digital.controller.position.response.PositionResponse;
import by.glavdel.digital.entity.Item;
import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.request.ItemRequest;
import by.glavdel.digital.request.ItemUpdateRequest;
import by.glavdel.digital.response.ItemTextResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class PositionService {

    private final PositionRepository repository;
    private final PositionMapper positionMapper;

    public PositionResponse getById(Long id) {
        log.info("...Method getById");
        return positionMapper.entityToResponse(repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.POSITION_NOT_FOUND)));
    }

    public PositionTextResponse delete(Long id) {
        log.info("...Method delete");
        Item item = repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
        item.setIsRemove(true);
        repository.save(item);
        log.info("...Item was deleted");
        return ItemTextResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .message("Item was deleted.")
                .build();
    }

    public PositionResponse create(ItemRequest request) {
        return positionMapper.entityToResponse(repository.save(positionMapper.requestToEntity(request)));
    }

    public PositionResponse update(Long id, ItemUpdateRequest request) {
        log.info("...Method update");
        Item item = repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
        item.setName(StringUtils.isNotEmpty(request.getName())
                ? request.getName()
                : item.getName());
        if (Double.parseDouble(request.getPrice()) < 0) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_PRICE);
        } else {
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
        }
        item.setDescription(StringUtils.isNotEmpty(request.getDescription())
                ? request.getDescription()
                : item.getDescription());
        Item updatedItem = repository.save(item);

        return positionMapper.entityToResponse(updatedItem);
    }
}

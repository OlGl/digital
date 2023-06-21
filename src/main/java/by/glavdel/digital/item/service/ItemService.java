package by.glavdel.digital.service;

import by.glavdel.digital.item.entity.Item;
import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.item.mapper.ItemMapper;
import by.glavdel.digital.item.repository.ItemRepository;
import by.glavdel.digital.item.request.ItemRequest;
import by.glavdel.digital.item.request.ItemUpdateRequest;
import by.glavdel.digital.item.responce.ItemResponse;
import by.glavdel.digital.item.responce.ItemTextResponse;
import by.glavdel.digital.position.repository.PositionRepository;
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
public class ItemService {

    private final ItemRepository itemRepository;
    private final PositionRepository positionRepository;
    private final ItemMapper itemMapper;

    public ItemResponse getById(Long id) {
        log.info("...Method getById");
        return itemMapper.entityToResponse(itemRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND)));
    }

    public ItemTextResponse delete(Long id) {
        log.info("...Method delete");
        Item item = itemRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
        item.setIsRemove(true);
        itemRepository.save(item);
        log.info("...Item was deleted");
        return ItemTextResponse.builder()
                .id(item.getId())
                .message("Item was deleted.")
                .build();
    }

    public ItemResponse create(ItemRequest request) {
        Item item = itemMapper.requestToEntity(request);
        if(positionRepository.findByIdAndIsRemoveFalse(request.getPositionId()).isPresent()) {
            item.setPosition(positionRepository.findByIdAndIsRemoveFalse(request.getPositionId()).get());
        }else{
            throw new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND);
        }
        return itemMapper.entityToResponse(itemRepository.save(itemMapper.requestToEntity(request)));
    }

    public ItemResponse update(Long id, ItemUpdateRequest request) {
        log.info("...Method update");
        Item item = itemRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
        if (StringUtils.isNotEmpty(request.getPrice()) && Double.parseDouble(request.getPrice()) < 0) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_PRICE);
        } else {
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
        }
        if (request.getActualCount()!=null && request.getActualCount() < 0) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_COUNT);
        } else {
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
        }
        Item updatedItem = itemRepository.save(item);

        return itemMapper.entityToResponse(updatedItem);
    }
}

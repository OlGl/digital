package by.glavdel.digital.service;

import by.glavdel.digital.item.entity.Item;
import by.glavdel.digital.order.entity.Order;
import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.mapper.OrderMapper;
import by.glavdel.digital.item.repository.ItemRepository;
import by.glavdel.digital.repository.OrderRepository;
import by.glavdel.digital.user.repository.UserRepository;
import by.glavdel.digital.item.request.ItemUpdateRequest;
import by.glavdel.digital.request.OrderRequest;
import by.glavdel.digital.item.responce.ItemResponse;
import by.glavdel.digital.order.response.OrderResponse;
import by.glavdel.digital.order.response.OrderTextResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private final OrderMapper orderMapper;

    public OrderResponse getById(Long id) {
        log.info("...Method getById");
        return orderMapper.entityToResponse(orderRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ORDER_NOT_FOUND)));
    }

    public OrderTextResponse delete(Long id) {
        log.info("...Method delete");
        Order order = orderRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ORDER_NOT_FOUND));
        order.setIsRemove(true);
        orderRepository.save(order);
        log.info("...Order was deleted");
        return OrderTextResponse.builder()
                .id(order.getId())
                .summa(order.getSumma())
                .message("Order was deleted.")
                .build();
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.requestToEntity(request);
        if (userRepository.findById(request.getUserId()).isPresent()) {
            order.setUser(userRepository.findById(request.getUserId()).get());
        } else {
            throw new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND);
        }

        List<Item> itemList = request.getItemsInOrderWithCount().keySet().stream()
                .map(id -> itemRepository.findByIdAndIsRemoveFalse(id).get())
                .toList();
        order.setItems(itemList);
        order.setSumma(findSummaAllItems(request.getItemsInOrderWithCount()));


        itemList.forEach(item -> item.getOrders().add(order));
        return orderMapper.entityToResponse(orderRepository.save(orderMapper.requestToEntity(request)));
    }

    private BigDecimal findSummaAllItems(Map<Long, Double> itemsInOrderWithCount) {
        BigDecimal summa = BigDecimal.valueOf(0);
        itemsInOrderWithCount.keySet().stream().map(itemId-> {
            Item item = itemRepository.findByIdAndIsRemoveFalse(itemId)
                    .orElseThrow(()->new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
            Double countInOrder = itemsInOrderWithCount.get(itemId);
            checkCount(item,countInOrder);
            item.setActualCount(item.getActualCount()-countInOrder);
            summa = summa + item.getPrice().multiply(BigDecimal.valueOf(countInOrder));
        })
    }

    private void checkCount(Item item, Double count) {
        if(item.getActualCount()< count){
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_ENOUGH_COUNT);
        }
    }

//    public ItemResponse update(Long id, ItemUpdateRequest request) {
//        log.info("...Method update");
//        Item item = orderRepository.findByIdAndIsRemoveFalse(id)
//                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
//        item.setName(StringUtils.isNotEmpty(request.getName())
//                ? request.getName()
//                : item.getName());
//        if (Double.parseDouble(request.getPrice()) < 0) {
//            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_PRICE);
//        } else {
//            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
//        }
//        item.setDescription(StringUtils.isNotEmpty(request.getDescription())
//                ? request.getDescription()
//                : item.getDescription());
//        Item updatedItem = orderRepository.save(item);
//
//        return orderMapper.entityToResponse(updatedItem);
//    }
}

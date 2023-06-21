package by.glavdel.digital.controller;

import by.glavdel.digital.request.OrderRequest;
import by.glavdel.digital.response.OrderResponse;
import by.glavdel.digital.response.OrderTextResponse;
import by.glavdel.digital.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        log.info("...Method getById");
        return orderService.getById(id);
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("...Method createItem");
        return orderService.create(request);
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id,
                               @Valid @RequestBody OrderUpdateRequest request) {
        log.info("...Method update");
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public OrderTextResponse delete(@PathVariable Long id) {
        log.info("...Method delete");
        return orderService.delete(id);
    }
}

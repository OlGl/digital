package by.glavdel.digital.controller;

import by.glavdel.digital.request.ItemRequest;
import by.glavdel.digital.request.ItemUpdateRequest;
import by.glavdel.digital.response.ItemResponse;
import by.glavdel.digital.response.ItemTextResponse;
import by.glavdel.digital.service.ItemService;
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
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ItemResponse getById(@PathVariable Long id) {
        log.info("...Method getById");
        return itemService.getById(id);
    }

    @PostMapping
    public ItemResponse createItem(@Valid @RequestBody ItemRequest request) {
        log.info("...Method createItem");
        return itemService.create(request);
    }

    @PutMapping("/{id}")
    public ItemResponse update(@PathVariable Long id,
                               @Valid @RequestBody ItemUpdateRequest request) {
        log.info("...Method update");
        return itemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ItemTextResponse delete(@PathVariable Long id) {
        log.info("...Method delete");
        return itemService.delete(id);
    }
}

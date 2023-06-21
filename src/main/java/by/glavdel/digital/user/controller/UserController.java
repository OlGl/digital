package by.glavdel.digital.controller;

import by.glavdel.digital.request.UserRequest;
import by.glavdel.digital.request.UserUpdateRequest;
import by.glavdel.digital.response.UserTextResponse;
import by.glavdel.digital.response.UserResponse;
import by.glavdel.digital.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        log.info("...Method getById");
        return userService.getById(id);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        log.info("...Method createUser");
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,
                               @Valid @RequestBody UserUpdateRequest request) {
        log.info("...Method update");
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public UserTextResponse delete(@PathVariable Long id) {
        log.info("...Method delete");
        return userService.delete(id);
    }
}

package by.glavdel.digital.mapper;

import by.glavdel.digital.entity.Role;
import by.glavdel.digital.entity.User;
import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.request.UserRequest;
import by.glavdel.digital.response.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponse entityToResponse(@NotNull User entity) {
        return UserResponse.builder()
                .userId(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .dateBirth(entity.getDateBirth())
                .build();
    }

    public User requestToEntity(@NotNull UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setDateBirth(request.getDateBirth());
        user.setRole(checkRoleEnum(request.getRole()));
        return user;
    }

    private Role checkRoleEnum(String value) {
        if (StringUtils.isEmpty(value)) {
            return Role.ROLE_USER;
        } else if (EnumUtils.isValidEnumIgnoreCase(Role.class, value)) {
            return Role.valueOf(value.toUpperCase());
        } else {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_ROLE);
        }
    }
}

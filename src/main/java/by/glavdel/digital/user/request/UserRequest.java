package by.glavdel.digital.request;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty
    @Pattern(regexp = "^[\\pL\\d]+[\\pL\\pP\\pZ\\d]{2,}$",
            message = ExceptionMessageConstants.ERROR_USERNAME_PATTERN)
    private String username;

    @NotEmpty
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotNull
    @Past
    private LocalDate dateBirth;

    private String role;

}

package by.glavdel.digital.request;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class UserUpdateRequest {

    @NotEmpty
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @Pattern(regexp = "^[\\pL\\d]+[\\pL\\pP\\pZ\\d]{2,}$",
            message = ExceptionMessageConstants.ERROR_USERNAME_PATTERN)
    private String username;

    @Past
    private LocalDate dateBirth;

}

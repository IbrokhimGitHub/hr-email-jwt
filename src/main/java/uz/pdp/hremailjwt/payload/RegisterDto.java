package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @Size(min = 3, max = 50)
    @NotNull
    private String firstName;


    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;//user emaili

    @NotNull
    private String password;

    @NotNull
    private String role;
}

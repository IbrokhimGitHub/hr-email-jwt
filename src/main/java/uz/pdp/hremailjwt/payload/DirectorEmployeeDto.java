package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorEmployeeDto {
    private String fullName;
    private String email;
    private String password;
    private String directorSecretKey;
}

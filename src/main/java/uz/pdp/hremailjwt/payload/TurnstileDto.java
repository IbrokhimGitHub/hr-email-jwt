package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnstileDto {
    @Email
    private String createdEmployee;

    private Timestamp firstTime;
    private Timestamp secondTime;
}

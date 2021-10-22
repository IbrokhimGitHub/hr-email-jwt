package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hremailjwt.entity.enums.MonthNameEnum;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeSalaryDto {

//    private String uuid;
//    private boolean is_given;

    private String firstName;

    private String email;

    @Enumerated(EnumType.STRING)
    private MonthNameEnum monthName;

    private double salaryAmount;
}

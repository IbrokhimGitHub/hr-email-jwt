package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.hremailjwt.entity.Salary;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDto {
    private Double salaryAmount;

}

package uz.pdp.hremailjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hremailjwt.entity.enums.MonthNameEnum;
import uz.pdp.hremailjwt.payload.EmployeeSalaryDto;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
//@SqlResultSetMapping(        name = "EmployeeSalaryDto1")
public class Salary {
    @Id
    @GeneratedValue
    private UUID uuid;


    @ManyToOne
    MonthName monthName;

    @ManyToOne
    private Employee employee;

    @Column(nullable = false)
    private boolean isGiven;

    @Column(nullable = false)
    private Double salaryAmount;

//    @Column(nullable = false)
//    private Double amount;


}

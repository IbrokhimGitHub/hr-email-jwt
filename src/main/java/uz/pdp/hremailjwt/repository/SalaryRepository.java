package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.MonthName;
import uz.pdp.hremailjwt.entity.Salary;
import uz.pdp.hremailjwt.entity.enums.MonthNameEnum;
import uz.pdp.hremailjwt.payload.EmployeeSalaryDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    List<Salary> findAllByEmployee(Employee employee);

    List<Salary> findAllByMonthName(MonthName monthName);






}

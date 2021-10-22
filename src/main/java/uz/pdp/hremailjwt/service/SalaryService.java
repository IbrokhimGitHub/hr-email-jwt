package uz.pdp.hremailjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.MonthName;
import uz.pdp.hremailjwt.entity.Salary;

import uz.pdp.hremailjwt.entity.enums.MonthNameEnum;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.EmployeeSalaryDto;
import uz.pdp.hremailjwt.payload.SalaryDto;
import uz.pdp.hremailjwt.repository.EmployeeRepository;
import uz.pdp.hremailjwt.repository.MonthNameRepository;

import uz.pdp.hremailjwt.repository.SalaryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MonthNameRepository monthNameRepository;



    public List<EmployeeSalaryDto>getByEmployee(){
return null;
    }
    public ApiResponse paySalary(SalaryDto salaryDto){
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(salaryDto.getEmail());
        Optional<MonthName> optionalMonthName = monthNameRepository.findByMonthName(MonthNameEnum.valueOf(salaryDto.getMonthName()));
        if (!optionalEmployee.isPresent()||!optionalMonthName.isPresent()) {
            return new ApiResponse("such employee or month name doesn't find",false);
        }
        Employee employee = optionalEmployee.get();
//        Optional<SalaryAmount> optionalSalaryAmount = salaryAmountRepository.findByEmployee(employee);
//        if (!optionalSalaryAmount.isPresent()) {
//            return new ApiResponse("such salary amount doesn't find",false);
//        }

        Salary salary=new Salary();
        salary.setMonthName(optionalMonthName.get());
        salary.setEmployee(employee);
        salary.setGiven(true);
        salary.setSalaryAmount(salaryDto.getSalaryAmount());
        salaryRepository.save(salary);
        return new ApiResponse("Salary for employee "+employee.getEmail()+" is set",true);








    }
    public List<Salary> getByEmployeeEmail(String email){
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);

        List<Salary> allByEmployee = salaryRepository.findAllByEmployee(optionalEmployee.get());
        return allByEmployee;
    }
    public List<Salary> getByMonthName(String monthName){
        Optional<MonthName> optionalMonthName = monthNameRepository.findByMonthName(MonthNameEnum.valueOf(monthName));
        List<Salary> allByMonthName = salaryRepository.findAllByMonthName(optionalMonthName.get());
        return allByMonthName;
    }


}

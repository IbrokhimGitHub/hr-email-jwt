package uz.pdp.hremailjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hremailjwt.entity.Salary;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.EmployeeSalaryDto;
import uz.pdp.hremailjwt.payload.SalaryDto;
import uz.pdp.hremailjwt.service.SalaryService;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    SalaryService salaryService;
    @PostMapping("/pay")
    public HttpEntity<?> pay(@RequestBody SalaryDto salaryDto){
       ApiResponse apiResponse= salaryService.paySalary(salaryDto);

       return ResponseEntity.status(apiResponse.isSuccess()?204:405).body(apiResponse);
    }
    @PostMapping("getByEmployee")
    public HttpEntity<?> getByEmail(@RequestBody  @Email String email){
        List<Salary> byEmployeeEmail = salaryService.getByEmployeeEmail(email);

        return ResponseEntity.status(204).body(byEmployeeEmail);
    }
    @PostMapping("getByMonthName")
    public HttpEntity<?> getByMonthName(@RequestBody   String monthName){
        List<Salary> byMonthName = salaryService.getByMonthName(monthName);

        return ResponseEntity.status(204).body(byMonthName);
    }
}

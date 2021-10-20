package uz.pdp.hremailjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> employeeList(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }
}

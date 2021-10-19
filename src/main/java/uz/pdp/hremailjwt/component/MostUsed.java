package uz.pdp.hremailjwt.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.Role;
import uz.pdp.hremailjwt.repository.EmployeeRepository;

import java.util.Set;

@Component
public class MostUsed {
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee getCurrentEmployee(){
        Employee currentEmployee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentEmployee;
    }
    public Set<Role> getCurrentEmployeeRole(){
        Set<Role> roles = getCurrentEmployee().getRoles();
        return roles;
    }

}

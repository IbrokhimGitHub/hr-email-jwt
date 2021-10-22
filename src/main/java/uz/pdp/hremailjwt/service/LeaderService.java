package uz.pdp.hremailjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.component.MostUsed;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.Role;
import uz.pdp.hremailjwt.entity.Task;
import uz.pdp.hremailjwt.entity.Turnstile;
import uz.pdp.hremailjwt.entity.enums.TaskStatus;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.TaskDto;
import uz.pdp.hremailjwt.payload.TurnstileDto;
import uz.pdp.hremailjwt.repository.EmployeeRepository;
import uz.pdp.hremailjwt.repository.TaskRepository;
import uz.pdp.hremailjwt.repository.TurnstileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LeaderService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TurnstileRepository turnstileRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MostUsed mostUsed;

    public ApiResponse taskListByStatus(TaskDto taskDto) {
        Employee currentEmployee = mostUsed.getCurrentEmployee();
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(taskDto.getResponsibleEmployeeEmail());
        if (!optionalEmployee.isPresent()) {
            return new ApiResponse("such employee doesn't find", false);
        }
        Set<Role> roles = currentEmployee.getRoles();
        for (Role role : roles) {
            if (role.getRoleName().getReasonPhrase().equalsIgnoreCase("worker")) {
                return new ApiResponse("you can't see these information", false);
            } else if (role.getRoleName().getReasonPhrase().equalsIgnoreCase("director")) {
                //bu yerga faqat director tushadi va barcha tasklarni ko'ra oladi
                List<Task> allByResponsibleEmployee = taskRepository.findAllByResponsibleEmployeeAndTaskStatus(optionalEmployee.get(), TaskStatus.valueOf(taskDto.getTaskStatus()));
                return new ApiResponse("there is all tasks of this employee by task", true, allByResponsibleEmployee);
            } else {
                //bu yerga faqat menedjerlar tushadi va ular faqat o'zi yaratgan tasklarni ko'ra oladi
                List<Task> allByResponsibleEmployeeAndCreatedEmployeeAndTaskStatus = taskRepository.findAllByResponsibleEmployeeAndCreatedEmployeeAndTaskStatus(
                        optionalEmployee.get(),
                        currentEmployee.getUuid(),
                        TaskStatus.valueOf(taskDto.getTaskStatus())
                );
                return new ApiResponse("there is all you created tasks", true, allByResponsibleEmployeeAndCreatedEmployeeAndTaskStatus);
            }

        }

        return new ApiResponse("something wnet worng", false);
    }

    public ApiResponse employeeTurnstile(TurnstileDto turnstileDto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(turnstileDto.getCreatedEmployee());
        if (!employeeOptional.isPresent()) {
            return new ApiResponse("such employee doesn't find",false);
        }
        List<Turnstile> allByCreatedByAndCreatedAtBetween = turnstileRepository.findAllByCreatedByAndCreatedAtBetween(
                employeeOptional.get().getUuid(),
                turnstileDto.getFirstTime(),
                turnstileDto.getSecondTime());
        return new ApiResponse("there is information about employee",true,allByCreatedByAndCreatedAtBetween);


    }
}

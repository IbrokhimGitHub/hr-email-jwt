package uz.pdp.hremailjwt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.component.MostUsed;
import uz.pdp.hremailjwt.config.MailSender;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.Role;
import uz.pdp.hremailjwt.entity.Task;
import uz.pdp.hremailjwt.entity.enums.RoleName;
import uz.pdp.hremailjwt.entity.enums.TaskStatus;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.TaskDto;
import uz.pdp.hremailjwt.repository.EmployeeRepository;
import uz.pdp.hremailjwt.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MostUsed mostUsed;

    @Autowired
    MailSender mailSender;

    @Autowired
    TaskRepository taskRepository;

    public ApiResponse create(TaskDto taskDto) {
        Employee currentEmployee = mostUsed.getCurrentEmployee();
        Set<Role> currentEmployeeRole = currentEmployee.getRoles();




        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(taskDto.getResponsibleEmployeeEmail());
        if (!optionalEmployee.isPresent()) {
            return new ApiResponse("such employee doesn't find", false);
        }
        Employee responsibleEmployee = optionalEmployee.get();
        for (Role role : currentEmployeeRole) {
            if (role.getRoleName().equals(RoleName.WORKER)){
                return new ApiResponse("you cant create new task",false);
                //vazifa berayotgan employee mangager bo'lsa...->
            }else if ((role.getRoleName().equals(RoleName.MANAGER)||role.getRoleName().equals(RoleName.HR_MANAGER))){
                for (Role responsibleEmployeeRole : responsibleEmployee.getRoles()) {
                    //...->workerdan boshqasiga vazifa bera olmasligi tekshirildi
                    if (!responsibleEmployeeRole.getRoleName().equals(RoleName.WORKER)) {
                        return new ApiResponse("you cant give task to manager or director",false);
                    }
                }
            }
        }
        Task task = new Task();
        task.setTaskName(taskDto.getTaskName());
        task.setTaskStatus(TaskStatus.NEW);
        task.setDeadline(taskDto.getDeadline());
        task.setDescription(taskDto.getDescription());
        task.setResponsibleEmployee(responsibleEmployee);
        task.setTaskCode(UUID.randomUUID().toString());
        task.setCreatedEmployee(currentEmployee.getUuid());
        Task savedTask = taskRepository.save(task);
        Boolean isSent = mailSender.sendEmailTaskCreated(currentEmployee.getEmail(),
                task.getTaskName(),
                responsibleEmployee.getEmail(), savedTask.getTaskCode());
        if (isSent) {
            return new ApiResponse("Task confirm sent to email = " + responsibleEmployee.getEmail(), true);
        }
        return new ApiResponse("Email not sended", false);

    }

    public ApiResponse confirm(String taskCode) {
        Optional<Task> optionalTask = taskRepository.findByTaskCode(taskCode);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("such task doesn't find", false);
        }
        Task task = optionalTask.get();
        Employee currentEmployee = mostUsed.getCurrentEmployee();
        if (!currentEmployee.getUuid().equals(task.getResponsibleEmployee().getUuid())) {
            return new ApiResponse("this task doesn't yours", false);
        }
        task.setTaskStatus(TaskStatus.IN_PROCESS);
        taskRepository.save(task);

        return new ApiResponse("Task status changed to IN_PROCESS", true);

    }


    public ApiResponse done(TaskDto taskDto) {

        Optional<Task> optionalTask = taskRepository.findByTaskCode(taskDto.getTaskCode());
        if (!optionalTask.isPresent()) {
            return new ApiResponse("such task doesn't find", false);
        }
        String sds="dasdasdasd";
        Task task = optionalTask.get();
        Employee currentEmployee = mostUsed.getCurrentEmployee();
        if (!currentEmployee.getUuid().equals(task.getResponsibleEmployee().getUuid())) {
            return new ApiResponse("this task doesn't yours", false);
        }
        task.setTaskStatus(TaskStatus.DONE);
        taskRepository.save(task);
        Employee employee = employeeRepository.findById(task.getCreatedEmployee()).get();

        mailSender.sendEmailToTaskCreater(currentEmployee.getEmail(),
                task.getTaskName(),
                employee.getEmail(),
                task.getDeadline());


        return new ApiResponse("Task status changed to IN_PROCESS", true);
    }
    public List<Task> taskList(){
        Employee currentEmployee = mostUsed.getCurrentEmployee();
       return taskRepository.findAllByResponsibleEmployee(currentEmployee);

    }
}

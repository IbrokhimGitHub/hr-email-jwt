package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.Task;
import uz.pdp.hremailjwt.entity.enums.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

Optional<Task> findByTaskCode(String taskCode);
List<Task> findAllByResponsibleEmployee(Employee responsibleEmployee);

List<Task> findAllByResponsibleEmployeeAndTaskStatus(Employee responsibleEmployee, TaskStatus taskStatus);
List<Task> findAllByResponsibleEmployeeAndCreatedEmployeeAndTaskStatus(Employee responsibleEmployee, UUID createdEmployee, TaskStatus taskStatus);
List<Task> findAllByCreatedEmployeeAndTaskStatus(UUID createdEmployee, TaskStatus taskStatus);
}

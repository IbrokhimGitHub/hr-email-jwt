package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.hremailjwt.entity.Employee;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    boolean existsByEmail(@Email String email);

    Optional<Employee> findByEmailAndEmailCode(@Email String email, String emailCode);

    Optional<Employee> findByEmail(@Email String email);



}

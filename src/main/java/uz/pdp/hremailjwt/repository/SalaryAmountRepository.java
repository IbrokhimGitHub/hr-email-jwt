//package uz.pdp.hremailjwt.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import uz.pdp.hremailjwt.entity.Employee;
//import uz.pdp.hremailjwt.entity.SalaryAmount;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RepositoryRestResource(path = "salaryAmount")
//public interface SalaryAmountRepository extends JpaRepository<SalaryAmount, UUID> {
//    Optional<SalaryAmount> findByEmployee(Employee employees);
//}

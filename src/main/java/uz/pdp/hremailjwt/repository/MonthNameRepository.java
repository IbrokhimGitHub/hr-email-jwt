package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hremailjwt.entity.MonthName;
import uz.pdp.hremailjwt.entity.enums.MonthNameEnum;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface MonthNameRepository extends JpaRepository<MonthName, Integer> {

    Optional<MonthName> findByMonthName(MonthNameEnum monthName);
}

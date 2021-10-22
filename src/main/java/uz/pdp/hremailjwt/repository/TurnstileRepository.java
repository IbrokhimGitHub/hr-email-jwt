package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hremailjwt.entity.Turnstile;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface TurnstileRepository extends JpaRepository<Turnstile,Integer> {

    List<Turnstile> findAllByCreatedByAndCreatedAtBetween(UUID createdBy, Timestamp createdAt, Timestamp createdAt2);

}

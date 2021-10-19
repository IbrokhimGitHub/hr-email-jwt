package uz.pdp.hremailjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hremailjwt.entity.Role;
import uz.pdp.hremailjwt.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    Role findByRoleName(RoleName roleName);
}

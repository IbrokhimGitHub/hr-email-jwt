package uz.pdp.hremailjwt.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.hremailjwt.config.MailSender;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.SalaryAmount;
import uz.pdp.hremailjwt.entity.enums.RoleName;
import uz.pdp.hremailjwt.repository.EmployeeRepository;
import uz.pdp.hremailjwt.repository.RoleRepository;
import uz.pdp.hremailjwt.repository.SalaryAmountRepository;
import uz.pdp.hremailjwt.service.AuthService;


import java.util.Collections;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {



    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailSender mailSender;

    @Value(value = "${spring.sql.init.mode}")
    private String initMode;

    @Autowired
    SalaryAmountRepository salaryAmountRepository;



    @Override
    public void run(String... args) throws Exception {


        if (initMode.equals("always")){

            Employee director=new Employee("Islom",
                    "Karimov",
                    "testforjavaspring@gmail.com",
                    passwordEncoder.encode("12345"),
                    Collections.singleton(roleRepository.findByRoleName(RoleName.DIRECTOR)));
            director.setEmailCode((UUID.randomUUID().toString()));
//            director.setSalaryAmount(500000);
            employeeRepository.save(director);

            SalaryAmount salaryAmount=new SalaryAmount();
            salaryAmount.setSalaryAmount(500000d);
            salaryAmountRepository.save(salaryAmount);
            if (mailSender.sendEmailToVerify(director.getEmail(),director.getEmailCode())) {
                System.out.println("email sent to director");
            }else {
                System.out.println("email wasn't send to director");
            }


        }
    }


}

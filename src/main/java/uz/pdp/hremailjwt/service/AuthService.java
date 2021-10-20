package uz.pdp.hremailjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hremailjwt.component.MostUsed;
import uz.pdp.hremailjwt.config.MailSender;
import uz.pdp.hremailjwt.entity.Employee;
import uz.pdp.hremailjwt.entity.Role;
import uz.pdp.hremailjwt.entity.enums.RoleName;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.LoginDto;
import uz.pdp.hremailjwt.payload.RegisterDto;
import uz.pdp.hremailjwt.repository.EmployeeRepository;
import uz.pdp.hremailjwt.repository.RoleRepository;
import uz.pdp.hremailjwt.security.JwtProvider;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MailSender mailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MostUsed mostUsed;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        return optionalEmployee.orElseThrow(() -> new UsernameNotFoundException(email+" can't find user"));
//        return employeeRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " can not find"));

    }
//    public Boolean sendEmail(String sendingEmail,String emailCode){
//        try {
//
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom("Ibrokhim");
//            mailMessage.setTo(sendingEmail);
//            mailMessage.setSubject("Assalomu alaykum");
//
////            "<form action=\"/api/auth/verifyEmail?emailCode="+emailCode+"&email=" +sendingEmail+"\">\n" +
////                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
////                    "    <button>submit</button>\n" +
////                    "</form>"
//            mailMessage.setText("<form action=\"http://localhost:8080/api/auth/verifyEmail?emailCode="+emailCode+"&email=" +sendingEmail+"\">\n" +
//                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
//                    "    <button>submit</button>\n" +
//                    "</form>");
//            javaMailSender.send(mailMessage);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setEnabled(true);
            employee.setEmailCode(null);
            employeeRepository.save(employee);
            return new ApiResponse("Account verified",true);
        }
        return new ApiResponse("Account doesnt verified",false);


    }

    public ApiResponse login(LoginDto loginDto) {
        try {


            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getLogin(),
                    loginDto.getPassword()
            ));
            Employee principal1 = (Employee) authenticate.getPrincipal();
//            Employee principal = mostUsed.getCurrentEmployee();

            String token = jwtProvider.generateToken(loginDto.getLogin(), principal1.getRoles());
            return new ApiResponse("Token", true, token);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("USERNAME OR PASSWORD  IS MISTAKE",false);
        }
    }

    public ApiResponse registerUser(RegisterDto registerDto) {

        boolean existsByEmail = employeeRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail){
            return new ApiResponse("Such email exist",false);
        }


        Employee user=new Employee();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        String role=registerDto.getRole();
        Set<Role> currentEmployeeRoles = mostUsed.getCurrentEmployeeRole();
        for (Role currentEmployeeRole : currentEmployeeRoles) {
            if (currentEmployeeRole.getRoleName().equals(RoleName.DIRECTOR)) {
                if (role.equalsIgnoreCase("MANAGER")) {
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.MANAGER)));
                } else if (role.equalsIgnoreCase("WORKER")) {
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.WORKER)));
                } else if (role.equalsIgnoreCase("HR_MANAGER")) {
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.HR_MANAGER)));
                }
            } else if (currentEmployeeRole.getRoleName().equals(RoleName.HR_MANAGER)) {
                if (role.equalsIgnoreCase("MANAGER")) {
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.MANAGER)));
                } else if (role.equalsIgnoreCase("WORKER")) {
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.WORKER)));
                }
            }
        }




//        if (role.equalsIgnoreCase("MANAGER")){
//            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.MANAGER)));
//        }else if (role.equalsIgnoreCase("WORKER")){
//            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.WORKER)));
//        }else if (role.equalsIgnoreCase("HR_MANAGER")){
//            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.HR_MANAGER)));
//        }
        user.setEmailCode(UUID.randomUUID().toString());
        boolean isEmailSent=mailSender.sendEmailToVerify(user.getEmail(),user.getEmailCode());
        System.out.println("sdsds");
        if (isEmailSent) {
            employeeRepository.save(user);
            return new ApiResponse("Success, please verify your email",true);
        }
        return new ApiResponse("something went wrong",false);
    }

    public ApiResponse resetPassword(){
        return null;
    }
}

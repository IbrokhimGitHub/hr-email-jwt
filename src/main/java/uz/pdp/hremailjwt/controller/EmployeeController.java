package uz.pdp.hremailjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.LoginDto;
import uz.pdp.hremailjwt.payload.RegisterDto;
import uz.pdp.hremailjwt.service.AuthService;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/auth")
public class EmployeeController {
    @Autowired
     AuthService authService;
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode,@RequestParam @Email String email){
        ApiResponse apiResponse=authService.verifyEmail(email,emailCode);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse=authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse=authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);

    }

}

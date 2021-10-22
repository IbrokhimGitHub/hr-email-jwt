package uz.pdp.hremailjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.TaskDto;
import uz.pdp.hremailjwt.payload.TurnstileDto;
import uz.pdp.hremailjwt.service.LeaderService;

@RestController
@RequestMapping("api/leader")
public class LeaderController {

    @Autowired
    LeaderService leaderService;

    @PostMapping("turnstileInfo")
    public HttpEntity<?> turnstileInfo(TurnstileDto turnstileDto){
        ApiResponse apiResponse = leaderService.employeeTurnstile(turnstileDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:406).body(apiResponse);
    }
    @PostMapping("/taskInfo")
    public HttpEntity<?> turnstileInfo(TaskDto taskDto){
        ApiResponse apiResponse = leaderService.taskListByStatus(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:406).body(apiResponse);
    }

}

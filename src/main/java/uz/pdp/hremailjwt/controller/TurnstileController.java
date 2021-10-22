package uz.pdp.hremailjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.service.TurnstileService;

@RestController
@RequestMapping("/api/trunstile")
public class TurnstileController {
    @Autowired
    TurnstileService turnstileService;
    @GetMapping("/frontSide")
    public HttpEntity<?> inComing(){
        ApiResponse apiResponse = turnstileService.comeToWork();
        return ResponseEntity.status(apiResponse.isSuccess()?200:501).body(apiResponse);

    }
    @GetMapping("/rearSide")
    public HttpEntity<?> outGoing(){
        ApiResponse apiResponse = turnstileService.outgoingFromWork();
        return ResponseEntity.status(apiResponse.isSuccess()?200:501).body(apiResponse);

    }

}

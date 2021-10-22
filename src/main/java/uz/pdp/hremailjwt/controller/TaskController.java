package uz.pdp.hremailjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hremailjwt.entity.Task;
import uz.pdp.hremailjwt.payload.ApiResponse;
import uz.pdp.hremailjwt.payload.TaskDto;
import uz.pdp.hremailjwt.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody TaskDto taskDto){
       ApiResponse apiResponse= taskService.create(taskDto);
       return  ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);

    }
    @GetMapping("/confirm")
    public HttpEntity<?> confirm(@RequestParam String taskCode){
        ApiResponse apiResponse= taskService.confirm(taskCode);
        return  ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);

    }
    @PostMapping("/done")
    public HttpEntity<?> done(@RequestBody TaskDto taskDto){
        ApiResponse apiResponse= taskService.done(taskDto);
        System.out.println("dsadasd");
        return  ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);

    }
    @GetMapping("/getList")
    public HttpEntity<List<Task>> getList(){
        List<Task> tasks = taskService.taskList();
        return ResponseEntity.status(205).body(tasks);
    }


}

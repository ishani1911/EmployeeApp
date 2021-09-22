package com.example.demo.controller;

import com.example.demo.model.Emp;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class EmpController {
    @Autowired
    EmpService empService;
    @CrossOrigin
    @GetMapping("/getempall")
    public Collection<Emp> findAll(){
        return empService.getAll();
    }

    @CrossOrigin
    @GetMapping("/findbyid")
    public Collection<Emp> findById(@RequestParam Integer id){
        return empService.findById(id);
    }

    @CrossOrigin
    @PostMapping("/addemp")
    public boolean saveEmp(@RequestBody Emp emp){
        return empService.saveEmp(emp);
    }
}

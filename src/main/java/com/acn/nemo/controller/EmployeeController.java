/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.controller;

import com.acn.nemo.entity.Employees;
import com.acn.nemo.services.EmployeeService;
import java.util.List;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author massimo.caputo
 */
@RestController
@RequestMapping("/ws")
public class EmployeeController {
    
//    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
    
    @GetMapping(path = "/employees" )
    public ResponseEntity<List<Employees>> getAllEmployeeList(){
        List<Employees> listEmp = (List<Employees>) employeeService.getAllListEmployee();
        
        if( listEmp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listEmp,HttpStatus.OK);
    }
    
}

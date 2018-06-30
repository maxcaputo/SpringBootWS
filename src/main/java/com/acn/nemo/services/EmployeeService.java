/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.services;

import com.acn.nemo.dao.EmployeeDao;
import com.acn.nemo.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author massimo.caputo
 */
@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDao employeeDao;
    
    public Iterable<Employees> getAllListEmployee() {
        return employeeDao.findAll();
    }
    
}

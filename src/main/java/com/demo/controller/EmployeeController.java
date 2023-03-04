package com.demo.controller;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        log.info("######### Trying to save data for : #########" + employee.getEmpName());
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Successfully");
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByName(empName));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {
        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("Data Updated ");
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyname")
    public ResponseEntity<List<Employee>> sortDataByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabysalary")
    public ResponseEntity<List<Employee>> sortDatBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpSalary() >= empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabydob")
    public ResponseEntity<List<Employee>> sortDataByDOB() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<List<Employee>> getDataByEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabyanyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpName().equals(input) || e.getEmpDOB().equals(input) ||
                e.getEmpContactNumber() == Long.valueOf(input) || e.getEmpSalary() == Double.valueOf(input)).collect(Collectors.toList()));
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted");
    }
}

package com.demo.dao;

import com.demo.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public Employee getDataById(int empId);

    public List<Employee> getAllData();

    public List<Employee> getDataByName(String empName);

    public Employee getDataByContactNumber(long empContactNumber);

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();

}

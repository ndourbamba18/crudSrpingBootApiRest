package com.parlonsdev.service;

import com.parlonsdev.exception.ResourceNotFoundException;
import com.parlonsdev.model.Employee;
import com.parlonsdev.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> list(){
        return employeeRepository.findAll();
    }

    public Employee getOne(Long employeeId){
        return employeeRepository.findById(employeeId)
                .orElseThrow( () -> new ResourceNotFoundException("Employee", "employeeId", employeeId));
    }

    public Employee getEmployeeByEmail(String email){
        return employeeRepository.findByEmail(email)
                .orElseThrow( () -> new ResourceNotFoundException("Employee", "email", email));
    }

    public Employee getEmployeeByPhoneNumber(String phoneNumber){
        return employeeRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow( () -> new ResourceNotFoundException("Employee", "phoneNumber", phoneNumber));
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public void delete(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public void deleteAll(){
        employeeRepository.deleteAll();
    }

    public Boolean existsById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public Boolean existsByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }

    public Boolean existsByPhoneNumber(String phoneNumber){
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }
}

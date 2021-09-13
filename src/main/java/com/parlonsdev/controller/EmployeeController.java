package com.parlonsdev.controller;

import com.parlonsdev.dto.EmployeeDto;
import com.parlonsdev.message.ResponseMessage;
import com.parlonsdev.model.Employee;
import com.parlonsdev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE A NEW EMPLOYEE
    @PostMapping(path = "/new")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        try {

            Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(),
                    employeeDto.getEmail(),
                    employeeDto.getPhoneNumber(), employeeDto.getAddress(), employeeDto.getJobTitle());
            employeeService.save(employee);
            return new ResponseEntity<>(new ResponseMessage("Employee ("+employee.getFirstName()+") has been added successfully!"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL EMPLOYEES
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllEmployees(){
       try {
           List<Employee> employees = employeeService.list();
           if (employees.isEmpty())
               return new ResponseEntity<>("employees empty!", HttpStatus.NOT_FOUND);
           return new ResponseEntity<>(employees, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // GET A SINGLE EMPLOYEE BY ID
    @GetMapping(path = "/detail/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId){
       try {

           Employee employee = employeeService.getOne(employeeId);
           return new ResponseEntity<>(employee, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("ERROR ID : " + employeeId), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // GET A SINGLE EMPLOYEE BY EMAIL
    @GetMapping(path = "/detailemail/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable("email") String email){
        try {
            /*if (!employeeService.existsByEmail(email))
                return new ResponseEntity<>(new ResponseMessage("Employee ("+email+") does not exist!"), HttpStatus.BAD_REQUEST);
            */

            Employee employee = employeeService.getEmployeeByEmail(email);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR EMAIL : "+ email), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET A SINGLE EMPLOYEE BY PHONE NUMBER
    @GetMapping(path = "/detailphone/{phoneNumber}")
    public ResponseEntity<?> getEmployeeByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        try {
            /*if (!employeeService.existsByPhoneNumber(phoneNumber))
                return new ResponseEntity<>(new ResponseMessage("Employee ("+phoneNumber+") does not exist!"), HttpStatus.BAD_REQUEST);
             */

            Employee employee = employeeService.getEmployeeByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR PHONE NUMBER : "+phoneNumber), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE A SINGLE EMPLOYEE BY ID
    @PutMapping(path = "/edit/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employeeDto){
        try {

            Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(),
                    employeeDto.getPhoneNumber(), employeeDto.getAddress(), employeeDto.getJobTitle());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setAddress(employeeDto.getAddress());
            employee.setJobTitle(employeeDto.getJobTitle());
            employeeService.save(employee);
            return new ResponseEntity<>(new ResponseMessage("Employee ("+employee.getFirstName()+") has been updated successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR ID : " + employeeId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE A SINGLE EMPLOYEE BY ID
    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("employeeId") Long employeeId){
        try {
            /*if (!employeeService.existsById(employeeId))
                return new ResponseEntity<>("Employee ("+employeeId+") does not exist!", HttpStatus.BAD_REQUEST);
            */
            employeeService.delete(employeeId);
            return new ResponseEntity<>(new ResponseMessage("Employee ("+employeeId+") is deleted successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR ID : " + employeeId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL EMPLOYEES
    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<?> deleteAllEmployees(){
        try {
            employeeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

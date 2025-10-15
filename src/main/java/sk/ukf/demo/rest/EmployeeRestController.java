package sk.ukf.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee not found - " + id);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee employee_res = employeeService.save(employee);
        return employee_res;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee employee_db = employeeService.findById(id);

        if(employee_db == null){
            throw new RuntimeException("Employee not found - " + id);
        }

        employee.setId(id);
        Employee updayedEmployee = employeeService.save(employee);
        return  updayedEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee not found - " + id);
        }

        employeeService.deleteById(id);

        return "Deleted employee: " + id;
    }


}
package sk.ukf.demo.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.response.ApiResponse;
import sk.ukf.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ApiResponse findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ApiResponse(employees, "Employees retrieved successfully");
    }

    @GetMapping("/employees/{id}")
    public ApiResponse getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found - " + id);
        }
        return new ApiResponse(employee, "Employee retrieved successfully");
    }

    @PostMapping("/employees")
    public ApiResponse addEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(0); // Ensure new employee
        Employee saved = employeeService.save(employee);
        return new ApiResponse(saved, "Employee created successfully");
    }

    @PutMapping("/employees/{id}")
    public ApiResponse updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            throw new RuntimeException("Employee not found - " + id);
        }
        employee.setId(id);
        Employee saved = employeeService.save(employee);
        return new ApiResponse(saved, "Employee updated successfully");
    }

    @DeleteMapping("/employees/{id}")
    public ApiResponse deleteEmployee(@PathVariable int id) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            throw new RuntimeException("Employee not found - " + id);
        }
        employeeService.deleteById(id);
        return new ApiResponse(null, "Employee deleted successfully");
    }
}
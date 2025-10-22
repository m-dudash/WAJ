package sk.ukf.demo.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.dto.EmployeeRequest;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.response.ApiResponse;
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
    public ApiResponse findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ApiResponse(employees, "Employees retrieved successfully");
    }
    @GetMapping("/employees/{id}")
    public ApiResponse getEmployee(@PathVariable @Min(value = 1, message = "Id must be > 0") int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee not found - " + id);
        }
        return new ApiResponse(employee, "Employee retrieved successfully");
    }

    @PostMapping("/employees")
    public ApiResponse addEmployee(@Valid @RequestBody EmployeeRequest employeeReq) {
        Employee employee = mapDtoToEntity(employeeReq);
        employee.setId(0); // Ensure new employee
        Employee saved = employeeService.save(employee);
        return new ApiResponse(saved, "Employee created successfully");
    }

    @PutMapping("/employees/{id}")
    public ApiResponse updateEmployee(
            @PathVariable @Min(value = 1, message = "Id must be > 0") int id,
            @Valid @RequestBody EmployeeRequest employeeReq
    ) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            throw new RuntimeException("Employee not found - " + id);
        }

        Employee updated = mapDtoToEntity(employeeReq);
        updated.setId(id);
        Employee saved = employeeService.save(updated);

        return new ApiResponse(saved, "Employee updated successfully");
    }


    @DeleteMapping("/employees/{id}")
    public ApiResponse deleteEmployee(@PathVariable @Min(value = 1, message = "Id must be > 0") int id) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            throw new RuntimeException("Employee not found - " + id);
        }

        employeeService.deleteById(id);
        return new ApiResponse(null, "Employee deleted successfully");
    }


    private Employee mapDtoToEntity(EmployeeRequest dto) {
        Employee e = new Employee();
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setBirth_date(dto.getBirth_date());
        e.setJob_title(dto.getJob_title());
        e.setSalary(dto.getSalary());
        e.setFull_time(dto.isFull_time());
        return e;
    }
}
package sk.ukf.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.service.EmployeeService;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Value("#{'${job.titles}'.split(',')}")
    private List<String> jobTitles;

    @Value("#{'${employment.types}'.split(',')}")
    private List<String> employmentTypes;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "list-employees";
    }

    @GetMapping("/employees/{id}")
    public String showEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return "redirect:/";
        }
        model.addAttribute("employee", employee);
        return "employee-detail";
    }

    @GetMapping("/employees/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("jobTitles", jobTitles);
        model.addAttribute("employmentTypes", employmentTypes);
        return "employee-form";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("jobTitles", jobTitles);
            model.addAttribute("employmentTypes", employmentTypes);
            return "employee-form";
        }
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/";
    }
}
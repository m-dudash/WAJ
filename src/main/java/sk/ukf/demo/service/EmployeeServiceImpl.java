package sk.ukf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.demo.dao.EmployeeDAO;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    public Employee findById(int id) { return  employeeDAO.findById(id); }

    @Transactional
    public Employee save(Employee employee) { return  employeeDAO.save(employee); }

    @Transactional
    public void deleteById(int id) { employeeDAO.deleteById(id); }
}
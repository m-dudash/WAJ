package sk.ukf.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.demo.dao.EmployeeDAO;
import sk.ukf.demo.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    public Employee findById(int id){
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    public Employee save(Employee employee){
        Employee employee_res = entityManager.merge(employee);
        return employee_res;
    }

    public void deleteById(int id){
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
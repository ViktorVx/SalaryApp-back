package org.pva.SalaryApp.Controller;

import org.pva.SalaryApp.Model.Employee;
import org.pva.SalaryApp.Repository.EmployeeRepository;
import org.pva.SalaryApp.Repository.PersonRepository;
import org.pva.SalaryApp.Service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    private PersonRepository personRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService,
                              PersonRepository personRepository,
                              EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.personRepository = personRepository;
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

}

package org.pva.SalaryApp.Service;

import org.pva.SalaryApp.Model.Employee;
import org.pva.SalaryApp.Model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(new Person("Иван", "Иванов"), new Date()));
        employees.add(new Employee(new Person("Юлия", "Петрова"), new Date()));
        employees.add(new Employee(new Person("Артур", "Пирожков"), new Date()));
        employees.add(new Employee(new Person("Артур", "Петров"), new Date()));
        employees.add(new Employee(new Person("Виктор", "Петров"), new Date()));
        return employees;
    }

}

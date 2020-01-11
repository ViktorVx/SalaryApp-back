package org.pva.SalaryApp.Service;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Employee;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.request.EmployeeRequest;
import org.pva.SalaryApp.Model.Dto.response.ObjectListResponse;
import org.pva.SalaryApp.Model.Dto.response.Response;
import org.pva.SalaryApp.Model.Dto.response.ResponseCode;
import org.pva.SalaryApp.Repository.EmployeeRepository;
import org.pva.SalaryApp.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PersonRepository personRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PersonRepository personRepository) {
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
    }

    public Response getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList == null || employeeList.isEmpty()) {
            return new Response(ResponseCode.FAIL, "No employee in database");
        } else {
            ObjectListResponse<Employee> employeeResponse = new ObjectListResponse(ResponseCode.SUCCESS, "Get employee");
            employeeResponse.setObjectList(employeeList);
            return employeeResponse;
        }
    }

    public Response addNewEmployee(EmployeeRequest employeeRequest) {
        try {
            Employee employee = new Employee();
            employee.setEmploymentDate(employeeRequest.getEmploymentDate());
            employee.setDismissalDate(employeeRequest.getDismissalDate());
            Person person = personRepository.findById(employeeRequest.getPersonId()).get();
            employee.setPerson(person);
            employeeRepository.saveAndFlush(employee);
            return new Response(ResponseCode.SUCCESS, "Add employee successfully");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    public Response getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employeeOptional.get());
            ObjectListResponse employeeResponse = new ObjectListResponse(ResponseCode.SUCCESS, "Get employee");
            employeeResponse.setObjectList(employeeList);
            return employeeResponse;
        } else {
            return new Response(ResponseCode.FAIL, String.format("Employee with %d id is absent", employeeId));
        }
    }

    public Response updateEmployee(EmployeeRequest employeeRequest) throws NotFoundException {
        if (employeeRequest.getId() == null) {
            throw new IllegalArgumentException("Unknown employee (id is absent)");
        }
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeRequest.getId());
        if (optionalEmployee.isPresent()) {
            Employee dbEmployee = updateEmployeeObject(optionalEmployee.get(), employeeRequest);
            employeeRepository.save(dbEmployee);
            return new Response(ResponseCode.SUCCESS, "Success person updating");
        } else {
            throw new NotFoundException("Person is absent in database");
        }
    }

    public Response deleteEmployee(Long employeeId) {
        if (employeeId == null) {
            throw new IllegalArgumentException("Unknown employee (id is absent)");
        }
        try {
            employeeRepository.deleteById(employeeId);
            return new Response(ResponseCode.SUCCESS, "Success deleted employee with id:".concat(employeeId.toString()));
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    private Employee updateEmployeeObject(Employee dbEmployee, EmployeeRequest updEmployee) {
        Person person = personRepository.findById(updEmployee.getPersonId()).get();
        dbEmployee.setPerson(person);
        dbEmployee.setDismissalDate(updEmployee.getDismissalDate());
        dbEmployee.setEmploymentDate(updEmployee.getEmploymentDate());
        return dbEmployee;
    }

}

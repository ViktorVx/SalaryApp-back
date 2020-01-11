package org.pva.SalaryApp.Controller;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Employee;
import org.pva.SalaryApp.Model.Dto.request.EmployeeRequest;
import org.pva.SalaryApp.Model.Dto.response.Response;
import org.pva.SalaryApp.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Response getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(path = "/list/get", method = RequestMethod.GET)
    public Response getEmployee(@RequestParam Long id) {
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.PUT)
    public Response addNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addNewEmployee(employeeRequest);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PATCH)
    public Response updateEmployee(@RequestBody EmployeeRequest employeeRequest) throws NotFoundException {
        return employeeService.updateEmployee(employeeRequest);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public Response deleteEmployee(@RequestParam Long id) {
        return employeeService.deleteEmployee(id);
    }

}

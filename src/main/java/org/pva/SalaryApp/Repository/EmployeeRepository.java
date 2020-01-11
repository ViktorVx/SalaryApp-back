package org.pva.SalaryApp.Repository;

import org.pva.SalaryApp.Model.Business.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

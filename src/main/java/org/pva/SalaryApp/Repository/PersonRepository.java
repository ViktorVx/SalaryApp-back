package org.pva.SalaryApp.Repository;

import org.pva.SalaryApp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

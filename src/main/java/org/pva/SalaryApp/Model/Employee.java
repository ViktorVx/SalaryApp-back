package org.pva.SalaryApp.Model;

import java.util.Date;

public class Employee {
    private Person person;
    private Date employmentDate;
    private Date dismissalDate;

    public Employee(Person person, Date employmentDate) {
        this.person = person;
        this.employmentDate = employmentDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Date getDismissalDate() {
        return dismissalDate;
    }

    public void setDismissalDate(Date dismissalDate) {
        this.dismissalDate = dismissalDate;
    }
}

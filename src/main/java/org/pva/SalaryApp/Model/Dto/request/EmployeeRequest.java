package org.pva.SalaryApp.Model.Dto.request;

import java.util.Date;

public class EmployeeRequest {

    private Long id;
    private Long personId;
    private Date employmentDate;
    private Date dismissalDate;

    public EmployeeRequest(Long personId, Date employmentDate, Date dismissalDate) {
        this.personId = personId;
        this.employmentDate = employmentDate;
        this.dismissalDate = dismissalDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

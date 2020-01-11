package org.pva.SalaryApp.Model.Dto;

import org.pva.SalaryApp.Model.Business.Person;

import java.util.List;

public class PersonResponse extends Response {

    private List<Person> personList;

    public PersonResponse(ResponseCode responseCode, String message) {
        super(responseCode, message);
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}

package org.pva.SalaryApp.Service;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.Response;
import org.pva.SalaryApp.Model.Dto.ResponseCode;
import org.pva.SalaryApp.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Response addNewPerson(Person person) {
        try {
            personRepository.saveAndFlush(person);
            return new Response(ResponseCode.SUCCESS, "Add person successfully");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    public Response updatePerson(Person person) throws NotFoundException {
        if (person.getId() == null) {
            throw new IllegalArgumentException("Unknown person (id is absent)");
        }
        Optional<Person> optionalPerson = personRepository.findById(person.getId());
        if (optionalPerson.isPresent()) {
            Person dbPerson = updatePersonObject(optionalPerson.get(), person);
            personRepository.save(dbPerson);
            return new Response(ResponseCode.SUCCESS, "Success person updating");
        } else {
            throw new NotFoundException("Person is absent in database");
        }
    }

    public Response deletePerson(Person person) {
        Long id = person.getId();
        if (id == null) {
            throw new IllegalArgumentException("Unknown person (id is absent)");
        }
        try {
            personRepository.deleteById(id);
            return new Response(ResponseCode.SUCCESS, "Success deleted person with id:".concat(id.toString()));
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    private Person updatePersonObject(Person dbPerson, Person updPerson) {
        dbPerson.setFirstName(updPerson.getFirstName());
        dbPerson.setMiddleName(updPerson.getMiddleName());
        dbPerson.setLastName(updPerson.getLastName());
        dbPerson.setBirthDate(updPerson.getBirthDate());
        dbPerson.setGender(updPerson.getGender());
        dbPerson.setPassportNumber(updPerson.getPassportNumber());
        return dbPerson;
    }

}

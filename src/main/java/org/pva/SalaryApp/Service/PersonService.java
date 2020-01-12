package org.pva.SalaryApp.Service;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.response.ObjectListResponse;
import org.pva.SalaryApp.Model.Dto.response.Response;
import org.pva.SalaryApp.Model.Dto.response.ResponseCode;
import org.pva.SalaryApp.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Response getAllPersons() {
        List<Person> personList = personRepository.findAll();
        if (personList == null || personList.isEmpty()) {
            return new Response(ResponseCode.FAIL, "No person in database");
        } else {
            ObjectListResponse<Person> personResponse = new ObjectListResponse(ResponseCode.SUCCESS, "Get persons");
            personResponse.setObjectList(personList);
            return personResponse;
        }
    }

    public Response addNewPerson(Person person) {
        try {
            personRepository.saveAndFlush(person);
            return new Response(ResponseCode.SUCCESS, "Add person successfully");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    public Response getPersonById(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            List<Person> personList = new ArrayList<>();
            personList.add(personOptional.get());
            ObjectListResponse<Person> personResponse = new ObjectListResponse(ResponseCode.SUCCESS, "Get person");
            personResponse.setObjectList(personList);
            return personResponse;
        } else {
            return new Response(ResponseCode.FAIL, String.format("Person with %d id is absent", personId));
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

    public Response deletePerson(Long id) {
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
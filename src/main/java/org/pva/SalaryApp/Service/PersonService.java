package org.pva.SalaryApp.Service;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.response.ObjectListResponse;
import org.pva.SalaryApp.Model.Dto.response.Response;
import org.pva.SalaryApp.Model.Dto.response.ResponseCode;
import org.pva.SalaryApp.Repository.PersonRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private MessageSource messageSource;

    public PersonService(PersonRepository personRepository, MessageSource messageSource) {
        this.personRepository = personRepository;
        this.messageSource = messageSource;
    }

    public Response getAllPersons(Locale locale) {
        List<Person> personList = personRepository.findAll();
        if (personList.isEmpty()) {
            return new Response(ResponseCode.FAIL,
                    messageSource.getMessage("salary-app.messages.persons.error.no-persons-in-database", null, locale));
        } else {
            ObjectListResponse<Person> personResponse = new ObjectListResponse<>(ResponseCode.SUCCESS,
                    messageSource.getMessage("salary-app.messages.persons.success.get-persons", null, locale));
            personResponse.setObjectList(personList);
            return personResponse;
        }
    }

    public Response addNewPerson(Person person, Locale locale) {
        try {
            personRepository.saveAndFlush(person);
            return new Response(ResponseCode.SUCCESS,
                    messageSource.getMessage("salary-app.messages.persons.success.add-person-succesfully", null, locale));
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage());
        }
    }

    public Response getPersonById(Long personId, Locale locale) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            List<Person> personList = new ArrayList<>();
            personList.add(personOptional.get());
            ObjectListResponse<Person> personResponse = new ObjectListResponse<>(ResponseCode.SUCCESS,
                    String.format(messageSource.getMessage("salary-app.messages.persons.success.get-person-with-id", null, locale), personId));
            personResponse.setObjectList(personList);
            return personResponse;
        } else {
            return new Response(ResponseCode.FAIL,
                    String.format(messageSource.getMessage("salary-app.messages.persons.error.person-with-id-is-absent", null, locale), personId));
        }
    }

    public Response updatePerson(Person person, Locale locale) throws NotFoundException {
        if (person.getId() == null) {
            throw new IllegalArgumentException(messageSource.getMessage("salary-app.messages.persons.error.no-persons-in-database", null, locale));
        }
        Optional<Person> optionalPerson = personRepository.findById(person.getId());
        if (optionalPerson.isPresent()) {
            Person dbPerson = updatePersonObject(optionalPerson.get(), person);
            personRepository.save(dbPerson);
            return new Response(ResponseCode.SUCCESS,
                    messageSource.getMessage("salary-app.messages.persons.success.person-succesfully-updated", null, locale));
        } else {
            throw new NotFoundException("Person is absent in database");
        }
    }

    public Response deletePerson(Long id, Locale locale) {
        if (id == null) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("salary-app.messages.persons.error.no-persons-in-database", null, locale)
            );
        }
        try {
            personRepository.deleteById(id);
            return new Response(ResponseCode.SUCCESS, String.format(
                    messageSource.getMessage("salary-app.messages.persons.success.succesfully-deleted-person", null, locale), id));
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

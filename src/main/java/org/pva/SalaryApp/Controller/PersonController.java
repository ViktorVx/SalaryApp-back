package org.pva.SalaryApp.Controller;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.Response;
import org.pva.SalaryApp.Service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Response addNewPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Response updatePerson(@RequestBody Person person) throws NotFoundException {
        return personService.updatePerson(person);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public Response deletePerson(@RequestBody Person person) {
        return personService.deletePerson(person);
    }

}

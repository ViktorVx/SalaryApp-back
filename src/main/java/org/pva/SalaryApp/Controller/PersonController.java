package org.pva.SalaryApp.Controller;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.Response;
import org.pva.SalaryApp.Service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Response getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(path = "/list/get", method = RequestMethod.GET)
    public Response getPerson(@RequestParam Long id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.PUT)
    public Response addNewPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PATCH)
    public Response updatePerson(@RequestBody Person person) throws NotFoundException {
        return personService.updatePerson(person);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public Response deletePerson(@RequestBody Person person) {
        return personService.deletePerson(person);
    }

}

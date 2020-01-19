package org.pva.SalaryApp.Controller;

import javassist.NotFoundException;
import org.pva.SalaryApp.Model.Business.Person;
import org.pva.SalaryApp.Model.Dto.response.Response;
import org.pva.SalaryApp.Service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Response getAllPersons(Locale locale) {
        logger.warn("Get person's list");
        return personService.getAllPersons(locale);
    }

    @RequestMapping(path = "/list/get", method = RequestMethod.GET)
    public Response getPerson(@RequestParam Long id, Locale locale) {
        return personService.getPersonById(id, locale);
    }

    @RequestMapping(path = "/add", method = RequestMethod.PUT)
    public Response addNewPerson(@RequestBody Person person, Locale locale) {
        return personService.addNewPerson(person, locale);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PATCH)
    public Response updatePerson(@RequestBody Person person, Locale locale) throws NotFoundException {
        return personService.updatePerson(person, locale);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public Response deletePerson(@RequestParam Long id, Locale locale) {
        return personService.deletePerson(id, locale);
    }

}

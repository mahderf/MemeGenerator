package com.mahi.demo.controller;

import com.mahi.demo.models.Person;
import com.mahi.demo.repository.PersonRepository;
import com.mahi.demo.repository.RoleRepository;
import com.mahi.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PersonService personService;

    @RequestMapping(value="/register",method= RequestMethod.GET)
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user",new Person());
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public  String processRegistrationPage(@Valid @ModelAttribute("user") Person person,
                                           BindingResult bindingResult, Model model) {
        model.addAttribute("user", person);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
            personService.saveUser(person);
            model.addAttribute("message", "User Account Successfully Created");

        return "login";
        }


}

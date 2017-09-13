package com.mahi.demo.controller;

import com.cloudinary.utils.ObjectUtils;
import com.mahi.demo.configuration.CloudinaryConfig;
import com.mahi.demo.models.Person;
import com.mahi.demo.models.Uploads;
import com.mahi.demo.repository.MemesRepository;
import com.mahi.demo.repository.PersonRepository;
import com.mahi.demo.repository.RoleRepository;
import com.mahi.demo.repository.UploadsRepository;
import com.mahi.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MemesRepository memesRepository;
    @Autowired
    UploadsRepository uploadsRepository;
    @Autowired
    PersonService personService;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @GetMapping("/")
    public String welocme(Model model){
        model.addAttribute("images",uploadsRepository.findAll());

        return"show";
    }

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

        @GetMapping("/addimage")
    public String loadImage(Model model){
        model.addAttribute("uploads", new Uploads());

        return"uploadform";
        }

        @PostMapping("/addimage")
    public String saveImage(@ModelAttribute("uploads") Uploads uploads,
                            @RequestParam("file")MultipartFile file, Model model)
        {
            if (file.isEmpty()) {
                return "redirect:/addimage";
            }
            try {
                Map uploadResult = cloudinaryConfig.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype","auto"));
                uploads.setImages(uploadResult.get("url").toString());
                uploadsRepository.save(uploads);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/addimage";
            }

          return"redirect:/";
        }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}

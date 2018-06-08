package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute(user);
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password","");
            model.addAttribute("title","User Signup");
            return "user/add";
        }

        else {
            model.addAttribute("user", user);
            return "user/index";

        }

    }

}




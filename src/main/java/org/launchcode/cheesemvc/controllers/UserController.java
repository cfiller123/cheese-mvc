package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "user/add";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email",user.getEmail());

        if (!model.containsAttribute("username") || !model.containsAttribute("email")) {
            model.addAttribute("errors", "please enter a username and email");
            return "redirect:";
            }

        else if (user.getPassword() != verify) {
            model.addAttribute("errors","passwords don't match");
            return "user/add";
        }

        return "redirect:";


    }
}

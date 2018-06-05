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

        if (user.getPassword() == verify) {
            return "redirect:";
        }

        else {
            model.addAttribute("errors","passwords don't match");
            return "user/add";
        }
    }
}

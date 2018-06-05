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

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "User Signup");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify) {
        if (verify.equals(user.getPassword())) {
            model.addAttribute("user", user);
            return "user/index";
        }

        else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("title", "User Signup");
            model.addAttribute("error", "passwords don't match");
            return "user/add";
        }

    }
}
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("email",user.getEmail());
//
//        if (!model.containsAttribute("username") || !model.containsAttribute("email")) {
//            model.addAttribute("errors", "please enter a username and email");
//            return "redirect:";
//            }
//
//        else if (user.getPassword() != verify) {
//            model.addAttribute("errors","passwords don't match");
//            return "user/add";
//        }
//
//        else if (user.getUsername().length()>5 & user.getUsername().length()<15) {
//            model.addAttribute("errors", "your username must be between 5 and 15 letters");
//            return "user/add";
//        }
//
//        return "redirect:";
//
//
//    }
//}

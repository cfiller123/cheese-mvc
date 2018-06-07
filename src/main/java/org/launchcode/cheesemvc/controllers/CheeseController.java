package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.cheesemvc.models.Cheese;

import javax.validation.Valid;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); //this is giving an extra id number
        model.addAttribute("cheeseTypes",CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Add Cheese");
            model.addAttribute("cheeseTypes",CheeseType.values());
            return "cheese/add";
        }
        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}")
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese c = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", c);
        model.addAttribute("cheeseTypes",CheeseType.values());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Cheese cheese, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title","Add Cheese");
            return "cheese/edit";
        }

        Cheese c = CheeseData.getById(cheese.getCheeseId());
        c.setName(cheese.getName());
        c.setType(cheese.getType());
        c.setDescription(cheese.getDescription());
        return "redirect:";
    }


//    @RequestMapping(value = "edit", method = RequestMethod.POST)
//    public String processEditForm(@ModelAttribute @Valid int cheeseId, String name, String description,
//                                  CheeseType cheeseType, Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title","Add Cheese");
//            model.addAttribute("cheeseTypes",CheeseType.values());
//            return "cheese/edit/{cheeseId}";
//        }
//
//        Cheese c = CheeseData.getById(cheeseId);
//        c.setType(cheeseType);
//        c.setName(name);
//        c.setDescription(description);
//        return "redirect:";
//    }

}

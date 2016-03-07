package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.service.AwesomeService;

import javax.validation.Valid;

/**
 * Created by agheboianu on 01.03.2016.
 */
@Controller
public class AwesomeController {

    @Autowired
    AwesomeService awesomeService;

    @RequestMapping("/")
    public
    @ResponseBody
    String beAwesome() {
        return "I am awesome!";
    }

    @RequestMapping(value = "/html", method = RequestMethod.GET)
    public String beMoreAwesome(
            @RequestParam(value = "name", required = false, defaultValue = "Alex") String name,
            Model model) {
        model.addAttribute("name", awesomeService.formatName(name));
        return "index";
    }

    @RequestMapping(value = "/list")
    public String printParams(Model model) {
        String[] names = {"Alex", "George", "Marius"};
        model.addAttribute("names", names);
        String[] names2 = {"Alex", "George", "Marius"};
        model.addAttribute("names2", names2);
        return "list";
    }
}

package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.academyplus.service.AwesomeService;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String printParams(Model model) {
        String[] param = {"Alex", "George", "Marius"};
        model.addAttribute("names", param);
        return "index";
    }




}

package ro.academyplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by MM on 2016-03-05.
 */
@Controller
public class MembersController {

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String controlMembers(
            @RequestParam(value = "name", required = false, defaultValue = "Alex") String name,
            @RequestParam(value = "id", required = false, defaultValue = "001") String id,
            @RequestParam(value = "email", required = false, defaultValue = "email@email.com") String email,
            @RequestParam(value = "password", required = false, defaultValue = "password") String password,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "members";
    }
}

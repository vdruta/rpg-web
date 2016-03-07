package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

/**
 * Created by MM on 2016-03-05.
 */
@Controller
public class MembersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String controlMembers(
            @RequestParam(value = "id", required = false, defaultValue = "") String id,
            Model model) {
        User user = userRepository.getOne(Long.parseLong(id));
        model.addAttribute("user", user);
        return "members";

    }
/*
    @RequestMapping(value = "/members", method = RequestMethod.POST)
*/
}

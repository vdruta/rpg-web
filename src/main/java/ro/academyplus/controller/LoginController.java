
package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.dto.LoginDTO;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.service.LoginService;

import javax.validation.Valid;

/**
 * Created by MM on 2016-03-07.
 */


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLogin(@RequestParam(value = "failed", required = false, defaultValue = "") String failed, Model model) {
        if (!failed.isEmpty()) {
            model.addAttribute("failed", true);
        }
        return "login";
    }
}

/*
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String createLoginForm(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("logindto", loginDTO);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticateUser(@ModelAttribute(value = "logindto") @Valid LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.hasErrors());
            System.out.println("Login error");
            return "login";
        }
        User userModel = loginService.verifyLogin(loginDTO);
        if (userModel == null) {
            System.out.println("asdasd");
            return "login";
        }
        return "redirect:members?id="+userModel.getId();
    }
}
*/
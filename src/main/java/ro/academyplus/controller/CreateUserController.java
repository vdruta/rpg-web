package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.service.CreateUserService;
import javax.validation.Valid;

/**
 * Created by MM on 2016-03-04.
 */
@Controller
class CreateUserController {

    @Autowired
    CreateUserService createUserService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserForm(Model model) {
        UserDTO user = new UserDTO();
        user.setName("");
        user.setEmail("");
        model.addAttribute("user", user);
        model.addAttribute("pagetitle", "Sign Up");
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String sendUser(@ModelAttribute(value = "user") @Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        User userModel = createUserService.registerUser(user);
        return "redirect:members?name="+userModel.getName()+"&email="+userModel.getEmail()+"&password="+userModel.getPassword()+"&id="+userModel.getId();
    }



}
package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.dto.LoginDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;
import ro.academyplus.service.HeroService;

import javax.validation.Valid;

/**
 * Created by MM on 2016-03-06.
 */
@Controller
public class HeroController {
    @Autowired
    UserRepository userRepository;
    HeroService heroService;


    @RequestMapping(value = "/createhero", method = RequestMethod.GET)
    public String createHeroForm(
            @RequestParam(value = "id", required = false, defaultValue = "001") String id,
            Model model) {
        HeroDTO heroDTO = new HeroDTO();
        heroDTO.setName("florea");
        heroDTO.setType("Mage");
        model.addAttribute("herodto", heroDTO);
        return "createhero";
    }

    @RequestMapping(value = "/createhero", method = RequestMethod.POST)
    public String createHero (
            @ModelAttribute(value = "herodto") @Valid HeroDTO heroDTO,
            BindingResult bindingResult,
            @RequestParam(value = "id", required = false, defaultValue = "001") String id ) throws ClassNotFoundException {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "createhero";
        }
        User userModel = userRepository.findOne(Long.parseLong(id));
        if (userModel == null) {
            return "login";
        }
        userModel.addHero(heroService.createHero(heroDTO));
        return "redirect:members?id="+userModel.getId();
    }

}

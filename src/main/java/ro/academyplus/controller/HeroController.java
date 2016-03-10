package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Orc;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.repository.UserRepository;
import ro.academyplus.service.HeroService;
import ro.academyplus.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MM on 2016-03-06.
 */
@Controller
public class HeroController {
    @Autowired
    HeroService heroService;
    @Autowired
    UserService userService;
    @Autowired
    HeroRepository heroRepository;

    @RequestMapping(value = "/createhero", method = RequestMethod.GET)
    public String createHeroForm(Model model) {
        HeroDTO heroDTO = new HeroDTO();
        List<String> heroes = new ArrayList<String>();
        heroes.add("Elf");
        heroes.add("Mage");
        heroes.add("Knight");
        heroes.add("Orc");
        heroDTO.setHtypes(heroes);
        heroDTO.setName("");
        model.addAttribute("herodto", heroDTO);
        return "createhero";
    }

    @RequestMapping(value = "/createhero", method = RequestMethod.POST)
    public String createHero (@ModelAttribute(value = "herodto") @Valid HeroDTO heroDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error creating hero");
            return "createhero";
        }
        Hero hero = heroService.createHero(heroDTO);
        userService.addNewHero(hero);

        return "redirect:members";
    }

    @RequestMapping(value = "/edithero", method = RequestMethod.GET)
    public String createHeroForm(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                               Model model){
        Hero hero = heroRepository.findOne(Long.parseLong(id));
        HeroDTO heroDTO = new HeroDTO();
        heroDTO.setName(hero.getName());
        heroDTO.setTmpid(hero.getId());
        heroDTO.setDelete(false);
        model.addAttribute("herodto", heroDTO);
        return "edithero";
    }

    @RequestMapping(value = "/edithero", method = RequestMethod.POST)
    public String saveHero(@ModelAttribute(value = "herodto") @Valid HeroDTO heroDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error editing hero");
            return "edithero";
        }
        heroService.updateHero(heroDTO);
        return "redirect:members";
    }

}

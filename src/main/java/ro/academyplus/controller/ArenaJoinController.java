package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Arena;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.service.MissionService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
@Controller
public class ArenaJoinController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ServletContext servletContext;
    @Autowired
    HeroRepository heroRepository;

    @RequestMapping(value = "/arenajoin", method = RequestMethod.GET)
    public String joinArena(@RequestParam(value = "id", required = false, defaultValue = "0") String id){
        Hero hero = (Hero) request.getSession().getAttribute("hero");
        Hero heroFromId = heroRepository.findOne(Long.parseLong(id));
        if (heroFromId.equals(hero)) {
            return "redirect:arena?id="+Long.parseLong(id);
        }
        else {
            servletContext.getContext("/arena?id="+Long.parseLong(id));
            servletContext.setAttribute("hero2", hero);
            Arena arena = (Arena) servletContext.getAttribute("arena");
            arena.setHero2(hero);
            return "redirect:arena?id="+Long.parseLong(id);
        }
    }
}

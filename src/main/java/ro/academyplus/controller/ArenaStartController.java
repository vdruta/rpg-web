package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.dto.ArenaJoinDTO;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.Arena;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.repository.HeroRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created by MM on 2016-03-09.
 */
@Controller
public class ArenaStartController {
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/arenastart", method = RequestMethod.GET)
    public String initArena(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {

        Hero hero = heroRepository.findOne(Long.parseLong(id));
        request.getSession().setAttribute("hero", hero);
        servletContext.setAttribute("hero1", hero);

        Arena arena = new Arena("Lions Arena");
        arena.setHero1(hero);
        servletContext.setAttribute("arena", arena);

        return "redirect:arenaconfig?id="+Long.parseLong(id);
    }

    @RequestMapping(value = "/arenajoin", method = RequestMethod.GET)
    public String createJoinForm(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                 Model model) {

        Hero hero = heroRepository.findOne(Long.parseLong(id));
        request.getSession().setAttribute("hero", hero);

        ArenaJoinDTO arenaJoinDTO = new ArenaJoinDTO();
        arenaJoinDTO.setJoinId("");
        model.addAttribute("join", arenaJoinDTO);
        return "arenajoin";
        //return "redirect:arenaconfig?id="+28;
    }

    @RequestMapping(value = "/arenajoin", method = RequestMethod.POST)
    public String joinArena(@ModelAttribute(value = "join") @Valid ArenaJoinDTO arenaJoinDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "arenajoin";
        }

        //Hero hero = (Hero) request.getSession().getAttribute("hero");
        //request.getSession().setAttribute("hero", hero);

        return "redirect:arenaconfig?id="+arenaJoinDTO.getJoinId();
    }
}
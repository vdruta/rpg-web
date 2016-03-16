package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.dto.ArenaDTO;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Arena;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.service.ArenaService;
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
public class ArenaController {
    @Autowired
    ArenaService arenaService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/arena", method = RequestMethod.GET)
    public String createArenaPage(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                  Model model){
        Arena arena = (Arena) servletContext.getAttribute("arena");
        model.addAttribute("hero1", arena.getHero1());
        model.addAttribute("hero2", arena.getHero2());
        model.addAttribute("arena", arena);
        model.addAttribute("map", arena.getMap());

        ArenaDTO arenaDTO = new ArenaDTO();
        List<String> actions = new ArrayList<String>();
        actions.add("Up");
        actions.add("Down");
        actions.add("Left");
        actions.add("Right");
        arenaDTO.setActions(actions);
        List<String> actions2 = new ArrayList<String>();
        actions2.add("Up");
        actions2.add("Down");
        actions2.add("Left");
        actions2.add("Right");
        arenaDTO.setActions2(actions2);
        model.addAttribute("arenadto", arenaDTO);

        model.addAttribute("tourn1", arena.isTourn1());
        model.addAttribute("tourn2", arena.isTourn2());
        return "arena";
    }

    @RequestMapping(value = "/arena", method = RequestMethod.POST)
    public String updateArena(@ModelAttribute(value = "arenadto") @Valid ArenaDTO arenaDTO,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members";
        }


        Hero heroSession = (Hero) request.getSession().getAttribute("hero");
        Arena arena = (Arena) servletContext.getAttribute("arena");
        if (heroSession.equals(arena.getHero1())) {
            arenaService.processArena1(arenaDTO);
        }
        else {
            arenaService.processArena2(arenaDTO);
        }


        //arenaService.processArena(arenaDTO);

        return "redirect:arena";
    }
}

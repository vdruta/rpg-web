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
    //@Autowired
    //ArenaService arenaService;
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

    /*    MissionDTO missionDTO = new MissionDTO();
        List<String> actions = new ArrayList<String>();
        actions.add("Up");
        actions.add("Down");
        actions.add("Left");
        actions.add("Right");
        missionDTO.setActions(actions);
        List<String> fightActions = new ArrayList<String>();
        fightActions.add("Fight");
        fightActions.add("Run");
        missionDTO.setFightActions(fightActions);
        List<String> keepOrDropActions = new ArrayList<String>();
        keepOrDropActions.add("Keep");
        keepOrDropActions.add("Drop");
        missionDTO.setGetOrDropActions(keepOrDropActions);
        model.addAttribute("missiondto", missionDTO); */
        return "arena";
        //return "arena?id="+Long.parseLong(id);
    }
/*
    @RequestMapping(value = "/arena", method = RequestMethod.POST)
    public String updateMissionPage(@ModelAttribute(value = "arenadto") @Valid ArenaDTO arenaDTO,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "members";
        }
        arenaService.playMission(missionDTO);
        return "redirect:mission";
    }*/
}

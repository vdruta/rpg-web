package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.academyplus.dto.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.service.MissionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
@Controller
public class MissionController {
    @Autowired
    MissionService missionService;

    @RequestMapping(value = "/mission", method = RequestMethod.GET)
    public String createMissionPage(HttpServletRequest request,
                                    Model model){
        Hero hero = (Hero) request.getSession().getAttribute("hero");
        model.addAttribute("hero", hero);
        Mission mission = (Mission) request.getSession().getAttribute("mission");
        model.addAttribute("map", mission.getMap());
        model.addAttribute("win", mission.isWin());
        model.addAttribute("monster", mission.isMonster());

        MissionDTO missionDTO = new MissionDTO();
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
        model.addAttribute("missiondto", missionDTO);
        return "mission";
    }

    @RequestMapping(value = "/mission", method = RequestMethod.POST)
    public String updateMissionPage(@ModelAttribute(value = "missiondto") @Valid MissionDTO missionDTO,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "members";
        }
        missionService.playMission(missionDTO);
        return "redirect:mission";
    }
}

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
import ro.academyplus.dto.validators.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.service.MissionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MM on 2016-03-09.
 */
@Controller
public class MissionController {
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    MissionService missionService;

    @RequestMapping(value = "/mission", method = RequestMethod.GET)
    public String createMissionPage(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                 Model model){
        Hero hero = heroRepository.findOne(Long.parseLong(id));
        Mission mission = Mission.getInstance(hero.getLevel());
        //add hero to mission
        mission.setHero(hero);

        MissionDTO missionDTO = new MissionDTO();
        //add actions dropdown box
        List<String> actions = new ArrayList<String>();
        actions.add("Up");
        actions.add("Down");
        actions.add("Left");
        actions.add("Right");
        missionDTO.setActions(actions);
        //add hero
        missionDTO.setHero(hero);
        //add map
        missionDTO.setMap(mission.getMap());
        //add villains
        missionDTO.setVillains(mission.getVillains());

        model.addAttribute("missiondto", missionDTO);
        return "mission?id="+hero.getId();
    }

    @RequestMapping(value = "/mission", method = RequestMethod.POST)
    public String updateMissionPage(@ModelAttribute(value = "missiondto") @Valid MissionDTO missionDTO,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "members";
        }
        missionService.updateMission(missionDTO);

        return "mission?id="+missionDTO.getHero().getId();
    }
}

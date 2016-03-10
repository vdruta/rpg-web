package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.repository.HeroRepository;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by MM on 2016-03-09.
 */
@Controller
public class MissionStartController {
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/startmission", method = RequestMethod.GET)
    public String initMission(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
        Hero hero = heroRepository.findOne(Long.parseLong(id));
        request.getSession().setAttribute("hero", hero);
        Mission mission = new Mission(hero.getLevel());
        request.getSession().setAttribute("mission", mission);
        return "redirect:mission";
    }

}
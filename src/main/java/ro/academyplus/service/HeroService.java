package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.model.User;
import ro.academyplus.model.characters.*;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.repository.UserRepository;

import java.util.List;

/**
 * Created by MM on 2016-03-07.
 */
@Service
public class HeroService {
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    UserRepository userRepository;

    public Hero createHero(HeroDTO heroDTO) {
        Hero hero = new Hero("default");
        if (heroDTO.getSelectedType().compareTo("Elf") == 0)
            hero = (Hero) (new Elf(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Mage") == 0)
            hero = (Hero) (new Mage(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Knight") == 0)
            hero = (Hero) (new Knight(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Orc") == 0)
            hero = (Hero) (new Orc(heroDTO.getName()));
        heroRepository.save(hero);
        return (hero);
    }

    public void saveHero(HeroDTO heroDTO) {
        Hero hero = new Hero("default");
        if (heroDTO.getSelectedType().compareTo("Elf") == 0)
            hero = (Hero) (new Elf(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Mage") == 0)
            hero = (Hero) (new Mage(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Knight") == 0)
            hero = (Hero) (new Knight(heroDTO.getName()));
        else if (heroDTO.getSelectedType().compareTo("Orc") == 0)
            hero = (Hero) (new Orc(heroDTO.getName()));
        heroRepository.save(hero);
    }

    public void updateHero(HeroDTO heroDTO) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Hero> heros = user.getHeroes();
        for (Hero h : heros) {
            if (h.getId() == heroDTO.getTmpid()) {
                if (heroDTO.isDelete()) {
                    //delete hero from user heroes + update user
                    heros.remove(h);
                    userRepository.saveAndFlush(user);
                    //delete hero from database
                    heroRepository.delete(h);
                    heroRepository.flush();
                    return ;
                }
                h.setName(heroDTO.getName());
                heroRepository.save(h);
            }
        }
    }
}
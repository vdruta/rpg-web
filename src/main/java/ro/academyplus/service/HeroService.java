package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.dto.LoginDTO;
import ro.academyplus.model.User;
import ro.academyplus.model.characters.Elf;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.repository.UserRepository;

/**
 * Created by MM on 2016-03-07.
 */
@Service
public class HeroService {

    public Hero createHero(HeroDTO heroDTO) throws ClassNotFoundException{

        Hero hero = new Hero("");
        try {
            hero = (Hero) Class.forName("ro.academyplus.model.characters." + heroDTO.getType()).newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return hero;
    }
    /*
        if (heroDTO.getHeroType().compareTo("ELF") == 0)
            hero = new Elf(heroDTO.getName());
        if (heroDTO.getHeroType().compareTo("MAGE") == 0)
            hero = new Elf(heroDTO.getName());
        if (heroDTO.getHeroType().compareTo("KNIGHT") == 0)
            hero = new Elf(heroDTO.getName());
        if (heroDTO.getHeroType().compareTo("ORC") == 0)
            hero = new Elf(heroDTO.getName());
        return (hero);
    }
    */
}

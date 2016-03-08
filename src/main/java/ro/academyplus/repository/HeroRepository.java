package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.User;
import ro.academyplus.model.characters.Hero;

import java.util.List;

/**
 * Created by agheboianu on 04.03.2016.
 */
public interface HeroRepository extends JpaRepository<Hero,Long>{
    public Hero findOneByName(String email);
    public List<Hero> findByNameLike(String name);
    public int countByName(String name);
}
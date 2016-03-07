package ro.academyplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.academyplus.model.User;

import java.util.List;

/**
 * Created by agheboianu on 04.03.2016.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    public User findOneByEmail(String email);
    public List<User> findByNameLike(String name);
    public int countByName(String name);

}
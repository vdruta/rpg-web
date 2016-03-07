package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

import java.util.List;

/**
 * Created by MM on 2016-03-03.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(UserDTO userDTO) {

        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        String encodedPass = passEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(encodedPass);
        userRepository.save(user);
        return user;
    }

    public void testQueryMethods() {
        User user = userRepository.findOneByEmail("gigi@example.com");
        System.out.println(user.getName() + " " + user.getId());

        List<User> userList = userRepository.findByNameLike("%igi%");
        System.out.println("List size: " + userList.size());

        int count = userRepository.countByName("Gigi");
        System.out.println(count);
    }

}

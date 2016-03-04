package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

/**
 * Created by MM on 2016-03-03.
 */
@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
}

package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.LoginDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

/**
 * Created by MM on 2016-03-07.
 */
@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public User verifyLogin(LoginDTO loginDTO) {
        User user = userRepository.findOneByEmail(loginDTO.getEmail());
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        if (passEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            return user;
        return null;
    }
}

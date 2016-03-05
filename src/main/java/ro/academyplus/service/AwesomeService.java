package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

/**
 * Created by agheboianu on 03.03.2016.
 */

@Service
public class AwesomeService {


    public String formatName(String name) {
        return "Mr." + name;
    }

    public String formatAddress(String address) {
        return "Address: " + address;
    }


}

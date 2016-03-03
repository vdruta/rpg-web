package ro.academyplus.service;

import org.springframework.stereotype.Service;

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

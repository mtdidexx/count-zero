package net.katrinka.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mdonahue on 7/18/17.
 */
@RestController
public class OrderServices {
    @RequestMapping("/")
    public String defaultService() {
        return "They set a slamhound on Turner's tail...";
    }
}

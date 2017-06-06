package es.colles.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PORTATIL on 05/06/2017.
 */
@Configuration
@EnableScheduling
public class MapsConfig {

//    @Autowired
//    private QueryHome queryHome;

    private List<String> modes = new ArrayList<>();


    @PostConstruct
    private void initializeModes() {
        modes.add("driving");
        modes.add("walking");
        modes.add("bicycling");
//        queryHome.setModes(modes);
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }
}

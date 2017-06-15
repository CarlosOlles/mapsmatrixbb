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

    private List<String> modes = new ArrayList<>();
    private List<String> units = new ArrayList<>();

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    @PostConstruct
    private void initializeModes() {
        modes.add("driving");
        modes.add("walking");
        modes.add("bicycling");
    }

    @PostConstruct
    private void initializeUnits() {
        units.add("metric");
        units.add("imperial");
    }


}

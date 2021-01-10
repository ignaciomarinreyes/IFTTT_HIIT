package model.IFTTT.system;

import model.IFTTT.environment.Actuator;

public class Action {
    private final Actuator actuator;
    private final Object threshold;

    public Action(Actuator actuator, Object threshold) {
        this.actuator = actuator;
        this.threshold = threshold;
    }

    public void actuate(){
        actuator.actuate(threshold);
    }
}

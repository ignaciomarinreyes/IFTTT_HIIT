package model.IFTTT.system;

import model.IFTTT.environment.Sensor;

public class Condition {
    private final Sensor sensor;
    private final Object threshold;
    private final RelationalOperator relationalOperator;

    public Condition(Sensor sensor, Object threshold, RelationalOperator relationalOperator) {
        this.sensor = sensor;
        this.threshold = threshold;
        this.relationalOperator = relationalOperator;
    }

    public boolean evaluate(){
        return relationalOperator.evaluate(threshold, sensor.getValue());
    }
}

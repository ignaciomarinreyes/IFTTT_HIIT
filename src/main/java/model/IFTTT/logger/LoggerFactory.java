package model.IFTTT.logger;

import model.IFTTT.environment.Sensor;
import model.IFTTT.environment.sensor.Timer;

public class LoggerFactory {
    public static Sensor getLoggerTimer(Object timer){
        return (Sensor) java.lang.reflect.Proxy.newProxyInstance(timer.getClass().getClassLoader(),
                                                                    new Class[] {Sensor.class},
                                                                    new LoggerTimer((Timer) timer));
    }

}

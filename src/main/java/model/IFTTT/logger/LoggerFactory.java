package model.IFTTT.logger;

import model.IFTTT.environment.Actuator;
import model.IFTTT.environment.Sensor;
import model.IFTTT.environment.actuator.SportAlarm;
import model.IFTTT.environment.sensor.Timer;

public class LoggerFactory {
    public static Sensor getLoggerTimer(Object timer){
        return (Sensor) java.lang.reflect.Proxy.newProxyInstance(timer.getClass().getClassLoader(),
                                                                    new Class[] {Sensor.class},
                                                                    new LoggerTimer((Timer) timer));
    }

    public static Actuator getLoggerActuator(Object actuator){
        return (Actuator) java.lang.reflect.Proxy.newProxyInstance(actuator.getClass().getClassLoader(),
                new Class[] {Actuator.class},
                new LoggerSportAlarm((SportAlarm) actuator));
    }

}

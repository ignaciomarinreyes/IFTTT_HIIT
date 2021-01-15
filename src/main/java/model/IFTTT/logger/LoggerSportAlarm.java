package model.IFTTT.logger;

import model.IFTTT.environment.Actuator;
import model.IFTTT.environment.Sensor;
import model.IFTTT.environment.actuator.SportAlarm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerSportAlarm implements InvocationHandler {

    private Actuator actuator;

    public LoggerSportAlarm(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(actuator, args);
        // Mostrar pantalla, guardar en fichero
        Logger logger_0 = LogManager.getLogger("logger_file");
        Logger logger_1 = LogManager.getLogger("stdout");

        SportAlarm sportAlarm = (SportAlarm) actuator;

        logger_0.info("La persona está en el estado: " + sportAlarm.getPerson().getStatus());
        logger_1.error("La persona está en el estado: " + sportAlarm.getPerson().getStatus());

        return null;
    }
}

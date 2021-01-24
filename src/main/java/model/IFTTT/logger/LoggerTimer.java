package model.IFTTT.logger;

import model.IFTTT.environment.Sensor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerTimer implements InvocationHandler {

    private Sensor timer;

    public LoggerTimer(Sensor timer) {
        this.timer = timer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Integer resultSensor = (Integer) method.invoke(timer, args);
        Logger logger_0 = LogManager.getLogger("logger_file");
        Logger logger_1 = LogManager.getLogger("stdout");
        logger_0.info("El valor del cronómetro es: " + resultSensor);
        logger_1.info("El valor del cronómetro es: " + resultSensor);
        return resultSensor;
    }
}

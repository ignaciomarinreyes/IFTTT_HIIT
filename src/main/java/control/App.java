package control;

import model.IFTTT.environment.Actuator;
import model.IFTTT.environment.Sensor;
import model.IFTTT.environment.actuator.SportAlarm;
import model.IFTTT.environment.sensor.Timer;
import model.IFTTT.logger.LoggerFactory;
import model.IFTTT.system.*;
import model.Person;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import view.PersonFrame;
import view.TimerFrame;

public class App {

    private static Rule[] rules;
    private static boolean executing;

    public static void main(String[] args) {
        configurateLog4J();
        Person person = new Person("Ronnie Coleman");
        person.start();
        Timer timer = new Timer();
        Sensor loggerTimer = LoggerFactory.getLoggerTimer(timer);
        Actuator sportAlarm = new SportAlarm("Garmin Forerunner 45 L/G", person);
        Actuator loggerSportAlarm = LoggerFactory.getLoggerActuator(sportAlarm);
        rules = new Rule[4];

        rules[0] = new Rule(0, "Rule to stop", "Person stop if it is a stop time interval");
        Condition conditionBetweenEqual1 = new Condition(loggerTimer,
                new IntervalInteger(150,179),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionStop = new Action(loggerSportAlarm, Person.STATUS.STOPPED);
        rules[0].addCondition(conditionBetweenEqual1);
        rules[0].addAction(actionStop);

        rules[1] = new Rule(1, "Rule to run", "Person run if it is a run time interval");
        Condition conditionBetweenEqual2 = new Condition(loggerTimer,
                new IntervalInteger(0,29),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Condition conditionBetweenEqual3 = new Condition(loggerTimer,
                new IntervalInteger(60,89),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Condition conditionBetweenEqual4 = new Condition(loggerTimer,
                new IntervalInteger(120,149),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRun = new Action(loggerSportAlarm, Person.STATUS.RUNNING);
        rules[1].addCondition(conditionBetweenEqual2);
        rules[1].addCondition(conditionBetweenEqual3);
        rules[1].addCondition(conditionBetweenEqual4);
        rules[1].addAction(actionRun);

        rules[2] = new Rule(2, "Rule to run fast", "Person run fast if it is a run fast time interval");
        Condition conditionBetweenEqual5 = new Condition(loggerTimer,
                new IntervalInteger(30,59),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunFast = new Action(loggerSportAlarm, Person.STATUS.RUNNING_FAST);
        rules[2].addCondition(conditionBetweenEqual5);
        rules[2].addAction(actionRunFast);

        rules[3] = new Rule(3, "Rule to run very fast", "Person run very fast if it is a run very fast time interval");
        Condition conditionBetweenEqual6 = new Condition(loggerTimer,
                new IntervalInteger(90,119),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunVeryFast = new Action(loggerSportAlarm, Person.STATUS.RUNNING_VERY_FAST);
        rules[3].addCondition(conditionBetweenEqual6);
        rules[3].addAction(actionRunVeryFast);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(executing){
                    rules[0].execute();
                    rules[1].execute();
                    rules[2].execute();
                    rules[3].execute();
                }
            }
        }).start();

        PersonFrame personFrame = new PersonFrame(person);
        new TimerFrame(timer, personFrame);

    }

    private static void configurateLog4J() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        /**/
        AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
        console.addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        console.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(console);

        /**/
        AppenderComponentBuilder file = builder.newAppender("log", "File");
        file.addAttribute("FileName", "log/logging.log");
        file.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(file);

        /**/
        LoggerComponentBuilder firstLogger = builder.newLogger("logger_file", Level.INFO);
        firstLogger.add(builder.newAppenderRef("log"));
        firstLogger.addAttribute("additivity", false);
        builder.add(firstLogger);

        /**/
        RootLoggerComponentBuilder rootLooger = builder.newRootLogger(Level.INFO);
        rootLooger.add(builder.newAppenderRef("stdout"));
        builder.add(console);

        /**/
        Configurator.initialize(builder.build());
    }

    public static void setExecuting(boolean executing) {
        App.executing = executing;
    }
}

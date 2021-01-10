package control;

import model.IFTTT.environment.Actuator;
import model.IFTTT.environment.Sensor;
import model.IFTTT.environment.SportAlarm;
import model.IFTTT.environment.Timer;
import model.IFTTT.system.*;
import model.Person;
import view.PersonFrame;
import view.TimerFrame;

public class App {

    private static Rule[] rules;
    private static boolean executing;

    public static void main(String[] args) {
        Person person = new Person("Ronnie Coleman");
        person.start();
        Sensor timer = new Timer();
        Actuator sportAlarm = new SportAlarm("Garmin Forerunner 45 L/G", person);
        rules = new Rule[4];

        rules[0] = new Rule(0, "Rule to stop", "Person stop if it is a stop time interval");
        Condition conditionBetweenEqual1 = new Condition(timer,
                new IntervalInteger(150,179),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionStop = new Action(sportAlarm, Person.STATUS.STOPPED);
        rules[0].addCondition(conditionBetweenEqual1);
        rules[0].addAction(actionStop);

        rules[1] = new Rule(1, "Rule to run", "Person run if it is a run time interval");
        Condition conditionBetweenEqual2 = new Condition(timer,
                new IntervalInteger(0,29),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Condition conditionBetweenEqual3 = new Condition(timer,
                new IntervalInteger(60,89),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Condition conditionBetweenEqual4 = new Condition(timer,
                new IntervalInteger(120,149),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRun = new Action(sportAlarm, Person.STATUS.RUNNING);
        rules[1].addCondition(conditionBetweenEqual2);
        rules[1].addCondition(conditionBetweenEqual3);
        rules[1].addCondition(conditionBetweenEqual4);
        rules[1].addAction(actionRun);

        rules[2] = new Rule(2, "Rule to run fast", "Person run fast if it is a run fast time interval");
        Condition conditionBetweenEqual5 = new Condition(timer,
                new IntervalInteger(30,59),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunFast = new Action(sportAlarm, Person.STATUS.RUNNING_FAST);
        rules[2].addCondition(conditionBetweenEqual5);
        rules[2].addAction(actionRunFast);

        rules[3] = new Rule(3, "Rule to run very fast", "Person run very fast if it is a run very fast time interval");
        Condition conditionBetweenEqual6 = new Condition(timer,
                new IntervalInteger(90,119),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunVeryFast = new Action(sportAlarm, Person.STATUS.RUNNING_VERY_FAST);
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
        new TimerFrame((Timer) timer, personFrame);

    }

    public static void setExecuting(boolean executing) {
        App.executing = executing;
    }
}

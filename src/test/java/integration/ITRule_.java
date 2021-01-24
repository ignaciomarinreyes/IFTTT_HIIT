package integration;

import model.IFTTT.environment.actuator.SportAlarm;
import model.IFTTT.environment.sensor.Timer;
import model.IFTTT.system.*;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class ITRule_ {
    private Person person;
    private Timer timer;
    private SportAlarm sportAlarm;

    @Before
    public void setUp() {
        person = new Person("Ronnie Coleman");
        person.start();
        timer = Mockito.mock(Timer.class);
        sportAlarm = new SportAlarm("Garmin Forerunner 45 L/G", person);
    }

    @Test
    public void could_person_stop_if_it_is_a_stop_time_interval() {
        doReturn(151).when(timer).getValue();
        Rule rule = new Rule(0, "Rule to stop", "Person stop if it is a stop time interval");
        Condition conditionBetweenEqual1 = new Condition(timer,
                new IntervalInteger(150,179),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionStop = new Action(sportAlarm, Person.STATUS.STOPPED);
        rule.addCondition(conditionBetweenEqual1);
        rule.addAction(actionStop);
        rule.execute();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Stopped");
    }

    @Test
    public void could_person_run_if_it_is_a_run_time_interval() {
        doReturn(10).when(timer).getValue();
        Rule rule = new Rule(1, "Rule to run", "Person run if it is a run time interval");
        Condition conditionBetweenEqual1 = new Condition(timer,
                new IntervalInteger(0,29),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRun = new Action(sportAlarm, Person.STATUS.RUNNING);
        rule.addCondition(conditionBetweenEqual1);
        rule.addAction(actionRun);
        rule.execute();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running");
    }

    @Test
    public void could_person_run_fast_if_it_is_a_run_fast_time_interval() {
        doReturn(40).when(timer).getValue();
        Rule rule = new Rule(2, "Rule to run fast", "Person run fast if it is a run fast time interval");
        Condition conditionBetweenEqual1 = new Condition(timer,
                new IntervalInteger(30,59),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunFast = new Action(sportAlarm, Person.STATUS.RUNNING_FAST);
        rule.addCondition(conditionBetweenEqual1);
        rule.addAction(actionRunFast);
        rule.execute();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running fast");
    }

    @Test
    public void could_person_run_very_fast_if_it_is_a_run_very_fast_time_interval() {
        doReturn(100).when(timer).getValue();
        Rule rule = new Rule(3, "Rule to run very fast", "Person run very fast if it is a run very fast time interval");
        Condition conditionBetweenEqual1 = new Condition(timer,
                new IntervalInteger(90,119),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        Action actionRunVeryFast = new Action(sportAlarm, Person.STATUS.RUNNING_VERY_FAST);
        rule.addCondition(conditionBetweenEqual1);
        rule.addAction(actionRunVeryFast);
        rule.execute();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running very fast");
    }
}
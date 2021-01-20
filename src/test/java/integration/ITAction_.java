package integration;

import model.IFTTT.environment.actuator.SportAlarm;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ITAction_ {

    private SportAlarm sportAlarmMock;

    @Before
    public void setUp() throws Exception {
        sportAlarmMock = mock(SportAlarm.class);
    }

    @Test
    public void could_change_status_person_to_running() {
        //Mockito.when(sportAlarmMock.actuate(Person.STATUS.RUNNING)).then(sportAlarmMock.getPerson().setStatus(Person.STATUS.RUNNING));
        //assertThat(sportAlarmMock.getPerson().getStatus()).isEqualTo(Person.STATUS.RUNNING);
    }
}
/*
    doReturn(0).when(timerMock).getValue();
        Condition conditionBetweenEqual = new Condition(timerMock,
        new IntervalInteger(0, 10),
        new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        assertTrue(conditionBetweenEqual.evaluate());*/

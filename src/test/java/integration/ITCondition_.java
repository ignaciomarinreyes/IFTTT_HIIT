package integration;

import model.IFTTT.environment.sensor.Timer;
import model.IFTTT.system.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ITCondition_ {

    private Timer timerMock;

    @Before
    public void setUp() {
        timerMock = mock(Timer.class);
    }

    @Test
    public void could_answer_true_intervalInteger_condition_between_equal_when_value_sensor_0() {
        doReturn(0).when(timerMock).getValue();
        Condition conditionBetweenEqual = new Condition(timerMock,
                                                        new IntervalInteger(0, 10),
                                                        new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        assertTrue(conditionBetweenEqual.evaluate());
    }

    @Test
    public void could_answer_true_intervalInteger_condition_between_equal_when_value_sensor_15() {
        doReturn(15).when(timerMock).getValue();
        Condition conditionBetweenEqual = new Condition(timerMock,
                new IntervalInteger(10, 25),
                new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL));
        assertTrue(conditionBetweenEqual.evaluate());
    }

    @Test
    public void could_answer_true_Integer_condition_less_equal_than_when_value_sensor_6() {
        doReturn(6).when(timerMock).getValue();
        Condition conditionLessEqualThan = new Condition(timerMock,
                5,
                new IntegerRelationalOperator(OPERATOR.LESS_EQUAL_THAN));
        assertTrue(conditionLessEqualThan.evaluate());
    }

    @Test
    public void could_answer_true_Integer_condition_greater_equal_than_when_value_sensor_6() {
        doReturn(6).when(timerMock).getValue();
        Condition conditionGreaterEqualThan = new Condition(timerMock,
                8,
                new IntegerRelationalOperator(OPERATOR.GREATER_EQUAL_THAN));
        assertTrue(conditionGreaterEqualThan.evaluate());
    }
}

package unitary;

import model.IFTTT.system.IntegerRelationalOperator;
import model.IFTTT.system.IntervalInteger;
import model.IFTTT.system.OPERATOR;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestIntegerRelationalOperator_ {

    @Test
    public void could_answer_true_if_value_is_between_equal_threshold() {
        IntegerRelationalOperator integerRelationalOperator = new IntegerRelationalOperator(OPERATOR.BETWEEN_EQUAL);
        assertTrue(integerRelationalOperator.evaluate(new IntervalInteger(50 , 60), 55));
        assertTrue(integerRelationalOperator.evaluate(new IntervalInteger(50 , 60), 50));
        assertTrue(integerRelationalOperator.evaluate(new IntervalInteger(50 , 60), 60));
    }

    @Test
    public void could_answer_true_if_threshold_less_equal_than_value() {
        IntegerRelationalOperator integerRelationalOperator = new IntegerRelationalOperator(OPERATOR.LESS_EQUAL_THAN);
        assertTrue(integerRelationalOperator.evaluate(new Integer(60), 61));
        assertTrue(integerRelationalOperator.evaluate(new Integer(60), 60));
    }

    @Test
    public void could_answer_true_if_threshold_it_is_greater_equal_than_value() {
        IntegerRelationalOperator integerRelationalOperator = new IntegerRelationalOperator(OPERATOR.GREATER_EQUAL_THAN);
        assertTrue(integerRelationalOperator.evaluate(new Integer(61), 60));
        assertTrue(integerRelationalOperator.evaluate(new Integer(60), 60));
    }
}

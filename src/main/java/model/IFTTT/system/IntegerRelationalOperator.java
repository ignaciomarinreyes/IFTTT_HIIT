package model.IFTTT.system;

public class IntegerRelationalOperator extends RelationalOperator{

    public IntegerRelationalOperator(OPERATOR operator) {
        super(operator);
    }

    @Override
    protected boolean less_equal_than(Object leftObject, Object rightObject) {
        return (Integer)leftObject <= (Integer) rightObject;
    }

    @Override
    protected boolean greater_equal_than(Object leftObject, Object rightObject) {
        return (Integer)leftObject >= (Integer) rightObject;
    }

    @Override
    protected boolean between_equal(Object leftObject, Object rightObject) {
        Integer lowerLimit = ((IntervalInteger) leftObject).getLowerLimit();
        Integer upperLimit = ((IntervalInteger) leftObject).getUpperLimit();
        return  less_equal_than(lowerLimit, rightObject) && greater_equal_than(upperLimit, rightObject);
    }
}

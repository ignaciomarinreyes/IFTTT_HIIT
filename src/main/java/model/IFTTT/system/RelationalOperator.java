package model.IFTTT.system;

public abstract class RelationalOperator {
    private final OPERATOR operator;

    public RelationalOperator(OPERATOR operator){
        this.operator = operator;
    }

    public boolean evaluate(Object leftObject, Object rightObject){
        switch(operator){
            case LESS_EQUAL_THAN:
                return less_equal_than(leftObject, rightObject);
            case GREATER_EQUAL_THAN:
                return greater_equal_than(leftObject, rightObject);
            case BETWEEN_EQUAL:
                return between_equal(leftObject, rightObject);
            default:
                return false;
        }
    }

    protected abstract boolean less_equal_than(Object leftObject, Object rightObject);
    protected abstract boolean greater_equal_than(Object leftObject, Object rightObject);
    protected abstract boolean between_equal(Object leftObject, Object rightObject);
}

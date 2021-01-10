package model.IFTTT.system;

public class IntervalInteger {
    private Integer lowerLimit;
    private Integer upperLimit;

    public IntervalInteger(Integer lowerLimit, Integer upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }
}

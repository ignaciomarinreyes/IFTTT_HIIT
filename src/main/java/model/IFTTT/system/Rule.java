package model.IFTTT.system;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private final int id;
    private final String name;
    private final String description;
    private List<Condition> conditions;
    private List<Action> actions;

    public Rule(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.conditions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void execute() {
        if (isTrueSomeCondition()) {
            actAllActions();
        }
    }

    private void actAllActions() {
        for (Action action : actions) action.actuate();
    }

    private boolean isTrueSomeCondition() {
        for (Condition condition : conditions) {
            if (condition.evaluate()) return true;
        }
        return false;
    }
}

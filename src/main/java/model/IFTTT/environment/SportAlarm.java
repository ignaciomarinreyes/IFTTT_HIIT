package model.IFTTT.environment;

import model.Person;

public class SportAlarm implements Actuator{

    private final String name;
    private final Person person;

    public SportAlarm(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    @Override
    public void actuate(Object status) {
        person.setStatus((Person.STATUS) status);
    }
}

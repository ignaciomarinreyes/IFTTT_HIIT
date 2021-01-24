package model.IFTTT.environment.actuator;

import model.IFTTT.environment.Actuator;
import model.Person;

public class SportAlarm implements Actuator {

    private final String name;
    private Person person;

    public SportAlarm(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    @Override
    public void actuate(Object status) {
        person.setStatus((Person.STATUS) status);
    }

    public Person getPerson() {
        return person;
    }

}

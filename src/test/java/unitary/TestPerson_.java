package unitary;

import model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPerson_ {

    private Person person;

    @Before
    public void setUp() {
        person = new Person("Ronnie Coleman");
        person.start();
    }

    @Test
    public void could_simulate_heartRate_stopped() {
        person.setStatus(Person.STATUS.STOPPED);
        assertTrue(person.getHeartRate() > 70 && person.getHeartRate() < 80);
    }

    @Test
    public void could_simulate_heartRate_running() {
        person.setStatus(Person.STATUS.RUNNING);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(person.getHeartRate() > 100 && person.getHeartRate() < 120);
    }

    @Test
    public void could_simulate_heartRate_running_fast() {
        person.setStatus(Person.STATUS.RUNNING_FAST);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(person.getHeartRate() > 140 && person.getHeartRate() < 160);
    }

    @Test
    public void could_simulate_heartRate_running_very_fast() {
        person.setStatus(Person.STATUS.RUNNING_VERY_FAST);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(person.getHeartRate() > 180 && person.getHeartRate() < 200);
    }

}

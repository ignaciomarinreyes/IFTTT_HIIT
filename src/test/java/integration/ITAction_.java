package integration;

import model.IFTTT.environment.actuator.SportAlarm;
import model.IFTTT.system.Action;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class ITAction_ {

    private Person person;

    @Before
    public void setUp() {
        person = Mockito.mock(Person.class);
    }

    @Test
    public void could_change_status_person_to_stopped() {
        Mockito.doReturn("Stopped").when(person).getStatus();
        SportAlarm sportAlarm = new SportAlarm("Ronnie Coleman", person);
        Action actionStop = new Action(sportAlarm, Person.STATUS.STOPPED);
        actionStop.actuate();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Stopped");
    }

    @Test
    public void could_change_status_person_to_running() {
        Mockito.doReturn("Running").when(person).getStatus();
        SportAlarm sportAlarm = new SportAlarm("Ronnie Coleman", person);
        Action actionRun = new Action(sportAlarm, Person.STATUS.RUNNING);
        actionRun.actuate();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running");
    }

    @Test
    public void could_change_status_person_to_running_fast() {
        Mockito.doReturn("Running fast").when(person).getStatus();
        SportAlarm sportAlarm = new SportAlarm("Ronnie Coleman", person);
        Action actionRunFast = new Action(sportAlarm, Person.STATUS.RUNNING_FAST);
        actionRunFast.actuate();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running fast");
    }

    @Test
    public void could_change_status_person_to_running_very_fast() {
        Mockito.doReturn("Running very fast").when(person).getStatus();
        SportAlarm sportAlarm = new SportAlarm("Ronnie Coleman", person);
        Action actionRunVeryFast = new Action(sportAlarm, Person.STATUS.RUNNING_VERY_FAST);
        actionRunVeryFast.actuate();
        assertThat(sportAlarm.getPerson().getStatus()).isEqualTo("Running very fast");
    }
}

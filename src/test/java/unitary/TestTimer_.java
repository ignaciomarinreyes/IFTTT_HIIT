package unitary;

import model.IFTTT.environment.sensor.Timer;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTimer_ {

    private Timer timer;

    @Before
    public void setUp() throws Exception {
        timer = new Timer();
    }

    @Test
    public void coud_increase_one_second() {
        timer.increaseASecond();
        assertThat(timer.getValue()).isEqualTo(new Integer(1));
    }

    @Test
    public void could_reset() {
        timer.increaseASecond();
        timer.increaseASecond();
        timer.reset();
        assertThat(timer.getValue()).isEqualTo(new Integer(0));
    }
}

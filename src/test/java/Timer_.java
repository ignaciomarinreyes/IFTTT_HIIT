import model.IFTTT.environment.sensor.Timer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Timer_ {

    @Test
    public void coud_increase_one_second() {
        Timer timer = new Timer();
        timer.increaseASecond();
        assertThat(timer.getValue()).isEqualTo(new Integer(1));
    }
}

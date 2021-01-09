package control;

import model.IFTTT.environment.Timer;
import model.Person;
import view.PulsometerFrame;
import view.TimerFrame;

public class App {

    public static void main(String[] args) {
        Person person = new Person();
        person.start();
        new PulsometerFrame(person);
        new TimerFrame(new Timer());
    }
}

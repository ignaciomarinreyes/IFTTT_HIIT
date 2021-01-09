package control;

import model.Person;
import view.PulsometerFrame;

public class App {

    public static void main(String[] args) {
        Person person = new Person();
        person.start();
        new PulsometerFrame(person);
    }
}

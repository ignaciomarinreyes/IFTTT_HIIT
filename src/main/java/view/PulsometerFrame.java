package view;

import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PulsometerFrame extends JFrame {
    private JPanel rootPane;
    private JButton increaseSpeedButton;
    private JButton reduceSpeedButton;
    private JTextField heartRatePulsometer;
    private JLabel status;
    private Person person;

    public PulsometerFrame(Person person) {
        super("Pulsometer");
        this.person = person;
        setContentPane(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 300);
        setSize(300, 150);
        setResizable(false);
        setVisible(true);
        status.setText(person.getStatus());
        increaseSpeedButton.addActionListener(new increaseButton());
        reduceSpeedButton.addActionListener(new reduceButton());
        new Task().execute();
    }

    private class Task extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() throws Exception {
            while (true) {
                heartRatePulsometer.setText(String.valueOf(person.getHeartRate()));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class reduceButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int speed = person.getSpeed();
            if (speed > 1) {
                speed--;
                person.setSpeed(speed);
                status.setText(person.getStatus());
            }
        }
    }

    private class increaseButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int speed = person.getSpeed();
            if (speed < 4) {
                speed++;
                person.setSpeed(speed);
                status.setText(person.getStatus());
            }
        }
    }
}

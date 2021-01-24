package view;

import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonFrame extends JFrame {
    
    private JPanel rootPane;
    private JTextField heartRate;
    private JLabel status;
    private JButton runFast;
    private JButton runVeryFast;
    private JButton stopped;
    private JButton run;
    private Person person;

    public PersonFrame(Person person) {
        super(person.getNamePerson());
        this.person = person;
        setContentPane(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 300);
        setSize(350, 200);
        setResizable(false);
        setVisible(true);
        status.setText(person.getStatus());
        new Task().execute();
        stopped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setStatus(Person.STATUS.STOPPED);
                status.setText(person.getStatus());
            }
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setStatus(Person.STATUS.RUNNING);
                status.setText(person.getStatus());
            }
        });
        runFast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setStatus(Person.STATUS.RUNNING_FAST);
                status.setText(person.getStatus());
            }
        });
        runVeryFast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setStatus(Person.STATUS.RUNNING_VERY_FAST);
                status.setText(person.getStatus());
            }
        });
    }

    private class Task extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() throws Exception {
            while (true) {
                heartRate.setText(String.valueOf(person.getHeartRate()));
                status.setText(person.getStatus());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void disableStatusButton(){
        runFast.setEnabled(false);
        runVeryFast.setEnabled(false);
        stopped.setEnabled(false);
        run.setEnabled(false);
    }

    public void enableStatusButton(){
        runFast.setEnabled(true);
        runVeryFast.setEnabled(true);
        stopped.setEnabled(true);
        run.setEnabled(true);
    }

    public void stopPerson(){
        person.setStatus(Person.STATUS.STOPPED);
    }
}

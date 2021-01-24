package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.App;
import model.IFTTT.environment.sensor.Timer;

public class TimerFrame extends JFrame {
    private Timer timer;
    private JPanel rootPane;
    private JButton start;
    private JButton pause;
    private JButton stop;
    private JLabel valueTimer;
    private javax.swing.Timer t;
    private PersonFrame personFrame;

    public TimerFrame(Timer timer, PersonFrame personFrame) {
        super("IFTTT");
        this.timer = timer;
        this.personFrame = personFrame;
        setContentPane(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(800, 300);
        setSize(300,150);
        setResizable(false);
        setVisible(true);
        t = new javax.swing.Timer(1000, actions);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                start.setEnabled(false);
                start.setText("Resume");
                pause.setEnabled(true);
                stop.setEnabled(true);
                App.setExecuting(true);
                personFrame.disableStatusButton();
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                start.setEnabled(true);
                pause.setEnabled(false);
                App.setExecuting(false);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.isRunning())
                {
                    t.stop();
                    start.setEnabled(true);
                }
                start.setText("Start");
                pause.setEnabled(false);
                stop.setEnabled(false);
                timer.reset();
                updateLabel();
                personFrame.stopPerson();
                App.setExecuting(false);
                personFrame.enableStatusButton();
            }
        });
    }

    private ActionListener actions = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            timer.increaseASecond();
            updateLabel();
        }
    };

    private void updateLabel() {
        String time = (timer.getH()<=9?"0":"")+timer.getH() +":"
                + (timer.getM()<=9?"0":"")+timer.getM()+":"
                + (timer.getS()<=9?"0":"")+timer.getS();
        valueTimer.setText(time);
    }

}

package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IFTTT.environment.Timer;

public class TimerFrame extends JFrame {
    private model.IFTTT.environment.Timer timer;
    private JPanel rootPane;
    private JButton start;
    private JButton pause;
    private JButton stop;
    private JLabel valueTimer;
    private javax.swing.Timer t;

    public TimerFrame(Timer timer) {
        super("IFTTT");
        this.timer = timer;
        setContentPane(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(800, 300);
        setSize(300,150);
        setResizable(false);
        setVisible(true);
        t = new javax.swing.Timer(10, actions);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                start.setEnabled(false);
                start.setText("Resume");
                pause.setEnabled(true);
                stop.setEnabled(true);
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                start.setEnabled(true);
                pause.setEnabled(false);
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
            }
        });
    }

    private ActionListener actions = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            timer.increaseACentisecond();
            updateLabel();
        }
    };

    private void updateLabel() {
        String time = (timer.getH()<=9?"0":"")+timer.getH() +":"
                + (timer.getM()<=9?"0":"")+timer.getM()+":"
                + (timer.getS()<=9?"0":"")+timer.getS()+":"
                + (timer.getCs()<=9?"0":"")+timer.getCs();
        valueTimer.setText(time);
    }
}

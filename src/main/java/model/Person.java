package model;

import java.util.Random;

public class Person extends Thread {

    private String name;
    private int heartRate;
    private Random r;
    private STATUS status;

    public Person(String name) {
        this.name = name;
        this.heartRate = 75;
        r = new Random();
        this.status = STATUS.STOPPED;
    }

    @Override
    public void run() {
        while (true) {
            switch (status) {
                case STOPPED:
                    simulateHeartRate(70, 80);
                    break;
                case RUNNING:
                    simulateHeartRate(100, 120);
                    break;
                case RUNNING_FAST:
                    simulateHeartRate(140, 160);
                    break;
                case RUNNING_VERY_FAST:
                    simulateHeartRate(180, 200);
                    break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void decreaseHeartRate() {
        heartRate = heartRate - r.nextInt(4);
    }

    private synchronized void increaseHeartRate() {
        heartRate = heartRate + r.nextInt(4);
    }

    private synchronized void simulateHeartRate(int lowerLimit, int upperLimit) {
        if (heartRate < lowerLimit) increaseHeartRate();
        else if (heartRate > upperLimit) decreaseHeartRate();
        else heartRate = heartRate + (int) Math.pow(-1, (double) r.nextInt(2)) * r.nextInt(3);
    }

    public synchronized int getHeartRate() {
        return heartRate;
    }

    public enum STATUS {
        STOPPED, RUNNING, RUNNING_FAST, RUNNING_VERY_FAST
    }

    public String getStatus() {
        switch (status) {
            case STOPPED:
                return "Stopped";
            case RUNNING:
                return "Running";
            case RUNNING_FAST:
                return "Running fast";
            case RUNNING_VERY_FAST:
                return "Running very fast";
        }
        return null;
    }

    public void setStatus(Person.STATUS status) {
        this.status = status;
    }

    public String getNamePerson(){
        return name;
    }
}

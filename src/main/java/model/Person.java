package model;

import java.util.Random;

public class Person extends Thread {

    private int heartRate;
    private Random r;
    private int speed;

    public Person() {
        this.heartRate = 75;
        r = new Random();
        speed = 1;
    }

    @Override
    public void run() {
        while (true) {
            switch (speed) {
                case 1:
                    simulateHeartRate(70, 80);
                    break;
                case 2:
                    simulateHeartRate(100, 120);
                    break;
                case 3:
                    simulateHeartRate(140, 160);
                    break;
                case 4:
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

    public String getStatus() {
        switch (speed) {
            case 1:
                return "Stopped";
            case 2:
                return "Running";
            case 3:
                return "Running fast";
            case 4:
                return "Running very fast";
        }
        return null;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

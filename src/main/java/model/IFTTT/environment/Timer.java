package model.IFTTT.environment;

public class Timer implements Sensor{
    private int h, m, s;

    public void increaseASecond() {
        ++s;
        if (s == 60) {
            s = 0;
            ++m;
        }
        if (m == 60) {
            m = 0;
            ++h;
        }
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }

    public void reset() {
        h=0; m=0; s=0;
    }

    @Override
    public Object getValue() {
        return new Integer(convertTimeToSeconds());
    }

    private int convertTimeToSeconds() {
        return h*3600+m*60+s;
    }
}

package model.IFTTT.environment;

public class Timer {
    private int h, m, s, cs;

    public void increaseACentisecond() {
        ++cs;
        if (cs == 100) {
            cs = 0;
            ++s;
        }
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

    public int getCs() {
        return cs;
    }

    public void reset() {
        h=0; m=0; s=0; cs=0;
    }
}

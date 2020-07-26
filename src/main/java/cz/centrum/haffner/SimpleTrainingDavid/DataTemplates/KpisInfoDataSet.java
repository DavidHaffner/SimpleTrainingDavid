package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

public class KpisInfoDataSet {

    // TODO: rename variables, getters and setters
    private int a;           // Total number of processed JSON files
    private int b;           // Total number of rows
    private int c;           // Total number of calls
    private int d;           // Total number of messages
    private int e;           // Total number of different origin country codes
    private int f;           // Total number of different destination country codes
    private int g;           // Duration of each JSON process

    public KpisInfoDataSet(int a, int b, int c, int d, int e, int f, int g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}

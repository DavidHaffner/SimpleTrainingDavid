package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

public class MetricsInfoSet {

    // TODO: rename variables, getters and setters
    private int a;        // Number of rows with missing fields
    private int b;        // Number of messages with blank content
    private int c;        // Number of rows with fields errors
    private int d;        // Number of calls origin/destination grouped by country code
    private int e;        // Relationship between OK/KO calls
    private int f;        // Average call duration grouped by country code
    private int g;        // Word occurrence ranking for the given words in message_content field


    public MetricsInfoSet(int a, int b, int c, int d, int e, int f, int g) {
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

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public int getE() {
        return e;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setE(int e) {
        this.e = e;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }
}

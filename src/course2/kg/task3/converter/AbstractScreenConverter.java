package course2.kg.task3.converter;

public abstract class AbstractScreenConverter {
    private double xR, yR, wR, hR;
    private int wS, hS;

    public abstract double getxR();

    public abstract void setxR(double xR);

    public abstract double getyR();

    public abstract void setyR(double yR);

    public abstract double getwR();

    public abstract void setwR(double wR);

    public abstract double gethR();

    public abstract void sethR(double hR);

    public abstract int getwS();

    public abstract void setwS(int wS);

    public abstract int gethS();

    public abstract void sethS(int hS);
}

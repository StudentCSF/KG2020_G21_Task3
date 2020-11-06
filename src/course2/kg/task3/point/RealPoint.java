package course2.kg.task3.point;

public class RealPoint {
    private double x, y;

    public RealPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public RealPoint(RealPoint point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

package course2.kg.task3.point;


public class CurvePoint<T extends AbstractPoint> {
    private T point;
    private boolean isPrimary;

    public CurvePoint(T point, boolean isPrimary) {
        this.point = point;
        this.isPrimary = isPrimary;
    }

    public CurvePoint(CurvePoint<T> other) {
        this.point = other.getPoint();
        this.isPrimary = other.isPrimary();
    }

    public T getPoint() {
        return point;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isSecondary() {
        return !isPrimary;
    }
}

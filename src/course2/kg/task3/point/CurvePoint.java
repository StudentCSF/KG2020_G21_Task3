package course2.kg.task3.point;


import course2.kg.task3.utils.PrimarySecondaryContainer;

public class CurvePoint<T extends AbstractPoint> extends PrimarySecondaryContainer<T> {

    public CurvePoint(T point, boolean isPrimary) {
        super(point, isPrimary);
    }

    public CurvePoint(PrimarySecondaryContainer<T> other) {
        super(other);
    }

    public T getPoint() {
        return getValue();
    }
}

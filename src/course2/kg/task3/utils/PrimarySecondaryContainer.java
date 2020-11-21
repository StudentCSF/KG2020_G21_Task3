package course2.kg.task3.utils;


public class PrimarySecondaryContainer<T> {
    private T value;
    private boolean isPrimary;

    public PrimarySecondaryContainer(T point, boolean isPrimary) {
        this.value = point;
        this.isPrimary = isPrimary;
    }

    public PrimarySecondaryContainer(PrimarySecondaryContainer<T> other) {
        this.value = other.getValue();
        this.isPrimary = other.isPrimary();
    }

    public T getValue() {
        return value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isSecondary() {
        return !isPrimary;
    }
}

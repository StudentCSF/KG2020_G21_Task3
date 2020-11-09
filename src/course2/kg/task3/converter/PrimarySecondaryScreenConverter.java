package course2.kg.task3.converter;

public abstract class PrimarySecondaryScreenConverter<TA, TB> extends AbstractScreenConverter {
    abstract TB convertToB(TA a);
    abstract TA convertToA(TB b);
}

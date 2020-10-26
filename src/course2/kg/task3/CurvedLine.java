package course2.kg.task3;

public class CurvedLine {
    RealPoint p1, p2, p3, p4;

    public CurvedLine(RealPoint p1, RealPoint p2, RealPoint p3, RealPoint p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public RealPoint getP1() {
        return p1;
    }

    public RealPoint getP2() {
        return p2;
    }

    public RealPoint getP3() {
        return p3;
    }

    public RealPoint getP4() {
        return p4;
    }
}

package course2.kg.task3.curved_line;

import course2.kg.task3.point.RealPoint;

import java.util.List;

public class CurvedLine {
    List<RealPoint> allPoints;

    public CurvedLine(List<RealPoint> points) {
        this.allPoints = points;
    }

    public List<RealPoint> getAllPoints() {
        return allPoints;
    }
}

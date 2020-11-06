package course2.kg.task3.curved_line;

import course2.kg.task3.point.RealPoint;

import java.util.ArrayList;
import java.util.List;

public class CurvedLine {
    List<RealPoint> allPoints = null;

    public CurvedLine(List<RealPoint> points) {
        this.allPoints = points;
    }

    public List<RealPoint> getAllPoints() {
        if (allPoints == null) return new ArrayList<>();
        return allPoints;
    }
}

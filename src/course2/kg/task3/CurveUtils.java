package course2.kg.task3;

import course2.kg.task3.curved_line.CurvedLine;
import course2.kg.task3.point.CurvePoint;
import course2.kg.task3.point.RealPoint;

import java.util.ArrayList;
import java.util.List;

public class CurveUtils {
    public static CurvedLine countCurrentCurve(CurvedLine start, CurvedLine end, double s) {
        if (start != null && end != null && canAnimated(start, end)) {
            List<CurvePoint<RealPoint>> pointsForCurrentCurve = new ArrayList<>();
            for (int i = 0; i < start.getAllPoints().size(); i++) {
                double sx = start.getAllPoints().get(i).getPoint().getX();
                double ex = end.getAllPoints().get(i).getPoint().getX();
                double x = sx + (ex - sx) * s;
                double sy = start.getAllPoints().get(i).getPoint().getY();
                double ey = end.getAllPoints().get(i).getPoint().getY();
                double y = sy + s * (ey - sy);
                boolean isPrim = start.getAllPoints().get(i).isPrimary();
                pointsForCurrentCurve.add(new CurvePoint<>(new RealPoint(x, y), isPrim));
            }
            return new CurvedLine(pointsForCurrentCurve);
        }
        return null;
    }

    private static boolean canAnimated(CurvedLine l1, CurvedLine l2) {
        if (l1.getAllPoints().size() != l2.getAllPoints().size()) return false;
        List<CurvePoint<RealPoint>> pts1 = l1.getAllPoints();
        List<CurvePoint<RealPoint>> pts2 = l2.getAllPoints();
        for (int i = 0; i < pts1.size(); i++) {
            if (pts1.get(i).isPrimary() && pts2.get(i).isSecondary() || pts1.get(i).isSecondary() && pts2.get(i).isPrimary())
                return false;
        }
        return true;
    }
}

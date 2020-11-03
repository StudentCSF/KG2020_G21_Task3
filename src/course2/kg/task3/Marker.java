package course2.kg.task3;

import course2.kg.task3.curved_line.drawer.CurvedLineDrawer;
import course2.kg.task3.line.drawer.LineDrawer;
import course2.kg.task3.point.BasicScreenPoint;
import course2.kg.task3.point.RealPoint;
import course2.kg.task3.point.ScreenPoint;

import java.util.ArrayList;
import java.util.List;

public class Marker {
    private RealPoint center;
    private static final double R = 0.01;

    public Marker(RealPoint center) {
        this.center = center;
    }

    public void draw(LineDrawer ld, ScreenConverter sc) {
        ld.drawLine(sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R)), sc.r2s(new RealPoint(this.center.getX() + R, this.center.getY() - R)));
        ld.drawLine(sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R)), sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() + R)));
        ld.drawLine(sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() + R)), sc.r2s(new RealPoint(this.center.getX() + R, this.center.getY() + R)));
        ld.drawLine(sc.r2s(new RealPoint(this.center.getX() + R, this.center.getY() - R)), sc.r2s(new RealPoint(this.center.getX() + R, this.center.getY() + R)));
       /* List<ScreenPoint> pts = new ArrayList<>();
        ScreenPoint p1 = sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R));
        ScreenPoint p2 = sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R));
        ScreenPoint p3 = sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R));
        ScreenPoint p4 = sc.r2s(new RealPoint(this.center.getX() - R, this.center.getY() - R));
        pts.add(new BasicScreenPoint())*/
    }
}

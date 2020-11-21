package course2.kg.task3.drawers.curve;

import course2.kg.task3.point.CurvePoint;
import course2.kg.task3.point.ScreenPoint;

import java.awt.*;
import java.util.List;

public interface CurvedLineDrawer {
    void draw(List<CurvePoint<ScreenPoint>> l, double step);
    void draw(List<CurvePoint<ScreenPoint>> l, double step, Color color);
}

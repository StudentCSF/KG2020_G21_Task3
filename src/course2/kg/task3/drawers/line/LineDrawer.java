package course2.kg.task3.drawers.line;

import course2.kg.task3.point.ScreenPoint;

import java.awt.*;

public interface LineDrawer {
    void drawLine(ScreenPoint p1, ScreenPoint p2, Color color);
    void drawLine(ScreenPoint p1, ScreenPoint p2);
}

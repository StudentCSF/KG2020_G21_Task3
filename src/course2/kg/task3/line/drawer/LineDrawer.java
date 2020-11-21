package course2.kg.task3.line.drawer;

import course2.kg.task3.point.ScreenPoint;

import java.awt.*;

public interface LineDrawer {
    void drawLine(ScreenPoint p1, ScreenPoint p2, Color color);
    void drawLine(ScreenPoint p1, ScreenPoint p2);
    void drawDottedLine(ScreenPoint p1, ScreenPoint p2, Color color);
    void drawDottedLine(ScreenPoint p1, ScreenPoint p2);
}

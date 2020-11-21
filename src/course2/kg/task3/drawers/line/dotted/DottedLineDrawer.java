package course2.kg.task3.drawers.line.dotted;

import course2.kg.task3.point.ScreenPoint;

import java.awt.*;

public interface DottedLineDrawer {
    void drawDottedLine(ScreenPoint p1, ScreenPoint p2, Color color);
    void drawDottedLine(ScreenPoint p1, ScreenPoint p2);
}

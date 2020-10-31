package course2.kg.task3;

import java.awt.*;
import java.util.List;

public interface CurvedLineDrawer {

    static final double step = 0.00001;

    void drawCurvedLine(List<ScreenPoint> l);
    void drawCurvedLine(List<ScreenPoint> l, Color color);
}

package course2.kg.task3.drawers.line;

import course2.kg.task3.drawers.pixel.PixelDrawer;
import course2.kg.task3.point.ScreenPoint;

import java.awt.*;

public class DottedLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public DottedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2, Color color) {
        int x1 = p1.getX(), y1 = p1.getY();
        int x2 = p2.getX(), y2 = p2.getY();
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (Math.abs(dy) > Math.abs(dx)) {
            double reversek = dx / dy;

            if (y1 > y2) {
                int tmp = y2;
                y2 = y1;
                y1 = tmp;
                tmp = x2;
                x2 = x1;
                x1 = tmp;
            }
            int counter = 0;
            for (int i = y1; i < y2; i++, counter++) {
                double j = (i - y1) * reversek + x1;
                int ij = j - (int) j > 0.5 ? (int) j + 1 : (int) j;
                if (counter % 4 < 2)
                    pd.colorPixel(ij, i, color);
            }
        } else {

            double k = dy / dx;
            if (x1 > x2) {
                int tmp = y2;
                y2 = y1;
                y1 = tmp;
                tmp = x2;
                x2 = x1;
                x1 = tmp;
            }
            int counter = 0;
            for (int j = x1; j <= x2; j++, counter++) {
                double i = (j - x1) * k + y1;
                int ii = i - (int) i > 0.5 ? (int) i + 1 : (int) i;
                if (counter % 4 < 2)
                    pd.colorPixel(j, ii, color);
            }
        }
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2) {
        drawLine(p1, p2, Color.BLUE);
    }
}

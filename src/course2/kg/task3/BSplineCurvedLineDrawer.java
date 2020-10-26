/*package course2.kg.task3;

import java.awt.*;

public class BSplineCurvedLineDrawer implements CurvedLineDrawer {
    private PixelDrawer pd;
    double step = 0.00001;

    public BSplineCurvedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    public void setStep(double step) {
        this.step = step;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2, ScreenPoint p3, ScreenPoint p4) {
        int t = 0;
        for (int i = 0; i < 1 / step; i++, t += step) {
            double st = t * t;
            double ct =  st * t;
            double x = p1.getX() * (2 * ct - 3 * st +1) + p3.getX() * (3 * st - 2 * ct) + (p2.getX() - p1.getX()) * (ct - 2 * st + t) + (p4.getX() - p3.getX()) * (ct - st);
            double y = p1.getY() * (2 * ct - 3 * st +1) + p3.getY() * (3 * st - 2 * ct) + (p2.getY() - p1.getY()) * (ct - 2 * st + t) + (p4.getY() - p3.getY()) * (ct - st);
            pd.colorPixel((int) x, (int) y, Color.BLACK);
        }
    }
}*/

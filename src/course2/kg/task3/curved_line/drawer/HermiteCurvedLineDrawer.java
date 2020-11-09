package course2.kg.task3.curved_line.drawer;

import course2.kg.task3.curved_line.drawer.CurvedLineDrawer;
import course2.kg.task3.pixel_drawer.PixelDrawer;
import course2.kg.task3.point.ScreenPoint;

import java.awt.*;
import java.util.List;

public class HermiteCurvedLineDrawer/* implements CurvedLineDrawer*/ {
    private PixelDrawer pd;
    private double step = 0.00001;

    public HermiteCurvedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    public void setStep(double step) {
        this.step = step;
    }

    //@Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2, ScreenPoint p3, ScreenPoint p4) {
        for (double t = 0; t <= 1; t += step) {
            double st = t * t;
            double ct =  st * t;
            double x = p1.getX() * (2 * ct - 3 * st +1) + p2.getX() * (3 * st - 2 * ct) + p3.getX() * (ct - 2 * st + t) + p4.getX() * (ct - st);
            double y = p1.getY() * (2 * ct - 3 * st +1) + p2.getY() * (3 * st - 2 * ct) + p3.getY() * (ct - 2 * st + t) + p4.getY() * (ct - st);
            pd.colorPixel((int) x, (int) y, Color.BLACK);
        }
    }
/*
    @Override
    public void draw(List<ScreenPoint> l) {

    }

    @Override
    public void draw(List<ScreenPoint> l, Color color) {

    }*/
}

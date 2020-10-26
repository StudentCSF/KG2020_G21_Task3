/*package course2.kg.task3;

import java.awt.*;

public class BezierCurvedLineDrawer implements CurvedLineDrawer {
    private PixelDrawer pd;
    private double step = 0.00001;

    public BezierCurvedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    public void setStep(double newStep) {
        this.step = newStep;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2, ScreenPoint p3, ScreenPoint p4) {
        for (double t = 0; t <= 1; t += step) {
            double x = Math.pow(1 - t, 3) * p1.getX() + 3 * Math.pow(1 - t, 2) * t * p2.getX() + 3 * (1 - t) * t * t * p3.getX() + t * t * t * p4.getX();
            double y = Math.pow(1 - t, 3) * p1.getY() + 3 * Math.pow(1 - t, 2) * t * p2.getY() + 3 * (1 - t) * t * t * p3.getY() + t * t * t * p4.getY();
            pd.colorPixel((int)x, (int)y, Color.BLACK);
        }
    }
}*/

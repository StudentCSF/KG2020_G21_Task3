package course2.kg.task3.curved_line.drawer;

import course2.kg.task3.pixel_drawer.PixelDrawer;
import course2.kg.task3.point.CurvePoint;
import course2.kg.task3.point.ScreenPoint;
import course2.kg.task3.utils.MathUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BezierCurvedLineDrawer implements CurvedLineDrawer {
    private PixelDrawer pd;

    public BezierCurvedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void draw(List<CurvePoint<ScreenPoint>> l, double step, Color color) {
        if (l.size() < 1) return;
        else if (l.size() == 1 && l.get(0).isPrimary()) {
            pd.colorPixel(l.get(0).getPoint().getX(), l.get(0).getPoint().getY());
            return;
        }
        List<CurvePoint<ScreenPoint>> pointsForFragment = new ArrayList<>();
        for (CurvePoint<ScreenPoint> curr : l) {
            pointsForFragment.add(curr);
            if (curr.isPrimary() && pointsForFragment.size() > 1) {
                drawFragment(pointsForFragment, step, color);
                if (l.indexOf(curr) != l.size() - 1) {
                    pointsForFragment = new ArrayList<>();
                    pointsForFragment.add(curr);
                }
            }
        }
    }

    private void drawFragment(List<CurvePoint<ScreenPoint>> pointsForFragment, double step, Color c) {
        int size = pointsForFragment.size();
        int[] coeffs = MathUtils.getBinomCoeffs(size - 1);
        for (double t = 0; t <= 1; t += step) {
            double x = 0;
            double y = 0;
            double mult2 = Math.pow(1 - t, size - 1);
            double mult3 = 1;
            for (int i = 0; i < size; i++) {
                ScreenPoint p = pointsForFragment.get(i).getPoint();
                int mult1 = coeffs[i];
                x += mult1 * mult2 * mult3 * p.getX();
                y += mult1 * mult2 * mult3 * p.getY();
                if (Math.abs(t - 1) < step) {
                    mult2 = 0;
                } else {
                    mult2 /= (1 - t);
                }
                mult3 *= t;
            }
            pd.colorPixel((int) x, (int) y, c);
        }
    }

    @Override
    public void draw(List<CurvePoint<ScreenPoint>> l, double step) {
        draw(l, step, Color.BLACK);
    }
}

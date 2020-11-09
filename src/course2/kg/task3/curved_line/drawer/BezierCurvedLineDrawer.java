package course2.kg.task3.curved_line.drawer;

import course2.kg.task3.pixel_drawer.PixelDrawer;
import course2.kg.task3.point.CurvePoint;
import course2.kg.task3.point.ScreenPoint;
import course2.kg.task3.point.SecondaryScreenPoint;

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
        int[] coeffs = getBinomCoeffs(size - 1);
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

    private int[] getBinomCoeffs(int degree) {
        int[] coeffs = new int[degree + 1];
        for (int i = 0; i < coeffs.length / 2 + 1; i++) {
            int v = countCombination(degree, i);
            coeffs[i] = v;
            coeffs[coeffs.length - 1 - i] = v;
        }
        return coeffs;
    }

    private int countCombination(int n, int k) {
        int res = 1;
        for (int i = k + 1; i <= n; i++) {
            res *= i;
        }
        int mult = 1;
        for (int i = 2; i <= n - k; i++) {
            mult *= i;
        }
        return res / mult;
    }

    @Override
    public void draw(List<CurvePoint<ScreenPoint>> l, double step) {
        draw(l, step, Color.BLACK);
    }
}

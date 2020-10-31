package course2.kg.task3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BezierCurvedLineDrawer implements CurvedLineDrawer {
    private PixelDrawer pd;

    public BezierCurvedLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawCurvedLine(List<ScreenPoint> l, Color color) {
        if (l.size() < 1) return;
        else if (l.size() == 1) {
            pd.colorPixel(l.get(0).getX(), l.get(0).getY());
            return;
        }
        List<ScreenPoint> pointsForFragment = new ArrayList<>();
        for (ScreenPoint curr : l) {
            pointsForFragment.add(curr);
            if (curr instanceof BasicScreenPoint && pointsForFragment.size() > 1) {
                drawFragmentCurvedLine(pointsForFragment, color);
                if (l.indexOf(curr) != l.size() - 1) {
                    pointsForFragment = new ArrayList<>();
                    pointsForFragment.add(curr);
                }
            }
        }
    }

    private void drawFragmentCurvedLine(List<ScreenPoint> pointsForFragment, Color c) {
        int size = pointsForFragment.size();
        for (double t = 0; t <= 1; t += step) {
            double x = 0;
            double y = 0;
            for (int i = 0; i < size; i++) {
                ScreenPoint p = pointsForFragment.get(i);
                int mult1 = countCombination(size - 1, i);
                double mult2 = Math.pow(1 - t, size - i - 1);
                double mult3 = Math.pow(t, i);
                x += mult1 * mult2 * mult3 * p.getX();
                y += mult1 * mult2 * mult3 * p.getY();
            }
            pd.colorPixel((int) x, (int) y, c);
        }
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
        //System.out.println(res/mult);
        return res / mult;
    }

    @Override
    public void drawCurvedLine(List<ScreenPoint> l) {
        drawCurvedLine(l, Color.BLACK);
    }
}

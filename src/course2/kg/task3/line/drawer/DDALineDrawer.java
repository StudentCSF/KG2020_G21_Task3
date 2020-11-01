package course2.kg.task3.line.drawer;

import course2.kg.task3.line.drawer.LineDrawer;
import course2.kg.task3.pixel_drawer.PixelDrawer;
import course2.kg.task3.point.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DDALineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public  DDALineDrawer(PixelDrawer pd){
        this.pd= pd;
    }

    @Override
    public void  drawLine(ScreenPoint p1, ScreenPoint p2){
        int x1 = p1.getX(), y1 = p1.getY();
        int x2 = p2.getX(), y2 = p2.getY();
        double dx =x2-x1;
        double dy=y2-y1;

        if(Math.abs(dy)>Math.abs(dx)){
            double reversek= dx/dy;

            if(y1>y2){
                int tmp=y2;y2=y1; y1=tmp;
                tmp =x2;x2=x1;x1=tmp;
            }
            for(int i=y1;i<y2;i++){
                double j =(i-y1)* reversek+x1;
                pd.colorPixel((int) j, i, Color.BLACK);
            }
        }else {

            double k = dy/dx;
            if(x1>x2) {
                int tmp = y2;
                y2 = y1;
                y1 = tmp;
                tmp = x2;
                x2 = x1;
                x1 = tmp;
            }
                for(int j =x1; j<=x2; j++){
                    double i =(j-x1)* k+y1;
                    pd.colorPixel(j,(int)i,Color.BLACK);

                }
            }
        }


    public void drawBezierCurvedLine(ScreenPoint p1, ScreenPoint p2, ScreenPoint p3, ScreenPoint p4) {
        List<ScreenPoint> points = pointsForBezier(p1, p2, p3, p4);
        for (int i = 1; i < points.size(); i++) {
            //drawLine(points.get(i - 1), points.get(i));
            pd.colorPixel(points.get(i).getX(), points.get(i).getY(), Color.BLACK);
        }
    }

    private List<ScreenPoint> pointsForBezier(ScreenPoint p1, ScreenPoint p2, ScreenPoint p3, ScreenPoint p4) {
        List<ScreenPoint> list = new ArrayList<>();
        for (double t = 0; t <= 1; t += 0.00001) {
            double x = Math.pow(1 - t, 3) * p1.getX() + 3 * Math.pow(1 - t, 2) * t * p2.getX() + 3 * (1 - t) * t * t * p3.getX() + t * t * t * p4.getX();
            double y = Math.pow(1 - t, 3) * p1.getY() + 3 * Math.pow(1 - t, 2) * t * p2.getY() + 3 * (1 - t) * t * t * p3.getY() + t * t * t * p4.getY();
            System.out.println(x + " " + y);
            list.add(new ScreenPoint((int) x, (int) y));
        }
        return list;
    }
}


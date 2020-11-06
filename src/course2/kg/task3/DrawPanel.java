package course2.kg.task3;

import course2.kg.task3.curved_line.drawer.BezierCurvedLineDrawer;
import course2.kg.task3.curved_line.CurvedLine;
import course2.kg.task3.curved_line.drawer.CurvedLineDrawer;
import course2.kg.task3.line.drawer.DDALineDrawer;
import course2.kg.task3.line.Line;
import course2.kg.task3.line.drawer.LineDrawer;
import course2.kg.task3.pixel_drawer.BufferedImagePixelDrawer;
import course2.kg.task3.pixel_drawer.PixelDrawer;
import course2.kg.task3.point.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    public DrawPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    private ScreenConverter sc = new ScreenConverter(-2, 2, 4, 4, 800, 600);

    private Line axisX = new Line(-1, 0, 1, 0);
    private Line axisY = new Line(0, -1, 0, 1);

    private List<Line> allLines = new ArrayList<Line>();
    private List<CurvedLine> allCurvedLines = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        sc.sethS(getHeight());
        sc.setwS(getWidth());
        //sc.sethR(getHeight() * sc.getwR() / getWidth());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        DDALineDrawer ld = new DDALineDrawer(pd);
        CurvedLineDrawer bcld = new BezierCurvedLineDrawer(pd);
        //HermiteCurvedLineDrawer hcld = new HermiteCurvedLineDrawer(pd);
        //CurvedLine cl = new CurvedLine(new ArrayList<>(Arrays.asList(new BasicRealPoint(0, 0), new SecondaryRealPoint(1, 1), new SecondaryRealPoint(2, 0), new BasicRealPoint(2, -1), new SecondaryRealPoint(3, 0), new SecondaryRealPoint(4, -1), new BasicRealPoint(5, 2))));
        CurvedLine cl = new CurvedLine(new ArrayList<>(Arrays.asList(new RealPoint(-1, 0), new SecondaryRealPoint(-1, 1), new RealPoint(0, 1), new SecondaryRealPoint(1, 1), new RealPoint(1, 0), new SecondaryRealPoint(1, -1), new RealPoint(0, -1), new SecondaryRealPoint(-1, -1), new RealPoint(-1, 0))));
        drawAll(ld);
        //drawCurve(pd);
        drawAll(bcld, ld);
        //drawCurve(cl, sc, bcld);
        g.drawImage(bi, 0, 0, null);
    }

    private void drawAll(CurvedLineDrawer cld, LineDrawer ld) {
        List<CurvedLine> error = new ArrayList<>();
        for (CurvedLine l : allCurvedLines) {
            if (l.getAllPoints().get(0) instanceof SecondaryRealPoint || l.getAllPoints().get(l.getAllPoints().size() - 1) instanceof SecondaryRealPoint) {
                //allCurvedLines.;
                error.add(l);
                continue;
            }
            for (RealPoint p : l.getAllPoints()) {
                drawMarker(p, cld);
            }
            drawCurve(l, sc, cld);
        }
        allCurvedLines.removeAll(error);
    }

    private void drawCurve(CurvedLine l, ScreenConverter sc, CurvedLineDrawer cld) {
        if (l != null) {
            List<ScreenPoint> screenPointList = new ArrayList<>();
            for (int i = 0; i < l.getAllPoints().size(); i++) {
                ScreenPoint sp = sc.r2s(l.getAllPoints().get(i));
                screenPointList.add(sp);
            }
            cld.draw(screenPointList);
        }
    }

    private void drawAll(LineDrawer ld) {
        drawLine(ld, axisX);
        drawLine(ld, axisY);
        for (Line l : allLines)
            drawLine(ld, l);
        if (newLine != null)
            drawLine(ld, newLine);
    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }

    //RealPoint ptr = null;

    private void change(ScreenPoint point) {
        RealPoint p = sc.s2r(point);
        double r = sc.gethR() / sc.gethS() * 4;
        outer:
        for (CurvedLine l : allCurvedLines) {
            for (RealPoint curr : l.getAllPoints()) {
                double sx = Math.pow(p.getX() - curr.getX(), 2);
                double sy = Math.pow(p.getY() - curr.getY(), 2);
                if (Math.sqrt(sx + sy) < r) {
                    if (curr instanceof SecondaryRealPoint) p = new SecondaryRealPoint(p);
                    cIndex = l.getAllPoints().indexOf(curr);
                    changedCurve = l;
                    break outer;
                }
            }
            /*if (index >= 0) {
                l.getAllPoints().set(index, p);
            }*/
        }
    }

    public void drawMarker(RealPoint p, CurvedLineDrawer cld) {
        double a = sc.gethR() / sc.gethS() * 4;
        double b = a * sc.gethS() / sc.getwS() * 2/*sc.getwR() / sc.getwS() * 4*/;
        ScreenPoint p1 = sc.r2s(new RealPoint(p.getX() - a, p.getY()));
        ScreenPoint p2 = sc.r2s(new SecondaryRealPoint(p.getX() - a, p.getY() - b));
        ScreenPoint p3 = sc.r2s(new RealPoint(p.getX(), p.getY() - b));
        ScreenPoint p4 = sc.r2s(new SecondaryRealPoint(p.getX() + a, p.getY() - b));
        ScreenPoint p5 = sc.r2s(new RealPoint(p.getX() + a, p.getY()));
        ScreenPoint p6 = sc.r2s(new SecondaryRealPoint(p.getX() + a, p.getY() + b));
        ScreenPoint p7 = sc.r2s(new RealPoint(p.getX(), p.getY() + b));
        ScreenPoint p8 = sc.r2s(new SecondaryRealPoint(p.getX() - a, p.getY() + b));
        List<ScreenPoint> pts = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, new ScreenPoint(p1)));
        cld.draw(pts);
    }

    private ScreenPoint lastPosition = null;

    private CurvedLine curve = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            if (curve != null) allCurvedLines.add(curve);
            curve = null;
        } else {
            RealPoint p = null/* = sc.s2r(new ScreenPoint(e.getX(), e.getY()))*/;
            if (e.getButton() == MouseEvent.BUTTON3) {
                p = sc.s2r(new SecondaryScreenPoint(e.getX(), e.getY()));
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                p = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
            }
            if (curve == null) curve = new CurvedLine(new ArrayList<>());
            if (p != null) curve.getAllPoints().add(p);
        }
        repaint();
    }

    private Line newLine = null;

    private CurvedLine changedCurve = null;
    private int cIndex = -1;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = new ScreenPoint(e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            /*newLine = new Line(
                    sc.s2r(new ScreenPoint(e.getX(), e.getY())),
                    sc.s2r(new ScreenPoint(e.getX(), e.getY())));*/
            change(new ScreenPoint(e.getX(), e.getY()));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = null;
        } else if (e.getButton() == MouseEvent.BUTTON1) {
           /* allLines.add(newLine);
            newLine = null;*/
            changedCurve = null;
            cIndex = -1;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint newPosition = new ScreenPoint(e.getX(), e.getY());
        if (lastPosition != null) {
            ScreenPoint screenDelta = new ScreenPoint(newPosition.getX() - lastPosition.getX(), newPosition.getY() - lastPosition.getY());
            RealPoint delta = sc.s2r(screenDelta);
            double vectorX = delta.getX() - sc.getxR();
            double vectorY = delta.getY() - sc.getyR();
            sc.setxR(sc.getxR() - vectorX);
            sc.setyR(sc.getyR() - vectorY);
            lastPosition = newPosition;
        }
        if (newLine != null) {
            newLine.setP2(sc.s2r(newPosition));
        }
        if (changedCurve != null && cIndex >= 0) {
            ScreenPoint p = changedCurve.getAllPoints().get(cIndex) instanceof SecondaryRealPoint ? new SecondaryScreenPoint(e.getX(), e.getY()) : new ScreenPoint(e.getX(), e.getY());
            changedCurve.getAllPoints().set(cIndex, sc.s2r(p));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double step = clicks > 0 ? 1.1 : 0.9;
        for (int i = Math.abs(clicks);/* (clicks < 0 && sc.gethR() > 2 || clicks > 0 && sc.gethR() < 8) && */i > 0; i--) {
            scale *= step;
        }
        /*RealPoint p = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
        sc.setxR(p.getX() - sc.getwR());
        sc.setyR(p.getY() - sc.gethR());*/
        double oldWR = sc.getwR();
        double oldHR = sc.gethR();
        sc.setwR(scale * oldWR);
        sc.sethR(scale * oldHR);
        //sc.setxR(sc.getxR() + (oldWR - sc.getwR()) / 2);
        //sc.setyR(sc.getyR() + (oldHR - sc.gethR()) / 2);
        repaint();
    }

    private void drawCurve(PixelDrawer pd) {
        Random rnd = new Random();
        CurvedLineDrawer bcld = new BezierCurvedLineDrawer(pd);
        List<RealPoint> l = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i == 0 || (i + 1) % 4 == 0) {
                RealPoint p = new RealPoint(rnd.nextInt(5), rnd.nextInt(5));
                l.add(p);
            } else {
                RealPoint p = new SecondaryRealPoint(rnd.nextInt(5), rnd.nextInt(5));
                l.add(p);
            }
        }
        CurvedLine cl = new CurvedLine(l);
        drawCurve(cl, sc, bcld);
    }
}

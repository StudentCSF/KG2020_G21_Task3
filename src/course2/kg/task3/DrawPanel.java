package course2.kg.task3;

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

    @Override
    public void paint(Graphics g) {
        sc.sethS(getHeight());
        sc.setwS(getWidth());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        DDALineDrawer ld = new DDALineDrawer(pd);
        CurvedLineDrawer bcld = new BezierCurvedLineDrawer(pd);
        //HermiteCurvedLineDrawer hcld = new HermiteCurvedLineDrawer(pd);
        //Line l1 = new Line(0, 0, 4, 6);
        //Line l2 = new Line(4, 7, 2, -9);
        CurvedLine cl = new CurvedLine(new ArrayList<>(Arrays.asList(new BasicRealPoint(0, 0), new SecondaryRealPoint(1, 1), new SecondaryRealPoint(2, 0), new BasicRealPoint(2, -1), new SecondaryRealPoint(3, 0), new SecondaryRealPoint(4, -1), new BasicRealPoint(5, 2))));
        //ld.drawBezierCurvedLine(new ScreenPoint(0, 0), new ScreenPoint(1, 1), new ScreenPoint(1, 2), new ScreenPoint(2, -2));
        //bcld.drawLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //bcld.drawLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //hcld.drawLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //drawAll(ld);
        //drawCurve(pd);
        drawCurve(cl, sc, bcld);
        g.drawImage(bi, 0, 0, null);
    }

    private void drawCurve(PixelDrawer pd) {
        Random rnd = new Random();
        CurvedLineDrawer bcld = new BezierCurvedLineDrawer(pd);
        List<RealPoint> l = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i == 0 || (i + 1) %  4 == 0) {
                BasicRealPoint p = new BasicRealPoint(rnd.nextInt(10), rnd.nextInt(10));
                l.add(p);
            } else {
                SecondaryRealPoint p = new SecondaryRealPoint(rnd.nextInt(10), rnd.nextInt(10));
                l.add(p);
            }
        }
        CurvedLine cl = new CurvedLine(l);
        drawCurve(cl, sc, bcld);
    }

    private void drawCurve(CurvedLine l, ScreenConverter sc, CurvedLineDrawer cld) {
        List<ScreenPoint> screenPointList = new ArrayList<>();
        for (int i = 0; i < l.getPoints().size(); i++) {
            boolean isBasic = l.getPoints().get(i) instanceof BasicRealPoint;
            ScreenPoint sp = sc.r2s(l.getPoints().get(i));
            if (isBasic) {
                sp = new BasicScreenPoint(sp.getX(), sp.getY());
            } else {
                sp = new SecondaryScreenPoint(sp.getX(), sp.getY());;
            }
            screenPointList.add(sp);
        }
        cld.drawCurvedLine(screenPointList);
    }

    private void drawAll(LineDrawer ld) {
        drawLine(ld, axisX);
        drawLine(ld, axisY);
        //cld.drawLine(new ScreenPoint(110, 110), new ScreenPoint(50, 50), new ScreenPoint(50, 50), new ScreenPoint(310, 110));
        for (Line l : allLines)
            drawLine(ld, l);
        if (newLine != null)
            drawLine(ld, newLine);
    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }

    private ScreenPoint lastPosition = null;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private Line newLine = null;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = new ScreenPoint(e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            newLine = new Line(
                    sc.s2r(new ScreenPoint(e.getX(), e.getY())),
                    sc.s2r(new ScreenPoint(e.getX(), e.getY())));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = null;
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            allLines.add(newLine);
            newLine = null;
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
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale *= step;
        }
        sc.setwR(scale * sc.getwR());
        sc.sethR(scale * sc.gethR());
        repaint();
    }
}

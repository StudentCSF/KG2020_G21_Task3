package course2.kg.task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
        LineDrawer ld = new DDALineDrawer(pd);
        //CurvedLineDrawer bcld = new BezierCurvedLineDrawer(pd);
        //CurvedLineDrawer hcld = new HermiteCurvedLineDrawer(pd);
        //Line l1 = new Line(0, 0, 4, 6);
        //Line l2 = new Line(4, 7, 2, -9);
        CurvedLine cl = new CurvedLine(new RealPoint(0, 0), new RealPoint(4, 6), new RealPoint(4, 7), new RealPoint(2, -9));
        ld.drawBezierCurvedLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //bcld.drawLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //hcld.drawLine(sc.r2s(cl.getP1()), sc.r2s(cl.getP2()), sc.r2s(cl.getP3()), sc.r2s(cl.getP4()));
        //drawAll(ld, cld);
        g.drawImage(bi, 0, 0, null);
    }

    private void drawAll(LineDrawer ld, CurvedLineDrawer cld) {
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
    public void mouseClicked(MouseEvent mouseEvent) {

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
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

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
    public void mouseMoved(MouseEvent mouseEvent) {

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

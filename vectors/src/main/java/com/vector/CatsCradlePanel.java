package com.vector;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.JPanel;

// class to create the window
// cannot exist multiple inheritence
public class CatsCradlePanel extends JPanel implements ActionListener {

    private static final Color BG_COLOR = new Color(163, 90, 196);
    private static final Color FG_COLOR = new Color(180, 192, 224);

    private static final double MARGIN = -0.1;
    private static final double SPEED = 40.0;

    private static final float OUTSIDE_LINE_THICKNESS = 5;
    private static final float INSIDE_LINE_THICKNESS = 2;
    private int numberOfSides;
    private double outerStep;
    private double innerStep;
    private double outerAngle;
    private double innerAngle;
    private double angle;
    private Color[] colors;
    private Stroke insideStroke;
    private Stroke outsideStroke;

    /**
     * This sets up the cats cradle panel.
     * @param numberOfSides number of sides the polygon has
     */
    public CatsCradlePanel(int numberOfSides) {
        this.setBackground(BG_COLOR);
        this.setForeground(FG_COLOR);
        this.numberOfSides = numberOfSides;
        this.outerStep = 0.5 * Math.PI / (SPEED * numberOfSides);
        this.innerStep = 7.0 * Math.PI / (SPEED * numberOfSides);
        this.outerAngle = 0.0;
        this.innerAngle = 0.0;
        this.insideStroke = new BasicStroke(INSIDE_LINE_THICKNESS, 
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        this.outsideStroke = new BasicStroke(OUTSIDE_LINE_THICKNESS, 
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        this.colors = new Color[numberOfSides];

        Random random = new Random();
        for (int i = 0; i < numberOfSides; i++) {
            int red = 90 + random.nextInt(60);
            int green = 10 + random.nextInt(60);
            int blue = 90 + random.nextInt(60);
            // int red = Math.floor(90 + i*5);
            // int green = Math.floor(90 + i*5);
            // int blue = Math.floor(90 + i*5);
            this.colors[i] = new Color(red, green, blue);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        Vector2D[] outside = new Vector2D[this.numberOfSides];
        Vector2D[] inside = new Vector2D[this.numberOfSides];
        double goldenRatio = 2.0 / (Math.sqrt(5.0) + 1);
        for (int i = 0; i < this.numberOfSides; i++) {
            double fraction = ((double) i) / this.numberOfSides;
            this.angle = fraction * 2.0 * Math.PI;
            double x = Math.cos(angle + outerAngle);
            double y = Math.sin(angle + outerAngle);
            outside[i] = new Vector2D(x, y);

            x = Math.cos(angle + innerAngle);
            y = Math.sin(angle + innerAngle);
            inside[i] = new Vector2D(goldenRatio * x, goldenRatio * y);
        } // for


        double rotation = 0.0;

        double scaleX = (1.0 - 2.0 * MARGIN) * w / 2;
        double scaleY = (1.0 - 2.0 * MARGIN) * h / 2;
        // to put their centers at the center of the panel.
        double deltaX = w / 4;
        double deltaY = h / 4;

        g2D.setColor(FG_COLOR);
        for (int i = 0; i < outside.length; i++) {
        Vector2D u = outside[i];
        u = u.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);

        Vector2D v = outside[(i + 1) % this.numberOfSides];
        v = v.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);

        double x0 = u.getX();
        double y0 = u.getY();
        double x1 = v.getX();
        double y1 = v.getY();
        Line2D line = new Line2D.Double(x0, y0, x1, y1);
        g2D.setStroke(this.outsideStroke);
        g2D.draw(line);

        u = inside[i];
        u = u.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);

        v = inside[(i + 1) % this.numberOfSides];
        v = v.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);

        x0 = u.getX();
        y0 = u.getY();
        x1 = v.getX();
        y1 = v.getY();

        line = new Line2D.Double(x0, y0, x1, y1);
        g2D.setStroke(this.insideStroke);
        g2D.draw(line);
        } // for

        g2D.setStroke(this.outsideStroke);
        for (int i = 0; i < this.numberOfSides; i++) {
            Vector2D u = outside[i];
            u = u.rotateScaleTranslate(angle, scaleX, scaleY, deltaX, deltaY);
            double x0 = u.getX();
            double y0 = u.getY();
            for (int j = i + 1; j < this.numberOfSides; j++) {
                Vector2D v = outside[j];
                v = v.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);
                double x1 = v.getX();
                double y1 = v.getY();

                int index = Math.abs(i - j);
                g2D.setColor(this.colors[index]);

                Line2D line = new Line2D.Double(x0, y0, x1, y1);
                g2D.draw(line);
            } // for
        } // for


        g2D.setStroke(this.insideStroke);
        for (int i = 0; i < this.numberOfSides; i++) {
            Vector2D u = inside[i];
            u = u.rotateScaleTranslate(angle, scaleX, scaleY, deltaX, deltaY);
            double x0 = u.getX();
            double y0 = u.getY();
            for (int j = i + 1; j < this.numberOfSides; j++) {
                Vector2D v = inside[j];
                v = v.rotateScaleTranslate(rotation, scaleX, scaleY, deltaX, deltaY);
                double x1 = v.getX();
                double y1 = v.getY();

                int index = Math.abs(i - j);
                g2D.setColor(this.colors[index]);

                Line2D line = new Line2D.Double(x0, y0, x1, y1);
                g2D.draw(line);
            } // for
        } // for 2nd
    } // paintComponent( Graphics )

    

  @Override
    public void actionPerformed(ActionEvent e) {
        this.outerAngle += this.outerStep;
        this.innerAngle += this.innerStep;
        this.repaint();
    } // actionPerformed( ActionEvent )
}
    

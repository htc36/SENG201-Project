package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class DarkSlider extends BasicSliderUI {

    private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);


    /**
     * <<auto generated javadoc comment>>
     * @param b <<Param Desc>>
     */
    public DarkSlider(JSlider b) {
        super(b);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param g <<Param Desc>>
     */
    @Override
    public void paintFocus(Graphics g) {
    }

    /**
     * <<auto generated javadoc comment>>
     * @param g <<Param Desc>>
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(trackRect.x, trackRect.height / 2, trackRect.x + trackRect.width, trackRect.height / 2);
        g2d.setStroke(old);
    }

    protected Dimension getThumbSize() {
        return new Dimension(10, 30);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param g <<Param Desc>>
     */
    @Override
    public void paintThumb(Graphics g) {
        int x1 = thumbRect.x;
        int topY = thumbRect.y;

        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        shape.moveTo(x1, topY);
        shape.lineTo(x1 + 10, topY);
        shape.lineTo(x1 + 10, topY + 20);
        shape.lineTo(x1, topY + 20);
        shape.closePath();
        g2d.setPaint(Color.WHITE);
        g2d.fill(shape);
        g2d.draw(shape);
        g2d.setStroke(old);

    }

}

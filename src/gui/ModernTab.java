package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

// CODE IN THIS CLASS IS SLIGHTLY MODIFIED FROM 
// https://java-swing-tips.blogspot.com/2017/01/change-tab-shape-of-jtabbedpane-to.html

public class ModernTab extends BasicTabbedPaneUI {
    private final Color selectedTabColor = Color.BLACK;
    private final Color tabBackgroundColor = Color.DARK_GRAY;
    private final Color tabBorderColor = Color.GRAY;

    @Override 
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        int tabCount = tabPane.getTabCount();

        Rectangle iconRect = new Rectangle(),
                textRect = new Rectangle();
        Rectangle clipRect = g.getClipBounds();

        for (int i = runCount - 1; i >= 0; i--) {
            int start = tabRuns[i];
            int next = tabRuns[(i == runCount - 1) ? 0 : i + 1];
            int end = next != 0 ? next - 1 : tabCount - 1; //NOPMD
            for (int j = end; j >= start; j--) {
                if (j != selectedIndex && rects[j].intersects(clipRect)) {
                    paintTab(g, tabPlacement, rects, j, iconRect, textRect);
                }
            }
        }
        if (selectedIndex >= 0 && rects[selectedIndex].intersects(clipRect)) {
            paintTab(g, tabPlacement, rects, selectedIndex, iconRect, textRect);
        }

    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int textShiftOffset = isSelected ? 0 : 1;

        Rectangle clipRect = g2.getClipBounds();
        g2.setClip(clipRect);

        GeneralPath square = new GeneralPath();
        square.moveTo(x, y + h);
        square.lineTo(x, y + textShiftOffset);
        square.lineTo(x + w, y + textShiftOffset);
        square.lineTo(x + w, y + h);
        //square.closePath();

        g2.setColor(isSelected ? selectedTabColor : tabBackgroundColor);
        g2.fill(square);

        g2.setColor(tabBorderColor);
        g2.draw(square);

        g2.dispose();

    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {}

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {}


}

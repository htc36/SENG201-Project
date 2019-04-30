package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.GameEngine;
import game.InsufficientFundException;

public class ShoppingCart {

    private JPanel panel;
    private JLabel priceLabel;
    private ArrayList<JCheckBox> checkboxes;
    private int x;
    private int y;
    private int checkboxSpacing = 3;
    private int checkboxHeight = 23;
    private int checkboxWidth = 120;
    private GameEngine engine;

    /**
     * Constructor for shopping cart
     * @param panel Panel where to show the cart
     * @param x X axis of shopping cart location
     * @param y Y axis of shopping cart location
     * @param engine Game engine
     * @param priceLabel Label showing the prices
     */
    public ShoppingCart(JPanel panel, int x, int y, GameEngine engine, JLabel priceLabel) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.engine = engine;
        this.priceLabel = priceLabel;
        checkboxes = new ArrayList<>();
    }

    /**
     * Adds selected item to the shopping cart
     * @param itemQuery the item
     */
    public void addItemToShoppingCart(String itemQuery) {
        int amount = Integer.valueOf(itemQuery.split("x")[0]);
        if (amount == 0)
            return;

        JCheckBox newItem = new JCheckBox();
        engine.addItemToShoppingBag(itemQuery);
        newItem.setText(itemQuery);
        newItem.setVisible(true);
        newItem.setSelected(true);
        newItem.setForeground(Color.WHITE);
        newItem.setBackground(Color.BLACK);
        newItem.addItemListener(new ItemListener() {
            /**
             * Removes item from the shopping cart when the box is unchecked
             * @param e Item Event
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                removeItemFromShoppingCart(newItem);
            }
        });
        
        // supports 2 columns of shopping items
        if (checkboxes.size() <= 7) {
            newItem.setBounds(
                    x, y + (checkboxes.size() % 8) * (checkboxHeight + checkboxSpacing), 
                    checkboxWidth, checkboxHeight);
        } else {
            newItem.setBounds(
                    x + checkboxWidth + checkboxSpacing, y + (checkboxes.size() % 8) * (checkboxHeight + checkboxSpacing), 
                    checkboxWidth, checkboxHeight);
        }

        panel.add(newItem); // show it to the user on the panel
        panel.revalidate(); // refresh the panel
        panel.repaint();    // refresh the panel

        checkboxes.add(newItem); // add to list to keep track

        priceLabel.setText(String.valueOf(engine.getShoppingBagTotalPrice()));
    }

    /**
     * Removes item from the shopping cart
     * @param item The checkbox clicked
     */
    public void removeItemFromShoppingCart(JCheckBox item) {
        String itemQuery = item.getText();
        int amount = Integer.valueOf(itemQuery.split("x")[0]);
        String itemName = itemQuery.split("x")[1];

        for (int i = 0; i < amount; i++) {
            engine.removeItemFromShoppingBag(itemName);
        }

        priceLabel.setText(String.valueOf(engine.getShoppingBagTotalPrice()));

        panel.remove(item);
        checkboxes.remove(item);
        redrawShoppingCart();
        panel.revalidate(); // refresh the panel
        panel.repaint();    // refresh the panel
    }

    /**
     * Updates the shopping cart according to current shopping cart 
     * display
     */
    public void redrawShoppingCart() {
        int counter = 0;
        for (JCheckBox item : checkboxes) {
            if (counter <= 7) {
                item.setBounds(
                        x, y + (counter % 8) * (checkboxHeight + checkboxSpacing), 
                        checkboxWidth, checkboxHeight);
            } else {
                item.setBounds(
                        x + checkboxWidth + checkboxSpacing, 
                        y + (counter % 8) * (checkboxHeight + checkboxSpacing), 
                        checkboxWidth, checkboxHeight);
            }
            counter++;
        }
    }

    /**
     * Purchases item
     * Adds item to the inventory, also deducts cost from money
     */
    public void purchaseItems() {
        try {
            engine.purchaseItems(engine.getShoppingBag());
        } catch (InsufficientFundException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            return;
        }
        for (JCheckBox c : checkboxes) {
            panel.remove(c);
        }
        checkboxes.clear();
        priceLabel.setText(String.valueOf(engine.getShoppingBagTotalPrice()));
        panel.revalidate(); // refresh the panel
        panel.repaint();    // refresh the panel
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getTotalPrice() {
        return engine.getShoppingBagTotalPrice();
    }

}

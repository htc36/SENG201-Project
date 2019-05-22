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

/**
 * Defines the shopping cart of the player, keeps track of items and prices
 * 
 */
public class ShoppingCart {

    /**
     * The panel shopping cart will draw on
     */
    private JPanel panel;
    
    /**
     * Label showing the total price of the cart
     */
    private JLabel priceLabel;
    
    /**
     * List of checkboxes showing all the items in 
     * the shopping cart
     */
    private ArrayList<JCheckBox> checkboxes;
    
    /**
     * X axis of the shopping cart location
     */
    private int x;
    
    /**
     * Y axis of the shopping cart location
     */
    private int y;
    
    /**
     * Spacing between each item in the shopping cart
     */
    private final int checkboxSpacing = 3;
    
    /**
     * Height of each item in the shopping cart
     */
    private final int checkboxHeight = 23;
    
    /**
     * Width of each item in the shopping cart
     */
    private final int checkboxWidth = 120;

    /**
     * Engine that runs and keeps track of the game state
     */
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
     * Returns the total price of player's shopping cart
     * @return int Total price of the shopping cart
     */
    public int getTotalPrice() {
        return engine.getShoppingBagTotalPrice();
    }

}

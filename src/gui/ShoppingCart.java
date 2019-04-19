package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ShoppingCart {
	
	private JPanel panel;
	private ArrayList<JCheckBox> checkboxes;
	private int x;
	private int y;
	private int checkboxSpacing = 3;
	private int checkboxHeight = 23;
	private int checkboxWidth = 120;

	public ShoppingCart(JPanel panel, int x, int y) {
		this.panel = panel;
		this.x = x;
		this.y = y;
		checkboxes = new ArrayList<>();
	}
	
	public void addItemToShoppingCart(String itemQuery) {
		JCheckBox newItem = new JCheckBox();
		newItem.setText(itemQuery);
		newItem.setVisible(true);
		newItem.setSelected(true);
        newItem.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				removeItemFromShoppingCart(newItem);
				
			}
        });
        newItem.setBounds(x, y + checkboxes.size() * (checkboxHeight + checkboxSpacing), checkboxWidth, checkboxHeight);

        panel.add(newItem); // show it to the user on the panel
        panel.revalidate(); // refresh the panel
        panel.repaint(); // refresh the panel

		checkboxes.add(newItem); // add to list to keep track
	}
	
	public void removeItemFromShoppingCart(JCheckBox item) {
		panel.remove(item);
		checkboxes.remove(item);
		redrawShoppingCart();
        panel.revalidate(); // refresh the panel
        panel.repaint(); // refresh the panel
	}
	
	public void redrawShoppingCart() {
		int counter = 0;
		for (JCheckBox item : checkboxes) {
			item.setBounds(x, y + counter * (checkboxHeight + checkboxSpacing), checkboxWidth, checkboxHeight);
			counter++;
		}
	}
	
	public ArrayList<String> getAllItemQueries() {
		ArrayList<String> queries = new ArrayList<>();
		for (JCheckBox query : checkboxes) {
			queries.add(query.getText());
		}
		
		return queries;
	}

}

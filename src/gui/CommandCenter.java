package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import game.GameEngine;
import unit.InsufficientActionException;

import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;


import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CommandCenter {

	private JFrame frmCommandCenter;
	private GameEngine engine;
	private GameGUI game;
	private JLabel currShipPieces;
	private JLabel currDay;
	private JProgressBar spaceshipHealth;

	private JLabel crewNames;
	private JLabel crewTypes;
	private JLabel crewHealths;
	private JLabel crewLucks;
	private JLabel crewPlagues;
	private JLabel crewHungers;
	private JLabel crewFatigues;
	private JLabel crewActions;

	private JLabel itemNames;
	private JLabel itemTypes;
	private JLabel itemHealings;
	private JLabel itemPrices;
	private JLabel itemCures;
	private JLabel itemFills;

	private JLabel selectedItem;
	private ShoppingCart cart;
	private JLabel cartPrice;

	private JLabel currMoney;
	private JLabel currInventory;

	private ArrayList<Integer> selectedCrews;
	private JToggleButton memberOne;
	private JToggleButton memberTwo;
	private JToggleButton memberThree;
	private JToggleButton memberFour;

	private int totalCrewMembers;
	private JLabel infoBox;


	/**
	 * Create the application.
	 */
	public CommandCenter(GameEngine engine, GameGUI game) {
		this.engine = engine;
		this.game = game;
		selectedCrews = new ArrayList<>();
		totalCrewMembers = engine.getCrewMemberStatus().size();
		initialize();
		frmCommandCenter.setVisible(true);
	}

	public void startDay() {
		int randomEvent = engine.getRandomEvent();
		switch (randomEvent) {
		case 1:
			System.out.println("Oh no those pesky alien pirates invaded the ship and raided our inventory");
			break;
		case 2:
			System.out.println("Houston we have a problem");
			System.out.println("Some crew member(s) have been infected with Space Plague");
			break;
		}

		engine.updateCrewMemberStatus();
		engine.getDeadCrewMembers();
	}

	private void refreshPage() {
		currShipPieces.setText(String.valueOf(engine.getFoundShipPieces()));
		currDay.setText(String.valueOf(engine.getCurrDay()));
	}

	private void refreshSpaceshipPage() {
		int shipHealth = engine.getSpaceshipHealth();
		spaceshipHealth.setValue(shipHealth);
	}

	private void refreshInventory() {
		ArrayList<ArrayList<String>> crewConsumables = engine.getCrewConsumables();
		String template = "<html>";
		for (ArrayList<String> consumable : crewConsumables) {
			template += consumable.get(5) + " x "; // name
			template += consumable.get(0); // name
			template += "<br>";
		}
		template += "</html>";

		currInventory.setText(template);
	}

	private void getItemDescription(String itemName) {
		String itemNames = "";
		String itemTypes = "";
		String itemHealings = "";
		String itemPrices = "";
		String itemCures = "";
		String itemFills = "";

		ArrayList<String> item = null;
		for (ArrayList<String> i : engine.getOutpostSaleProducts()) {
			if (i.get(0).equals(itemName)) {
				item = i;
				break;
			}
		}

		String name = item.get(0);
		itemNames += name;

		String type = item.get(3);
		itemTypes += type;

		String healing = item.get(2);
		itemHealings += healing;

		String price = item.get(1);
		itemPrices += price;

		String cures = "";
		String fills = "";
		if (type.equals("[Food]")) {
			fills = item.get(4);
			itemFills += fills;
			itemCures += "F";
		} else {
			cures = item.get(4);
			itemCures += cures;
			itemFills += "F";
		}

		this.itemNames.setText(itemNames);
		this.itemTypes.setText(itemTypes);
		this.itemHealings.setText(itemHealings);
		this.itemPrices.setText(itemPrices);
		this.itemCures.setText(itemCures);
		this.itemFills.setText(itemFills);
	}

	private void showItemDetails(String item) {
		String imageName = "/img/" + item.toLowerCase() + ".png";
		selectedItem.setIcon(new ImageIcon(CommandCenter.class.getResource(imageName)));
		getItemDescription(item);
	}


	private void refreshCrewStatusPage() {
		String crewNames = "<html>";
		String crewTypes = "<html>";
		String crewHealth = "<html>";
		String crewLuck = "<html>";
		String crewPlagued = "<html>";
		String crewHunger = "<html>";
		String crewFatigue = "<html>";
		String crewActions = "<html>";

		int numNewlines = 4;

		for (ArrayList<String> member : engine.getCrewMemberStatus()) {
			String name = member.get(0);
			crewNames += name;
			for (int i = 0; i < numNewlines; i++) {
				crewNames += "<br>";
			}

			String type = member.get(7);
			crewTypes += type;
			for (int i = 0; i < numNewlines; i++) {
				crewTypes += "<br>";
			}

			String health = member.get(1);
			crewHealth += health;
			for (int i = 0; i < numNewlines; i++) {
				crewHealth += "<br>";
			}

			String luck = member.get(2);
			crewLuck += luck;
			for (int i = 0; i < numNewlines; i++) {
				crewLuck += "<br>";
			}

			String plagued = member.get(3);
			crewPlagued += plagued;
			for (int i = 0; i < numNewlines; i++) {
				crewPlagued += "<br>";
			}

			String hunger = member.get(4);
			crewHunger += hunger;
			for (int i = 0; i < numNewlines; i++) {
				crewHunger += "<br>";
			}

			String fatigue = member.get(5);
			crewFatigue += fatigue;
			for (int i = 0; i < numNewlines; i++) {
				crewFatigue += "<br>";
			}

			String actions = member.get(6);
			crewActions += actions;
			for (int i = 0; i < numNewlines; i++) {
				crewActions += "<br>";
			}
		}
		crewNames += "</html>";
		crewTypes += "</html>";
		crewHealth += "</html>";
		crewLuck += "</html>";
		crewPlagued += "</html>";
		crewHunger += "</html>";
		crewFatigue += "</html>";
		crewActions += "</html>";

		this.crewNames.setText(crewNames);
		this.crewTypes.setText(crewTypes);
		this.crewHealths.setText(crewHealth);
		this.crewLucks.setText(crewLuck);
		this.crewPlagues.setText(crewPlagued);
		this.crewHungers.setText(crewHunger);
		this.crewFatigues.setText(crewFatigue);
		this.crewActions.setText(crewActions);
	}

	private void refreshSelectedCrews() {
		for (int i = 0; i < totalCrewMembers; i++) {
			boolean selected = false;
			if (selectedCrews.contains(i))
				selected = true;
			switch(i) {
			case 0:
				memberOne.setSelected(selected);
				break;
			case 1:
				memberTwo.setSelected(selected);
				break;
			case 2:
				memberThree.setSelected(selected);
				break;
			case 3:
				memberFour.setSelected(selected);
				break;
			}
		}
	}

	private void addCrewToSelection(int index) {
		if (selectedCrews.contains(index)) {
			selectedCrews.remove(selectedCrews.indexOf(index));
			if (selectedCrews.size() == 1)
				engine.selectCrewMember(selectedCrews.get(0));
			else
				engine.selectedCrewCancel();
			refreshSelectedCrews();
			return;
		}

		if (selectedCrews.size() > 1) {
			selectedCrews.remove(0);
		}
		selectedCrews.add(index);

		if (selectedCrews.size() > 1) {
			engine.selectCrewMember(selectedCrews.get(0));
			engine.setCopilot(selectedCrews.get(1));
		} else {
			engine.selectCrewMember(selectedCrews.get(0));
		}
		refreshSelectedCrews();
	}

	private void getInputCrewConsume(ArrayList<ArrayList<String>> consumables) {
		JDialog consWindow = new JDialog();
		consWindow.setResizable(false);
		consWindow.getContentPane().setBackground(Color.BLACK);
		consWindow.setTitle("Select Consumables");
		consWindow.setBounds(100, 100, 340, 380);
		consWindow.getContentPane().setLayout(null);
		consWindow.setVisible(true);

		int itemsSpacing = 10;
		int buttonSize = 100;
		int items_x = 10;
		int items_y = 10;

		for (ArrayList<String> item : consumables) {
			String itemName = item.get(0).toLowerCase();
			System.out.println(itemName);
			JButton itemButton = new JButton(itemName);
			itemButton.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/" + itemName + ".png")));
			itemButton.setBackground(Color.DARK_GRAY);
			itemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						engine.selectedCrewUseItem(consumables.indexOf(item));
						String selectedCrewName = engine.selectedCrewName();
						String template = "";
						template += selectedCrewName + " consumed a " + itemName + ". ";
						template += "Their health has been increased by " + item.get(2) + ". ";
						JOptionPane.showMessageDialog(new JFrame(), template);
					} catch (InsufficientActionException err) {
						JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
						return;
					}
					consWindow.setVisible(false);
					consWindow.dispose();
				}
			});

			int itemRow = consumables.indexOf(item) / 3;
			int itemCol = consumables.indexOf(item) % 3;
			itemButton.setBounds(items_x + itemCol * (buttonSize + itemsSpacing), 
					items_y + itemRow * (buttonSize + itemsSpacing), 
					buttonSize, buttonSize);
			itemButton.setText("");
			itemButton.setBorder(null);

			consWindow.getContentPane().add(itemButton);
		}
	}

	/**
	 * <<auto generated javadoc comment>>
	 */
	public void closeWindow() {
		frmCommandCenter.dispose();
	}

	/**
	 * <<auto generated javadoc comment>>
	 */
	public void finishedWindow() {
		game.closeCommandCenter(this);
	}

	private void initialize() {
		UIManager.put("TabbedPane.unselectedForeground", Color.RED);
		UIManager.put("TabbedPane.selectedBackground", new Color(100, 0, 0));
		frmCommandCenter = new JFrame();
		frmCommandCenter.getContentPane().setBackground(Color.BLACK);
		frmCommandCenter.setResizable(false);
		frmCommandCenter.setTitle("Command Center");
		frmCommandCenter.setBounds(100, 100, 1024, 768);
		frmCommandCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCommandCenter.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
//		tabbedPane.setBackground(new Color(51, 51, 51));
//		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBounds(12, 0, 1000, 648);
		frmCommandCenter.getContentPane().add(tabbedPane);

		JPanel crewStatus = new JPanel();
		crewStatus.setForeground(new Color(204, 204, 204));
		crewStatus.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Crew Status", null, crewStatus, null);
		crewStatus.setLayout(null);

		tabbedPane.addChangeListener(new ChangeListener() {

			/**
			 * Initialize the contents of the frame.
			 */
			@Override
			public void stateChanged(ChangeEvent arg0) {
				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					refreshCrewStatusPage();
					break;
				case 1:
					break;
				case 2:
					refreshSpaceshipPage();
					break;
				case 3:
					refreshInventory();
					break;
				}
			}
		});

		int x = 20;

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(102, 102, 102));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblName.setBounds(86, 29, 82, 62);
		crewStatus.add(lblName);

		JLabel lblType = new JLabel("Type");
		lblType.setForeground(new Color(102, 102, 102));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblType.setBounds(275, 29, 82, 62);
		crewStatus.add(lblType);

		JLabel lblHealth = new JLabel("Health");
		lblHealth.setForeground(new Color(102, 102, 102));
		lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealth.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHealth.setBounds(379, 29, 82, 62);
		crewStatus.add(lblHealth);

		JLabel lblLuck = new JLabel("Luck");
		lblLuck.setForeground(new Color(102, 102, 102));
		lblLuck.setHorizontalAlignment(SwingConstants.CENTER);
		lblLuck.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblLuck.setBounds(473, 29, 82, 62);
		crewStatus.add(lblLuck);

		JLabel lblPlagued = new JLabel("Plagued");
		lblPlagued.setForeground(new Color(102, 102, 102));
		lblPlagued.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlagued.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblPlagued.setBounds(567, 29, 109, 62);
		crewStatus.add(lblPlagued);

		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setForeground(new Color(102, 102, 102));
		lblHunger.setHorizontalAlignment(SwingConstants.CENTER);
		lblHunger.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHunger.setBounds(676, 29, 82, 62);
		crewStatus.add(lblHunger);

		JLabel lblFatigue = new JLabel("Fatigue");
		lblFatigue.setForeground(new Color(102, 102, 102));
		lblFatigue.setHorizontalAlignment(SwingConstants.CENTER);
		lblFatigue.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblFatigue.setBounds(780, 29, 94, 62);
		crewStatus.add(lblFatigue);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setForeground(new Color(102, 102, 102));
		lblActions.setHorizontalAlignment(SwingConstants.CENTER);
		lblActions.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblActions.setBounds(886, 29, 82, 62);
		crewStatus.add(lblActions);

		JLabel label = new JLabel("Name");
		label.setForeground(new Color(204, 204, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Samanata", Font.PLAIN, 20));

		crewNames = label;
		label.setBounds(12, 112, 238, 471);
		crewStatus.add(label);

		JLabel label_1 = new JLabel("Type");
		label_1.setForeground(new Color(204, 204, 204));
		label_1.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		crewTypes = label_1;
		label_1.setBounds(258, 112, 109, 471);
		crewStatus.add(label_1);

		JLabel label_2 = new JLabel("Type");
		label_2.setForeground(new Color(204, 204, 204));
		label_2.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		crewHealths = label_2;
		label_2.setBounds(389, 112, 82, 471);
		crewStatus.add(label_2);

		JLabel label_3 = new JLabel("Type");
		label_3.setForeground(new Color(204, 204, 204));
		label_3.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		crewLucks = label_3;
		label_3.setBounds(483, 112, 82, 471);
		crewStatus.add(label_3);

		JLabel label_4 = new JLabel("Type");
		label_4.setForeground(new Color(204, 204, 204));
		label_4.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		crewPlagues = label_4;
		label_4.setBounds(577, 112, 82, 471);
		crewStatus.add(label_4);

		JLabel label_5 = new JLabel("Type");
		label_5.setForeground(new Color(204, 204, 204));
		label_5.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		crewHungers = label_5;
		label_5.setBounds(676, 112, 82, 471);
		crewStatus.add(label_5);

		JLabel label_6 = new JLabel("Type");
		label_6.setForeground(new Color(204, 204, 204));
		label_6.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		crewFatigues = label_6;
		label_6.setBounds(780, 112, 82, 471);
		crewStatus.add(label_6);

		JLabel label_7 = new JLabel("Type");
		label_7.setForeground(new Color(204, 204, 204));
		label_7.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		crewActions = label_7;
		label_7.setBounds(886, 112, 82, 471);
		crewStatus.add(label_7);

		JPanel commitActions = new JPanel();
		commitActions.setBorder(null);
		commitActions.setBackground(Color.BLACK);
		tabbedPane.addTab("Commit Actions", null, commitActions, null);
		commitActions.setLayout(null);

		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START

		JButton memberUseConsumable = new JButton("Use consumables");
		memberUseConsumable.setFont(new Font("Ubuntu Mono", Font.ITALIC, 16));
		memberUseConsumable.setForeground(Color.WHITE);
		memberUseConsumable.setBorderPainted(false);
		memberUseConsumable.setBackground(new Color(20, 20, 20));
		memberUseConsumable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBox.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/creweat.jpg")));
			}
		});
		memberUseConsumable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				ArrayList<ArrayList<String>> userItems = engine.getCrewConsumables();
				getInputCrewConsume(userItems);
			}
		});
		memberUseConsumable.setBounds(12, 134, 210, 179);
		commitActions.add(memberUseConsumable);

		JButton memberSleep = new JButton("Sleep");
		memberSleep.setFont(new Font("Ubuntu Mono", Font.ITALIC, 16));
		memberSleep.setForeground(Color.WHITE);
		memberSleep.setBackground(new Color(20, 20, 20));
		memberSleep.setBorderPainted(false);
		memberSleep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBox.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/crewsleep.jpg")));
			}
		});
		memberSleep.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					engine.selectedCrewSleep();
					JOptionPane.showMessageDialog(new JFrame(), "Slept");
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberSleep.setBounds(232, 134, 210, 179);
		commitActions.add(memberSleep);

		JButton memberRepair = new JButton("Repair shield");
		memberRepair.setFont(new Font("Ubuntu Mono", Font.ITALIC, 16));
		memberRepair.setForeground(Color.WHITE);
		memberRepair.setBackground(new Color(20, 20, 20));
		memberRepair.setBorderPainted(false);
		memberRepair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBox.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/repairship.png")));
			}
		});
		memberRepair.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					engine.selectedCrewRepairShield();
					JOptionPane.showMessageDialog(new JFrame(), "Repaired");
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberRepair.setBounds(12, 320, 210, 179);
		commitActions.add(memberRepair);

		JButton memberSearch = new JButton("Search the planet");
		memberSearch.setFont(new Font("Ubuntu Mono", Font.ITALIC, 16));
		memberSearch.setForeground(Color.WHITE);
		memberSearch.setBackground(new Color(20, 20, 20));
		memberSearch.setBorderPainted(false);
		memberSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBox.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/crewexplore.jpg")));
			}
		});
		memberSearch.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				boolean foundShipPiece = false;
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					foundShipPiece = engine.selectedCrewSearchPlanet();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
					return;
				}

				if (foundShipPiece) {
					JOptionPane.showMessageDialog(new JFrame(), "Found ship piece");
					engine.incrementFoundShipPieces();
					engine.planetExtractShipPieces();
					if (engine.hasGameEnded()) {
						// TODO: close command center, show final screen window	
					}
				} else {
					if (engine.unlucky(20)) {
					JOptionPane.showMessageDialog(new JFrame(), "Found nothing");
						// found nothing
					} else if (engine.unlucky(50)) { // or if found something, 50% chance it's item
					JOptionPane.showMessageDialog(new JFrame(), "Found random item");
						engine.crewGetRandomItem();
					} else { // 50% chance it's money
					JOptionPane.showMessageDialog(new JFrame(), "Found money");
						engine.crewAddMoney();
					}
				}

				refreshPage();
			}
		});
		memberSearch.setBounds(232, 320, 210, 179);
		commitActions.add(memberSearch);

		JButton memberPilot = new JButton("PUNCH THE BOOSTERS");
		memberPilot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBox.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/crewlaunch.png")));
			}
		});
		memberPilot.setFont(new Font("Ubuntu Mono", Font.ITALIC, 16));
		memberPilot.setForeground(Color.WHITE);
		memberPilot.setBackground(new Color(20, 20, 20));
		memberPilot.setBorderPainted(false);
		memberPilot.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 2) {
					JOptionPane.showMessageDialog(new JFrame(), "You need 2 crew members for this action");
					return;
				}
				try {
					engine.selectedCrewPilotSpaceship();
					if (engine.isHitAsteroid()) {
						engine.asteroidCausingDamage();
						JOptionPane.showMessageDialog(new JFrame(), "Crashed asteroid");
					} else 
						JOptionPane.showMessageDialog(new JFrame(), "Arrived safely");
					if (engine.planetHasShipPieces())
						JOptionPane.showMessageDialog(new JFrame(), "Planet has ship piece");
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberPilot.setBounds(12, 506, 430, 103);
		commitActions.add(memberPilot);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(460, 21, 523, 588);
		commitActions.add(lblNewLabel_2);
		infoBox = lblNewLabel_2;

		int memberButtonsSize = 100;
		JToggleButton memberOne = new JToggleButton("");
		memberOne.setBorder(null);
		String crewType = engine.getCrewMemberStatus().get(0).get(7);
		String crewIconSelected = "/img/" + crewType.toLowerCase() + ".png";
		String crewIcon = "/img/" + crewType.toLowerCase() + "-selected.png";
		System.out.println(crewIcon);
		memberOne.setSelectedIcon(new ImageIcon(CommandCenter.class.getResource(crewIconSelected)));
		memberOne.setIcon(new ImageIcon(CommandCenter.class.getResource(crewIcon)));
		memberOne.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				addCrewToSelection(0);
			}
		});
		memberOne.setBounds(12, 21, memberButtonsSize, memberButtonsSize);
		commitActions.add(memberOne);
		this.memberOne = memberOne;

		JToggleButton memberTwo = new JToggleButton("");
		memberTwo.setBorder(null);
		crewType = engine.getCrewMemberStatus().get(1).get(7);
		crewIconSelected = "/img/" + crewType.toLowerCase() + ".png";
		crewIcon = "/img/" + crewType.toLowerCase() + "-selected.png";
		memberTwo.setSelectedIcon(new ImageIcon(CommandCenter.class.getResource(crewIconSelected)));
		memberTwo.setIcon(new ImageIcon(CommandCenter.class.getResource(crewIcon)));
		memberTwo.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param e <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent e) {
				addCrewToSelection(1);
			}
		});
		memberTwo.setBounds(122, 21, memberButtonsSize, memberButtonsSize);
		commitActions.add(memberTwo);
		this.memberTwo = memberTwo;

		if (totalCrewMembers > 2) {
			JToggleButton memberThree = new JToggleButton("");
			memberThree.setBorder(null);
			crewType = engine.getCrewMemberStatus().get(2).get(7);
			crewIconSelected = "/img/" + crewType.toLowerCase() + ".png";
			crewIcon = "/img/" + crewType.toLowerCase() + "-selected.png";
			memberThree.setSelectedIcon(new ImageIcon(CommandCenter.class.getResource(crewIconSelected)));
			memberThree.setIcon(new ImageIcon(CommandCenter.class.getResource(crewIcon)));
			memberThree.addActionListener(new ActionListener() {
				/**
				 * <<auto generated javadoc comment>>
				 * @param e <<Param Desc>>
				 */
				public void actionPerformed(ActionEvent e) {
					addCrewToSelection(2);
				}
			});
			memberThree.setBounds(232, 21, memberButtonsSize, memberButtonsSize);
			commitActions.add(memberThree);
			this.memberThree = memberThree;

			if (totalCrewMembers > 3) {
				JToggleButton memberFour = new JToggleButton("");
				memberFour.setBorder(null);
				crewType = engine.getCrewMemberStatus().get(3).get(7);
				crewIconSelected = "/img/" + crewType.toLowerCase() + ".png";
				crewIcon = "/img/" + crewType.toLowerCase() + "-selected.png";
				memberFour.setSelectedIcon(new ImageIcon(CommandCenter.class.getResource(crewIconSelected)));
				memberFour.setIcon(new ImageIcon(CommandCenter.class.getResource(crewIcon)));
				memberFour.addActionListener(new ActionListener() {
					/**
					 * <<auto generated javadoc comment>>
					 * @param e <<Param Desc>>
					 */
					public void actionPerformed(ActionEvent e) {
						addCrewToSelection(3);

					}
				});
				memberFour.setBounds(342, 21, memberButtonsSize, memberButtonsSize);
				commitActions.add(memberFour);
				this.memberFour = memberFour;
			}
		}

		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END

		JPanel spaceshipStatus = new JPanel();

		spaceshipStatus.setBackground(Color.BLACK);
		tabbedPane.addTab("Spaceship Status", null, spaceshipStatus, null);
		spaceshipStatus.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setForeground(Color.GREEN);
		progressBar.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		progressBar.setBounds(30, 476, 923, 20);
		progressBar.setValue(engine.getSpaceshipHealth());
		progressBar.setStringPainted(true);
		spaceshipStatus.add(progressBar);
		spaceshipHealth = progressBar;

		JLabel lblShieldLevel = new JLabel("Shield Level ");
		lblShieldLevel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		lblShieldLevel.setForeground(Color.GRAY);
		lblShieldLevel.setBounds(30, 437, 165, 30);
		spaceshipStatus.add(lblShieldLevel);

		JLabel spaceshipIcon = new JLabel("");
		spaceshipIcon.setBounds(12, 12, 971, 396);
		spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship.png")));
		spaceshipStatus.add(spaceshipIcon);

		JPanel VisitOutpost = new JPanel();
		VisitOutpost.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Visit Outpost", null, VisitOutpost, null);
		VisitOutpost.setLayout(null);

		JLabel lblItemsOnSale = new JLabel("Items on sale:");
		lblItemsOnSale.setForeground(Color.GRAY);
		lblItemsOnSale.setBackground(Color.GRAY);
		lblItemsOnSale.setBounds(20, 12, 121, 34);
		VisitOutpost.add(lblItemsOnSale);

		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setForeground(Color.GRAY);
		lblName_1.setBackground(Color.GRAY);
		lblName_1.setBounds(x, 62, 66, 15);
		VisitOutpost.add(lblName_1);

		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setForeground(Color.GRAY);
		lblType_1.setBackground(Color.GRAY);
		lblType_1.setBounds(132, 62, 66, 15);
		VisitOutpost.add(lblType_1);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.GRAY);
		lblPrice.setBackground(Color.GRAY);
		lblPrice.setBounds(215, 62, 66, 15);
		VisitOutpost.add(lblPrice);

		JLabel lblHeals = new JLabel("Heals");
		lblHeals.setForeground(Color.GRAY);
		lblHeals.setBackground(Color.GRAY);
		lblHeals.setBounds(293, 62, 66, 15);
		VisitOutpost.add(lblHeals);

		JLabel lblFills = new JLabel("Fills");
		lblFills.setForeground(Color.GRAY);
		lblFills.setBackground(Color.GRAY);
		lblFills.setBounds(371, 62, 66, 15);
		VisitOutpost.add(lblFills);

		JLabel lblCuresPlague = new JLabel("Cures Plague");
		lblCuresPlague.setForeground(Color.GRAY);
		lblCuresPlague.setBackground(Color.GRAY);
		lblCuresPlague.setBounds(449, 62, 106, 15);
		VisitOutpost.add(lblCuresPlague);

		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START

		int itemDescription_y = 90;

		JLabel itemName = new JLabel("");
		itemName.setForeground(Color.WHITE);
		itemNames = itemName;
		itemName.setBounds(x, itemDescription_y, 106, 97);
		VisitOutpost.add(itemName);

		JLabel itemPrice = new JLabel("");
		itemPrice.setForeground(Color.WHITE);
		itemPrices = itemPrice;
		itemPrice.setBounds(215, itemDescription_y, 66, 97);
		VisitOutpost.add(itemPrice);

		JLabel itemHeal = new JLabel("");
		itemHeal.setForeground(Color.WHITE);
		itemHealings = itemHeal;
		itemHeal.setBounds(293, itemDescription_y, 66, 97);
		VisitOutpost.add(itemHeal);

		JLabel itemFill = new JLabel("");
		itemFill.setForeground(Color.WHITE);
		itemFills = itemFill;
		itemFill.setBounds(371, itemDescription_y, 66, 97);
		VisitOutpost.add(itemFill);

		JLabel itemCure = new JLabel("");
		itemCure.setForeground(Color.WHITE);
		itemCures = itemCure;
		itemCure.setBounds(449, itemDescription_y, 80, 97);
		VisitOutpost.add(itemCure);

		JLabel itemType = new JLabel("");
		itemType.setForeground(Color.WHITE);
		itemType.setBounds(137, itemDescription_y, 66, 97);
		VisitOutpost.add(itemType);
		itemTypes= itemType;

		/// ITEM DESCRIPTIONS END
		/// ITEM DESCRIPTIONS END
		/// ITEM DESCRIPTIONS END

		/// SHOPPING CART START
		/// SHOPPING CART START
		/// SHOPPING CART START

		int shopping_x = 600;

		JLabel lblShoppingBag = new JLabel("Your shopping cart:");
		lblShoppingBag.setForeground(Color.GRAY);
		lblShoppingBag.setBackground(Color.GRAY);
		lblShoppingBag.setBounds(shopping_x, 12, 150, 34);
		VisitOutpost.add(lblShoppingBag);

		int purchase_y = 258;

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setBorder(null);
		btnPurchase.setForeground(Color.WHITE);
		btnPurchase.setBackground(Color.DARK_GRAY);
		btnPurchase.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				cart.purchaseItems();
				currMoney.setText(String.valueOf(engine.getCrewMoney()));
				refreshInventory();
			}
		});
		btnPurchase.setBounds(852, purchase_y, 131, 25);
		VisitOutpost.add(btnPurchase);

		JLabel lblTotalPrice = new JLabel("Total price:");
		lblTotalPrice.setForeground(Color.GRAY);
		lblTotalPrice.setBackground(Color.GRAY);
		lblTotalPrice.setBounds(shopping_x, 258, 88, 25);
		VisitOutpost.add(lblTotalPrice);

		JLabel lblPrice_1 = new JLabel("price");
		lblPrice_1.setForeground(Color.WHITE);
		lblPrice_1.setText("$0");
		lblPrice_1.setBounds(715, purchase_y, 106, 25);
		VisitOutpost.add(lblPrice_1);
		cartPrice = lblPrice_1;

		cart = new ShoppingCart(VisitOutpost, shopping_x, 50, engine, cartPrice);

		/// SHOPPING CART END
		/// SHOPPING CART END
		/// SHOPPING CART END

		// ITEM ICONS START
		// ITEM ICONS START
		// ITEM ICONS START

		int consumablesIconSize = 100;
		int y = 250;
		int spacing = 20;

		Color defaultBGColor = new Color(20, 20, 20);
		JButton brownieBtn = new JButton(""); // Brownie
		brownieBtn.setBackground(defaultBGColor);
		brownieBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/brownie.png")));
		brownieBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Brownie");
			}
		});
		brownieBtn.setBounds(x, y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(brownieBtn);

		JButton friedRiceBtn = new JButton(""); // FriedRice
		friedRiceBtn.setBackground(defaultBGColor);
		friedRiceBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/friedrice.png")));
		friedRiceBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("FriedRice");
			}
		});
		friedRiceBtn.setBounds(x + spacing + consumablesIconSize, y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(friedRiceBtn);

		JButton dumplingsBtn = new JButton(""); // Dumplings
		dumplingsBtn.setBackground(defaultBGColor);
		dumplingsBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/dumplings.png")));
		dumplingsBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Dumplings");
			}
		});
		dumplingsBtn.setBounds(x + 2 * (spacing + consumablesIconSize), y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(dumplingsBtn);

		JButton spacecakeBtn = new JButton(""); // Space cake
		spacecakeBtn.setBackground(defaultBGColor);
		spacecakeBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spacecake.png")));
		spacecakeBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("SpaceCake");
			}
		});
		spacecakeBtn.setBounds(x, y + consumablesIconSize + spacing, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(spacecakeBtn);

		JButton tikkaMasalaBtn = new JButton(""); // Tikka Masala
		tikkaMasalaBtn.setBackground(defaultBGColor);
		tikkaMasalaBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/tikkamasala.png")));
		tikkaMasalaBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("TikkaMasala");
			}
		});
		tikkaMasalaBtn.setBounds(x + spacing + consumablesIconSize, y + spacing + consumablesIconSize, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(tikkaMasalaBtn);

		JButton hotbotBtn = new JButton(""); // Hotbot
		hotbotBtn.setBackground(defaultBGColor);
		hotbotBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/hotbot.png")));
		hotbotBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Hotbot");
			}
		});
		hotbotBtn.setBounds(x + 2 * (spacing + consumablesIconSize), y + spacing + consumablesIconSize, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(hotbotBtn);

		JButton polyjuiceBtn = new JButton(""); // Poly Juice
		polyjuiceBtn.setBackground(defaultBGColor);
		polyjuiceBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/polyjuice.png")));
		polyjuiceBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("PolyJuice");
			}
		});
		polyjuiceBtn.setBounds(x, y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(polyjuiceBtn);

		JButton pickledPlumBtn = new JButton(""); // Pickled Plum
		pickledPlumBtn.setBackground(defaultBGColor);
		pickledPlumBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/pickledplum.png")));
		pickledPlumBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("PickledPlum");
			}
		});
		pickledPlumBtn.setBounds(x + spacing + consumablesIconSize, y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(pickledPlumBtn);

		JButton vaccineBtn = new JButton(""); // Vaccine
		vaccineBtn.setBackground(defaultBGColor);
		vaccineBtn.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/vaccine.png")));
		vaccineBtn.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Vaccine");
			}
		});
		vaccineBtn.setBounds(x + 2 * (spacing + consumablesIconSize), y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(vaccineBtn);

		// ITEM ICONS END
		// ITEM ICONS END
		// ITEM ICONS END

		/// SELECTED ITEM START
		/// SELECTED ITEM START
		/// SELECTED ITEM START

		int selected_x = 380;

		JLabel lblYouSelected = new JLabel("You selected:");
		lblYouSelected.setForeground(Color.GRAY);
		lblYouSelected.setBackground(Color.GRAY);
		lblYouSelected.setBounds(selected_x, y, 106, 34);
		VisitOutpost.add(lblYouSelected);

		int spinnerMin = 0;
		int spinnerMax = 50;
		int spinnerStep = 1;
		int spinnerInitValue = 0;
		SpinnerModel model = new SpinnerNumberModel(spinnerInitValue, spinnerMin, spinnerMax, spinnerStep);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(selected_x, 413, 66, 45);
		VisitOutpost.add(spinner);

		JLabel lblSelecteditem = new JLabel("selectedItem");
		lblSelecteditem.setForeground(Color.GRAY);
		lblSelecteditem.setBackground(Color.GRAY);
		lblSelecteditem.setBounds(selected_x, 282, 106, 106);
		VisitOutpost.add(lblSelecteditem);
		selectedItem = lblSelecteditem;

		JButton btnAddToCart = new JButton("Add to cart");
		btnAddToCart.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				String itemQuery = itemNames.getText();
				if (itemQuery.equals(""))
					return;
				int amount = (Integer) spinner.getValue();
				cart.addItemToShoppingCart(amount + "x" + itemQuery);
			}
		});
		btnAddToCart.setBounds(380, 472, 114, 25);
		VisitOutpost.add(btnAddToCart);

		/// SELECTED ITEM END
		/// SELECTED ITEM END
		/// SELECTED ITEM END

		// SEPARATOR IN THE MIDDLE
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(564, 26, 18, 554);
		VisitOutpost.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(shopping_x, 310, 383, 2);
		VisitOutpost.add(separator_1);

		// INVENTORY ITEM START
		// INVENTORY ITEM START
		// INVENTORY ITEM START

		JLabel lblYourInventory = new JLabel("Your inventory:");
		lblYourInventory.setForeground(Color.GRAY);
		lblYourInventory.setBackground(Color.GRAY);
		lblYourInventory.setBounds(shopping_x, 310, 121, 34);
		VisitOutpost.add(lblYourInventory);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setBounds(shopping_x, 356, 383, 229);
		VisitOutpost.add(lblNewLabel);
		currInventory = lblNewLabel;

		// INVENTORY ITEM END
		// INVENTORY ITEM END
		// INVENTORY ITEM END

		JButton btnNewButton = new JButton("End day");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				engine.endDay();
				if (engine.hasGameEnded()) {
					finishedWindow();
				}
				refreshPage();
				refreshSpaceshipPage();
				refreshCrewStatusPage();

				startDay();
			}
		});

		int bottomPanel_y = 673;
		btnNewButton.setBounds(898, bottomPanel_y, 114, 25);
		frmCommandCenter.getContentPane().add(btnNewButton);

		JLabel lblShipPiecesFound = new JLabel("Ship Pieces Found");
		lblShipPiecesFound.setForeground(Color.LIGHT_GRAY);
		lblShipPiecesFound.setBounds(12, bottomPanel_y, 136, 25);
		frmCommandCenter.getContentPane().add(lblShipPiecesFound);

		JLabel lblCurrentDay = new JLabel("Current Day");
		lblCurrentDay.setForeground(Color.LIGHT_GRAY);
		lblCurrentDay.setBounds(359, bottomPanel_y, 99, 25);
		frmCommandCenter.getContentPane().add(lblCurrentDay);

		JLabel lblX = new JLabel("x");
		lblX.setForeground(Color.LIGHT_GRAY);
		lblX.setText("0");
		lblX.setBounds(160, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblX);
		currShipPieces = lblX;

		JLabel lblOutOf = new JLabel("out of");
		lblOutOf.setForeground(Color.LIGHT_GRAY);
		lblOutOf.setBounds(186, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblOutOf);

		JLabel lblY = new JLabel("y");
		lblY.setForeground(Color.LIGHT_GRAY);
		lblY.setBounds(241, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblY);
		lblY.setText(String.valueOf(engine.getShipPieces()));

		JLabel label_8 = new JLabel("x");
		label_8.setForeground(Color.LIGHT_GRAY);
		label_8.setText("1");
		label_8.setBounds(456, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_8);
		currDay = label_8;

		JLabel label_9 = new JLabel("out of");
		label_9.setForeground(Color.LIGHT_GRAY);
		label_9.setBounds(482, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_9);

		JLabel label_10 = new JLabel("y");
		label_10.setForeground(Color.LIGHT_GRAY);
		label_10.setBounds(537, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_10);
		label_10.setText(String.valueOf(engine.getGameLength()));

		JLabel lblMoney = new JLabel("Money");
		lblMoney.setForeground(Color.LIGHT_GRAY);
		lblMoney.setBounds(598, bottomPanel_y, 64, 25);
		frmCommandCenter.getContentPane().add(lblMoney);

		JLabel label_11 = new JLabel("$0");
		label_11.setForeground(Color.LIGHT_GRAY);
		label_11.setBounds(674, bottomPanel_y, 64, 25);
		frmCommandCenter.getContentPane().add(label_11);
		currMoney = label_11;

		refreshCrewStatusPage();
		currMoney.setText(String.valueOf(engine.getCrewMoney()));
	}
}

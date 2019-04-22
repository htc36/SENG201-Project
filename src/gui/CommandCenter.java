package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;

public class CommandCenter {

	private JFrame frmCommandCenter;
	private GameEngine engine;
	private GameGUI game;
	private JLabel currShipPieces;
	private JLabel currDay;
	private JProgressBar spaceshipHealth;
	private JLabel spaceshipIcon;

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
	private JLabel infoBox;

	private int totalCrewMembers;

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

	private void refreshPage() {
		currShipPieces.setText(String.valueOf(engine.getFoundShipPieces()));
		currDay.setText(String.valueOf(engine.getCurrDay()));
	}

	private void refreshSpaceshipPage() {
		int shipHealth = engine.getSpaceshipHealth();
		spaceshipHealth.setValue(shipHealth);

		if (shipHealth < 30) {
			spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship-dmg2.png")));
		} else if (shipHealth < 60) {
			spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship-dmg1.png")));
		} else {
			spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship.png")));
		}
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
	
	private void selectedCrewUseConsumable() {
		if (engine.getCrewConsumablesCount() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "No items in the inventory :(");
			return;
		}
	}

	public void closeWindow() {
		frmCommandCenter.dispose();
	}

	public void finishedWindow() {
		game.closeCommandCenter(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCommandCenter = new JFrame();
		frmCommandCenter.setResizable(false);
		frmCommandCenter.setTitle("Command Center");
		frmCommandCenter.setBounds(100, 100, 1024, 768);
		frmCommandCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCommandCenter.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 1000, 648);
		frmCommandCenter.getContentPane().add(tabbedPane);

		JPanel crewStatus = new JPanel();
		tabbedPane.addTab("Crew Status", null, crewStatus, null);
		crewStatus.setLayout(null);

		tabbedPane.addChangeListener(new ChangeListener() {

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
					break;
				}
			}
		});


		int x = 20;

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblName.setBounds(86, 29, 82, 62);
		crewStatus.add(lblName);

		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblType.setBounds(275, 29, 82, 62);
		crewStatus.add(lblType);

		JLabel lblHealth = new JLabel("Health");
		lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealth.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHealth.setBounds(379, 29, 82, 62);
		crewStatus.add(lblHealth);

		JLabel lblLuck = new JLabel("Luck");
		lblLuck.setHorizontalAlignment(SwingConstants.CENTER);
		lblLuck.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblLuck.setBounds(473, 29, 82, 62);
		crewStatus.add(lblLuck);

		JLabel lblPlagued = new JLabel("Plagued");
		lblPlagued.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlagued.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblPlagued.setBounds(567, 29, 109, 62);
		crewStatus.add(lblPlagued);

		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setHorizontalAlignment(SwingConstants.CENTER);
		lblHunger.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHunger.setBounds(676, 29, 82, 62);
		crewStatus.add(lblHunger);

		JLabel lblFatigue = new JLabel("Fatigue");
		lblFatigue.setHorizontalAlignment(SwingConstants.CENTER);
		lblFatigue.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblFatigue.setBounds(780, 29, 94, 62);
		crewStatus.add(lblFatigue);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setHorizontalAlignment(SwingConstants.CENTER);
		lblActions.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblActions.setBounds(886, 29, 82, 62);
		crewStatus.add(lblActions);

		JLabel label = new JLabel("Name");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Ubuntu", Font.BOLD, 20));

		crewNames = label;
		label.setBounds(12, 112, 238, 471);
		crewStatus.add(label);

		JLabel label_1 = new JLabel("Type");
		label_1.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		crewTypes = label_1;
		label_1.setBounds(258, 112, 109, 471);
		crewStatus.add(label_1);

		JLabel label_2 = new JLabel("Type");
		label_2.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		crewHealths = label_2;
		label_2.setBounds(389, 112, 82, 471);
		crewStatus.add(label_2);

		JLabel label_3 = new JLabel("Type");
		label_3.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		crewLucks = label_3;
		label_3.setBounds(483, 112, 82, 471);
		crewStatus.add(label_3);

		JLabel label_4 = new JLabel("Type");
		label_4.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		crewPlagues = label_4;
		label_4.setBounds(577, 112, 82, 471);
		crewStatus.add(label_4);

		JLabel label_5 = new JLabel("Type");
		label_5.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		crewHungers = label_5;
		label_5.setBounds(676, 112, 82, 471);
		crewStatus.add(label_5);

		JLabel label_6 = new JLabel("Type");
		label_6.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		crewFatigues = label_6;
		label_6.setBounds(780, 112, 82, 471);
		crewStatus.add(label_6);

		JLabel label_7 = new JLabel("Type");
		label_7.setFont(new Font("Ubuntu", Font.BOLD, 20));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		crewActions = label_7;
		label_7.setBounds(886, 112, 82, 471);
		crewStatus.add(label_7);

		JPanel commitActions = new JPanel();
		tabbedPane.addTab("Commit Actions", null, commitActions, null);
		commitActions.setLayout(null);

		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START

		JButton memberUseConsumable = new JButton("Use consumables");
		memberUseConsumable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		memberUseConsumable.setBounds(12, 124, 192, 179);
		commitActions.add(memberUseConsumable);

		JButton memberSleep = new JButton("Sleep");
		memberSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					engine.selectedCrewSleep();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberSleep.setBounds(211, 124, 192, 179);
		commitActions.add(memberSleep);

		JButton memberRepair = new JButton("Repair shield");
		memberRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					engine.selectedCrewRepairShield();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberRepair.setBounds(12, 315, 192, 179);
		commitActions.add(memberRepair);

		JButton memberSearch = new JButton("Search the planet");
		memberSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "1 crew member needed for this action");
					return;
				}
				try {
					engine.selectedCrewSearchPlanet();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberSearch.setBounds(211, 315, 192, 179);
		commitActions.add(memberSearch);

		JButton memberPilot = new JButton("PUNCH THE BOOSTERS");
		memberPilot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrews.size() != 2) {
					JOptionPane.showMessageDialog(new JFrame(), "You need 2 crew members for this action");
					return;
				}
				try {
					engine.selectedCrewPilotSpaceship();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberPilot.setBounds(12, 506, 390, 103);
		commitActions.add(memberPilot);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(522, 24, 461, 574);
		commitActions.add(lblNewLabel_2);
		infoBox = lblNewLabel_2;

		JToggleButton memberOne = new JToggleButton("New toggle button");
		memberOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCrewToSelection(0);
			}
		});
		memberOne.setBounds(12, 21, 109, 91);
		commitActions.add(memberOne);
		this.memberOne = memberOne;

		JToggleButton memberTwo = new JToggleButton("New toggle button");
		memberTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCrewToSelection(1);
			}
		});
		memberTwo.setBounds(135, 21, 109, 91);
		commitActions.add(memberTwo);
		this.memberTwo = memberTwo;

		if (totalCrewMembers > 2) {
			JToggleButton memberThree = new JToggleButton("New toggle button");
			memberThree.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCrewToSelection(2);
				}
			});
			memberThree.setBounds(256, 21, 109, 91);
			commitActions.add(memberThree);
			this.memberThree = memberThree;

			if (totalCrewMembers > 3) {
				JToggleButton memberFour = new JToggleButton("New toggle button");
				memberFour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addCrewToSelection(3);

					}
				});
				memberFour.setBounds(377, 21, 109, 91);
				commitActions.add(memberFour);
				this.memberFour = memberFour;
			}
		}

		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END

		JPanel spaceshipStatus = new JPanel();

		spaceshipStatus.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Spaceship Status", null, spaceshipStatus, null);
		spaceshipStatus.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(30, 476, 923, 45);
		progressBar.setValue(engine.getSpaceshipHealth());
		progressBar.setStringPainted(true);
		spaceshipStatus.add(progressBar);
		spaceshipHealth = progressBar;

		JLabel lblShieldLevel = new JLabel("Shield Level");
		lblShieldLevel.setBounds(30, 420, 107, 15);
		spaceshipStatus.add(lblShieldLevel);

		JLabel spaceshipIcon = new JLabel("");
		spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship-dmg2.png")));
		spaceshipIcon.setBounds(12, 12, 971, 396);
		spaceshipStatus.add(spaceshipIcon);
		this.spaceshipIcon = spaceshipIcon;

		JPanel VisitOutpost = new JPanel();
		tabbedPane.addTab("Visit Outpost", null, VisitOutpost, null);
		VisitOutpost.setLayout(null);

		JLabel lblItemsOnSale = new JLabel("Items on sale:");
		lblItemsOnSale.setBounds(20, 12, 121, 34);
		VisitOutpost.add(lblItemsOnSale);

		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(x, 62, 66, 15);
		VisitOutpost.add(lblName_1);

		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setBounds(132, 62, 66, 15);
		VisitOutpost.add(lblType_1);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(215, 62, 66, 15);
		VisitOutpost.add(lblPrice);

		JLabel lblHeals = new JLabel("Heals");
		lblHeals.setBounds(293, 62, 66, 15);
		VisitOutpost.add(lblHeals);

		JLabel lblFills = new JLabel("Fills");
		lblFills.setBounds(371, 62, 66, 15);
		VisitOutpost.add(lblFills);

		JLabel lblCuresPlague = new JLabel("Cures Plague");
		lblCuresPlague.setBounds(449, 62, 106, 15);
		VisitOutpost.add(lblCuresPlague);

		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START

		int itemDescription_y = 90;

		JLabel itemName = new JLabel("");
		itemNames = itemName;
		itemName.setBounds(x, itemDescription_y, 106, 97);
		VisitOutpost.add(itemName);

		JLabel itemPrice = new JLabel("");
		itemPrices = itemPrice;
		itemPrice.setBounds(215, itemDescription_y, 66, 97);
		VisitOutpost.add(itemPrice);

		JLabel itemHeal = new JLabel("");
		itemHealings = itemHeal;
		itemHeal.setBounds(293, itemDescription_y, 66, 97);
		VisitOutpost.add(itemHeal);

		JLabel itemFill = new JLabel("");
		itemFills = itemFill;
		itemFill.setBounds(371, itemDescription_y, 66, 97);
		VisitOutpost.add(itemFill);

		JLabel itemCure = new JLabel("");
		itemCures = itemCure;
		itemCure.setBounds(449, itemDescription_y, 80, 97);
		VisitOutpost.add(itemCure);

		JLabel itemType = new JLabel("");
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
		lblShoppingBag.setBounds(shopping_x, 12, 150, 34);
		VisitOutpost.add(lblShoppingBag);

		int purchase_y = 258;

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cart.purchaseItems();
				currMoney.setText(String.valueOf(engine.getCrewMoney()));
				refreshInventory();
			}
		});
		btnPurchase.setBounds(852, purchase_y, 131, 25);
		VisitOutpost.add(btnPurchase);

		JLabel lblTotalPrice = new JLabel("Total price:");
		lblTotalPrice.setBounds(shopping_x, 258, 88, 25);
		VisitOutpost.add(lblTotalPrice);

		JLabel lblPrice_1 = new JLabel("price");
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

		JButton btnNewButton_1 = new JButton(""); // Brownie
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/brownie.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Brownie");
			}
		});
		btnNewButton_1.setBounds(x, y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnNewButton_1);

		JButton btnF = new JButton(""); // FriedRice
		btnF.setBackground(Color.DARK_GRAY);
		btnF.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/friedrice.png")));
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("FriedRice");
			}
		});
		btnF.setBounds(x + spacing + consumablesIconSize, y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnF);

		JButton btnF_1 = new JButton(""); // Dumplings
		btnF_1.setBackground(Color.DARK_GRAY);
		btnF_1.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/dumplings.png")));
		btnF_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Dumplings");
			}
		});
		btnF_1.setBounds(x + 2 * (spacing + consumablesIconSize), y, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnF_1);

		JButton btnF_2 = new JButton(""); // Space cake
		btnF_2.setBackground(Color.DARK_GRAY);
		btnF_2.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spacecake.png")));
		btnF_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("SpaceCake");
			}
		});
		btnF_2.setBounds(x, y + consumablesIconSize + spacing, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnF_2);

		JButton btnF_3 = new JButton(""); // Tikka Masala
		btnF_3.setBackground(Color.DARK_GRAY);
		btnF_3.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/tikkamasala.png")));
		btnF_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("TikkaMasala");
			}
		});
		btnF_3.setBounds(x + spacing + consumablesIconSize, y + spacing + consumablesIconSize, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnF_3);

		JButton btnF_4 = new JButton(""); // Hotbot
		btnF_4.setBackground(Color.DARK_GRAY);
		btnF_4.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/hotbot.png")));
		btnF_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Hotbot");
			}
		});
		btnF_4.setBounds(x + 2 * (spacing + consumablesIconSize), y + spacing + consumablesIconSize, consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnF_4);

		JButton btnM = new JButton(""); // Poly Juice
		btnM.setBackground(Color.DARK_GRAY);
		btnM.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/polyjuice.png")));
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("PolyJuice");
			}
		});
		btnM.setBounds(x, y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnM);

		JButton btnM_1 = new JButton(""); // Pickled Plum
		btnM_1.setBackground(Color.DARK_GRAY);
		btnM_1.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/pickledplum.png")));
		btnM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("PickledPlum");
			}
		});
		btnM_1.setBounds(x + spacing + consumablesIconSize, y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnM_1);

		JButton btnM_2 = new JButton(""); // Vaccine
		btnM_2.setBackground(Color.DARK_GRAY);
		btnM_2.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/vaccine.png")));
		btnM_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showItemDetails("Vaccine");
			}
		});
		btnM_2.setBounds(x + 2 * (spacing + consumablesIconSize), y + 2 * (consumablesIconSize + spacing), consumablesIconSize, consumablesIconSize);
		VisitOutpost.add(btnM_2);

		// ITEM ICONS END
		// ITEM ICONS END
		// ITEM ICONS END

		/// SELECTED ITEM START
		/// SELECTED ITEM START
		/// SELECTED ITEM START

		int selected_x = 380;

		JLabel lblYouSelected = new JLabel("You selected:");
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
		lblSelecteditem.setBounds(selected_x, 282, 106, 106);
		VisitOutpost.add(lblSelecteditem);
		selectedItem = lblSelecteditem;

		JButton btnAddToCart = new JButton("Add to cart");
		btnAddToCart.addActionListener(new ActionListener() {
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
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(564, 26, 18, 554);
		VisitOutpost.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(shopping_x, 310, 383, 2);
		VisitOutpost.add(separator_1);

		// INVENTORY ITEM START
		// INVENTORY ITEM START
		// INVENTORY ITEM START

		JLabel lblYourInventory = new JLabel("Your inventory:");
		lblYourInventory.setBounds(shopping_x, 310, 121, 34);
		VisitOutpost.add(lblYourInventory);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(shopping_x, 356, 383, 229);
		VisitOutpost.add(lblNewLabel);
		currInventory = lblNewLabel;

		// INVENTORY ITEM END
		// INVENTORY ITEM END
		// INVENTORY ITEM END

		JButton btnNewButton = new JButton("End day");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				engine.endDay();
				if (engine.hasGameEnded()) {
					finishedWindow();
				}
				refreshPage();
				refreshSpaceshipPage();
				refreshCrewStatusPage();
			}
		});

		int bottomPanel_y = 673;
		btnNewButton.setBounds(898, bottomPanel_y, 114, 25);
		frmCommandCenter.getContentPane().add(btnNewButton);

		JLabel lblShipPiecesFound = new JLabel("Ship Pieces Found");
		lblShipPiecesFound.setBounds(12, bottomPanel_y, 136, 25);
		frmCommandCenter.getContentPane().add(lblShipPiecesFound);

		JLabel lblCurrentDay = new JLabel("Current Day");
		lblCurrentDay.setBounds(359, bottomPanel_y, 99, 25);
		frmCommandCenter.getContentPane().add(lblCurrentDay);

		JLabel lblX = new JLabel("x");
		lblX.setText("0");
		lblX.setBounds(160, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblX);
		currShipPieces = lblX;

		JLabel lblOutOf = new JLabel("out of");
		lblOutOf.setBounds(186, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblOutOf);

		JLabel lblY = new JLabel("y");
		lblY.setBounds(241, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblY);
		lblY.setText(String.valueOf(engine.getShipPieces()));

		JLabel label_8 = new JLabel("x");
		label_8.setText("1");
		label_8.setBounds(456, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_8);
		currDay = label_8;

		JLabel label_9 = new JLabel("out of");
		label_9.setBounds(482, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_9);

		JLabel label_10 = new JLabel("y");
		label_10.setBounds(537, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(label_10);
		label_10.setText(String.valueOf(engine.getGameLength()));

		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(598, bottomPanel_y, 64, 25);
		frmCommandCenter.getContentPane().add(lblMoney);

		JLabel label_11 = new JLabel("$0");
		label_11.setBounds(674, bottomPanel_y, 64, 25);
		frmCommandCenter.getContentPane().add(label_11);
		currMoney = label_11;

		JMenuBar menuBar = new JMenuBar();
		frmCommandCenter.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnNewMenu.add(mntmLoad);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);

		refreshCrewStatusPage();
		currMoney.setText(String.valueOf(engine.getCrewMoney()));
	}
}

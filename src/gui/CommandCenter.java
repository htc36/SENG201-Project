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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


import java.awt.Color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.InsetsUIResource;

import crew.InsufficientItemInStock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
	private ArrayList<JToggleButton> crewButtons;
	
	private JLabel radarPlanetStatus;

	private JLabel infoBox;

	/**
	 * Create the application.
	 */
	public CommandCenter(GameEngine engine, GameGUI game) {
		this.engine = engine;
		this.game = game;
		selectedCrews = new ArrayList<>();
		crewButtons = new ArrayList<>();
		initialize();
		frmCommandCenter.setVisible(true);
	}

	public void startDay() {
		int randomEvent = engine.getRandomEvent();
		switch (randomEvent) {
		case 1:
			JOptionPane.showMessageDialog(new JFrame(), "Alien Pirates");
			break;
		case 0:
			JOptionPane.showMessageDialog(new JFrame(), "Space Plague");
			break;
		}
	}

	private void refreshPage() {
		currShipPieces.setText(String.valueOf(engine.getFoundShipPieces()));
		currDay.setText(String.valueOf(engine.getCurrDay()));
		currMoney.setText(String.valueOf(engine.getCrewMoney()));
		radarPlanetStatus.setText("No ship piece detected");
		if (engine.planetHasShipPieces())
			radarPlanetStatus.setText("Detected ship piece");
	}

	private void refreshSpaceshipPage() {
		int shipHealth = engine.getSpaceshipHealth();
		if (shipHealth <= 30) {
		    spaceshipHealth.setForeground(new Color(201, 29, 18));
		} else if (shipHealth <= 60) {
		    spaceshipHealth.setForeground(new Color(240, 230, 140));
		} else
		    spaceshipHealth.setForeground(new Color(34, 139, 34));
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
			String health = member.get(1);
			if (Integer.valueOf(health) == 0) {
				continue;
			}
			crewHealth += health;
			for (int i = 0; i < numNewlines; i++) {
				crewHealth += "<br>";
			}

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

		engine.getDeadCrewMembers();
		
		this.crewNames.setText(crewNames);
		this.crewTypes.setText(crewTypes);
		this.crewHealths.setText(crewHealth);
		this.crewLucks.setText(crewLuck);
		this.crewPlagues.setText(crewPlagued);
		this.crewHungers.setText(crewHunger);
		this.crewFatigues.setText(crewFatigue);
		this.crewActions.setText(crewActions);
	}
	
	private void refreshCrewButtons(JPanel commitActions) {
		int memberButtonsSize = 100;
		int memberButtonsSpacing = 10;
		int counter = 0;
		
		ArrayList<ArrayList<String>> crewMembers = engine.getCrewMemberStatus();
		if (crewButtons.size() != 0) {
			for (int i = 0; i < crewMembers.size(); i++) {
				int health = Integer.valueOf(crewMembers.get(i).get(1));
				if (health == 0) {
					crewButtons.get(i).setVisible(false);
				}
			}
			return;
		}
		
		for (ArrayList<String> member : crewMembers) {
			JToggleButton cMember = new JToggleButton("");
			crewButtons.add(cMember);
			cMember.setBorder(null);
			String crewType = engine.getCrewMemberStatus().get(counter).get(7);
			String crewIconSelected = "/img/" + crewType.toLowerCase() + ".png";
			String crewIcon = "/img/" + crewType.toLowerCase() + "-selected.png";
			cMember.setSelectedIcon(new ImageIcon(CommandCenter.class.getResource(crewIconSelected)));
			cMember.setIcon(new ImageIcon(CommandCenter.class.getResource(crewIcon)));
			cMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCrewToSelection(crewMembers.indexOf(member));
				}
			});
			cMember.setBounds(12 + counter * (memberButtonsSize + memberButtonsSpacing), 21, memberButtonsSize, memberButtonsSize);
			commitActions.add(cMember);
			counter++;
		}

	}

	private void refreshSelectedCrews() {
		for (int i = 0; i < crewButtons.size(); i++) {
			if (selectedCrews.contains(i)) {
				crewButtons.get(i).setSelected(true);
			} else
				crewButtons.get(i).setSelected(false);
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
					} catch (InsufficientItemInStock err) {
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
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0, 0, 0, 0));
		frmCommandCenter = new JFrame();
		frmCommandCenter.getContentPane().setBackground(Color.BLACK);
		frmCommandCenter.setResizable(false);
		frmCommandCenter.setTitle("Command Center");
		frmCommandCenter.setBounds(100, 100, 1024, 800);
		frmCommandCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCommandCenter.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new ModernTab());
		tabbedPane.setForeground(Color.WHITE);
		
		tabbedPane.setFont(new Font("Quantico", Font.BOLD, 16));
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(12, 0, 1000, 648);
		frmCommandCenter.getContentPane().add(tabbedPane);

		JPanel crewStatus = new JPanel();
		crewStatus.setForeground(new Color(204, 204, 204));
		crewStatus.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Crew Status", null, crewStatus, null);
		crewStatus.setLayout(null);

		int x = 20;
		Font defaultHeaderFont = new Font("Quantico", Font.PLAIN, 20);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(102, 102, 102));
		lblName.setFont(defaultHeaderFont);
		lblName.setBounds(86, 29, 82, 62);
		crewStatus.add(lblName);

		JLabel lblType = new JLabel("Type");
		lblType.setForeground(new Color(102, 102, 102));
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(defaultHeaderFont);
		lblType.setBounds(258, 29, 70, 62);
		crewStatus.add(lblType);

		JLabel lblHealth = new JLabel("Health");
		lblHealth.setForeground(new Color(102, 102, 102));
		lblHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblHealth.setFont(defaultHeaderFont);
		lblHealth.setBounds(379, 29, 82, 62);
		crewStatus.add(lblHealth);

		JLabel lblLuck = new JLabel("Luck");
		lblLuck.setForeground(new Color(102, 102, 102));
		lblLuck.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuck.setFont(defaultHeaderFont);
		lblLuck.setBounds(473, 29, 82, 62);
		crewStatus.add(lblLuck);

		JLabel lblPlagued = new JLabel("Plagued");
		lblPlagued.setForeground(new Color(102, 102, 102));
		lblPlagued.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlagued.setFont(defaultHeaderFont);
		lblPlagued.setBounds(567, 29, 109, 62);
		crewStatus.add(lblPlagued);

		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setForeground(new Color(102, 102, 102));
		lblHunger.setHorizontalAlignment(SwingConstants.LEFT);
		lblHunger.setFont(defaultHeaderFont);
		lblHunger.setBounds(676, 29, 82, 62);
		crewStatus.add(lblHunger);

		JLabel lblFatigue = new JLabel("Fatigue");
		lblFatigue.setForeground(new Color(102, 102, 102));
		lblFatigue.setHorizontalAlignment(SwingConstants.LEFT);
		lblFatigue.setFont(defaultHeaderFont);
		lblFatigue.setBounds(780, 29, 94, 62);
		crewStatus.add(lblFatigue);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setForeground(new Color(102, 102, 102));
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setFont(defaultHeaderFont);
		lblActions.setBounds(886, 29, 82, 62);
		crewStatus.add(lblActions);

		Font crewStatusFont = new Font("Quantico", Font.PLAIN, 18);

		JLabel lblCrewNames = new JLabel("Name");
		lblCrewNames.setForeground(new Color(204, 204, 204));
		lblCrewNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewNames.setFont(crewStatusFont);
		crewNames = lblCrewNames;
		lblCrewNames.setBounds(12, 112, 238, 471);
		crewStatus.add(lblCrewNames);
		
		JLabel lblCrewTypes = new JLabel("Type");
		lblCrewTypes.setForeground(new Color(204, 204, 204));
		lblCrewTypes.setFont(crewStatusFont);
		lblCrewTypes.setHorizontalAlignment(SwingConstants.LEFT);
		crewTypes = lblCrewTypes;
		lblCrewTypes.setBounds(258, 112, 109, 471);
		crewStatus.add(lblCrewTypes);

		JLabel lblCrewHealths = new JLabel("Type");
		lblCrewHealths.setForeground(new Color(204, 204, 204));
		lblCrewHealths.setFont(crewStatusFont);
		lblCrewHealths.setHorizontalAlignment(SwingConstants.LEFT);
		crewHealths = lblCrewHealths;
		lblCrewHealths.setBounds(379, 112, 82, 471);
		crewStatus.add(lblCrewHealths);

		JLabel lblCrewLucks = new JLabel("Type");
		lblCrewLucks.setForeground(new Color(204, 204, 204));
		lblCrewLucks.setFont(crewStatusFont);
		lblCrewLucks.setHorizontalAlignment(SwingConstants.LEFT);
		crewLucks = lblCrewLucks;
		lblCrewLucks.setBounds(473, 112, 82, 471);
		crewStatus.add(lblCrewLucks);

		JLabel lblCrewPlagues = new JLabel("Type");
		lblCrewPlagues.setForeground(new Color(204, 204, 204));
		lblCrewPlagues.setFont(crewStatusFont);
		lblCrewPlagues.setHorizontalAlignment(SwingConstants.LEFT);
		crewPlagues = lblCrewPlagues;
		lblCrewPlagues.setBounds(567, 112, 82, 471);
		crewStatus.add(lblCrewPlagues);

		JLabel lblCrewHungers = new JLabel("Type");
		lblCrewHungers.setForeground(new Color(204, 204, 204));
		lblCrewHungers.setFont(crewStatusFont);
		lblCrewHungers.setHorizontalAlignment(SwingConstants.LEFT);
		crewHungers = lblCrewHungers;
		lblCrewHungers.setBounds(676, 112, 82, 471);
		crewStatus.add(lblCrewHungers);

		JLabel lblCrewFatigues = new JLabel("Type");
		lblCrewFatigues.setForeground(new Color(204, 204, 204));
		lblCrewFatigues.setFont(crewStatusFont);
		lblCrewFatigues.setHorizontalAlignment(SwingConstants.LEFT);
		crewFatigues = lblCrewFatigues;
		lblCrewFatigues.setBounds(780, 112, 82, 471);
		crewStatus.add(lblCrewFatigues);

		JLabel lblCrewActions = new JLabel("Type");
		lblCrewActions.setForeground(new Color(204, 204, 204));
		lblCrewActions.setFont(crewStatusFont);
		lblCrewActions.setHorizontalAlignment(SwingConstants.LEFT);
		crewActions = lblCrewActions;
		lblCrewActions.setBounds(886, 112, 82, 471);
		crewStatus.add(lblCrewActions);

		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START
		/// COMMIT ACTIONS START

		JPanel commitActions = new JPanel();
		commitActions.setBorder(null);
		commitActions.setBackground(Color.BLACK);
		tabbedPane.addTab("Commit Actions", null, commitActions, null);
		commitActions.setLayout(null);

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
						finishedWindow();
						return;
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
				refreshInventory();

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

				if (!engine.isSpaceshipAbleToFly()) {
					JOptionPane.showMessageDialog(new JFrame(), "Spaceship is too damaged to take off");
					return;
				}

				try {
					engine.selectedCrewPilotSpaceship();
					if (engine.isHitAsteroid()) {
						engine.asteroidCausingDamage();
						JOptionPane.showMessageDialog(new JFrame(), "Crashed asteroid, arrived at " + engine.getPlanetName());
					} else 
						JOptionPane.showMessageDialog(new JFrame(), "Arrived safely at " + engine.getPlanetName());

					if (engine.planetHasShipPieces())
						JOptionPane.showMessageDialog(new JFrame(), "Planet has ship piece");

					refreshPage();
				} catch (InsufficientActionException err) {
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage());
				}
			}
		});
		memberPilot.setBounds(12, 506, 430, 103);
		commitActions.add(memberPilot);

		JLabel lblCurrentInventory_2 = new JLabel("");
		lblCurrentInventory_2.setBounds(460, 21, 523, 588);
		commitActions.add(lblCurrentInventory_2);
		infoBox = lblCurrentInventory_2;

		refreshCrewButtons(commitActions);

		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END
		/// COMMIT ACTIONS END

		/// SPACESHIP PAGE START
		/// SPACESHIP PAGE START
		/// SPACESHIP PAGE START

		JPanel spaceshipStatus = new JPanel();

		spaceshipStatus.setBackground(Color.BLACK);
		tabbedPane.addTab("Spaceship Status", null, spaceshipStatus, null);
		spaceshipStatus.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setForeground(new Color(34, 139, 34));
		progressBar.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		progressBar.setBounds(30, 476, 923, 20);
		progressBar.setValue(engine.getSpaceshipHealth());
		progressBar.setStringPainted(true);
		spaceshipStatus.add(progressBar);
		spaceshipHealth = progressBar;

		JLabel lblShieldLevel = new JLabel("Shield Level ");
		lblShieldLevel.setFont(defaultHeaderFont);
		lblShieldLevel.setForeground(Color.GRAY);
		lblShieldLevel.setBounds(30, 437, 165, 30);
		spaceshipStatus.add(lblShieldLevel);

		JLabel spaceshipIcon = new JLabel("");
		spaceshipIcon.setBounds(12, 12, 971, 396);
		spaceshipIcon.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/spaceship.png")));
		spaceshipStatus.add(spaceshipIcon);

		/// SPACESHIP PAGE END
		/// SPACESHIP PAGE END
		/// SPACESHIP PAGE END
		
		/// OUTPOST PAGE START
		/// OUTPOST PAGE START
		/// OUTPOST PAGE START

		Font outpostHeaderFont = new Font("Quantico", Font.PLAIN, 15);
		Font outpostItemDescFont = new Font("Quantico", Font.PLAIN, 13);
		JPanel VisitOutpost = new JPanel();
		VisitOutpost.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Visit Outpost", null, VisitOutpost, null);
		VisitOutpost.setLayout(null);

		JLabel lblItemsOnSale = new JLabel("Items on sale:");
		lblItemsOnSale.setForeground(Color.GRAY);
		lblItemsOnSale.setBackground(Color.GRAY);
		lblItemsOnSale.setFont(outpostHeaderFont);
		lblItemsOnSale.setBounds(20, 12, 121, 34);
		VisitOutpost.add(lblItemsOnSale);

		JLabel lblItemSaleName = new JLabel("Name");
		lblItemSaleName.setForeground(Color.GRAY);
		lblItemSaleName.setBackground(Color.GRAY);
		lblItemSaleName.setBounds(x, 62, 66, 15);
		lblItemSaleName.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSaleName);

		JLabel lblItemSaleType = new JLabel("Type");
		lblItemSaleType.setForeground(Color.GRAY);
		lblItemSaleType.setBackground(Color.GRAY);
		lblItemSaleType.setBounds(132, 62, 66, 15);
		lblItemSaleType.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSaleType);

		JLabel lblItemSalePrice = new JLabel("Price");
		lblItemSalePrice.setForeground(Color.GRAY);
		lblItemSalePrice.setBackground(Color.GRAY);
		lblItemSalePrice.setBounds(215, 62, 66, 15);
		lblItemSalePrice.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSalePrice);

		JLabel lblItemSaleHeals = new JLabel("Heals");
		lblItemSaleHeals.setForeground(Color.GRAY);
		lblItemSaleHeals.setBackground(Color.GRAY);
		lblItemSaleHeals.setBounds(293, 62, 66, 15);
		lblItemSaleHeals.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSaleHeals);

		JLabel lblItemSaleFills = new JLabel("Fills");
		lblItemSaleFills.setForeground(Color.GRAY);
		lblItemSaleFills.setBackground(Color.GRAY);
		lblItemSaleFills.setBounds(371, 62, 66, 15);
		lblItemSaleFills.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSaleFills);

		JLabel lblItemSaleCuresPlague = new JLabel("Cures Plague");
		lblItemSaleCuresPlague.setForeground(Color.GRAY);
		lblItemSaleCuresPlague.setBackground(Color.GRAY);
		lblItemSaleCuresPlague.setBounds(449, 62, 106, 15);
		lblItemSaleCuresPlague.setFont(outpostHeaderFont);
		VisitOutpost.add(lblItemSaleCuresPlague);

		/// OUTPOST PAGE END
		/// OUTPOST PAGE END
		/// OUTPOST PAGE END

		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START
		/// ITEM DESCRIPTIONS START

		int itemDescription_y = 90;

		JLabel itemName = new JLabel("");
		itemName.setForeground(Color.WHITE);
		itemNames = itemName;
		itemName.setBounds(x, itemDescription_y, 106, 97);
		itemName.setFont(outpostItemDescFont);
		VisitOutpost.add(itemName);

		JLabel itemPrice = new JLabel("");
		itemPrice.setForeground(Color.WHITE);
		itemPrices = itemPrice;
		itemPrice.setBounds(215, itemDescription_y, 66, 97);
		itemPrice.setFont(outpostItemDescFont);
		VisitOutpost.add(itemPrice);

		JLabel itemHeal = new JLabel("");
		itemHeal.setForeground(Color.WHITE);
		itemHealings = itemHeal;
		itemHeal.setBounds(293, itemDescription_y, 66, 97);
		itemHeal.setFont(outpostItemDescFont);
		VisitOutpost.add(itemHeal);

		JLabel itemFill = new JLabel("");
		itemFill.setForeground(Color.WHITE);
		itemFills = itemFill;
		itemFill.setBounds(371, itemDescription_y, 66, 97);
		itemFill.setFont(outpostItemDescFont);
		VisitOutpost.add(itemFill);

		JLabel itemCure = new JLabel("");
		itemCure.setForeground(Color.WHITE);
		itemCures = itemCure;
		itemCure.setBounds(449, itemDescription_y, 80, 97);
		itemCure.setFont(outpostItemDescFont);
		VisitOutpost.add(itemCure);

		JLabel itemType = new JLabel("");
		itemType.setForeground(Color.WHITE);
		itemType.setBounds(132, 90, 66, 97);
		itemType.setFont(outpostItemDescFont);
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
		lblShoppingBag.setFont(outpostHeaderFont);
		VisitOutpost.add(lblShoppingBag);

		int purchase_y = 258;

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setBorder(null);
		btnPurchase.setForeground(Color.WHITE);
		btnPurchase.setBackground(Color.DARK_GRAY);
		btnPurchase.setFont(outpostHeaderFont);
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
		lblTotalPrice.setFont(outpostHeaderFont);
		VisitOutpost.add(lblTotalPrice);

		JLabel lblShoppingBagPrice = new JLabel("price");
		lblShoppingBagPrice.setForeground(Color.WHITE);
		lblShoppingBagPrice.setText("$0");
		lblShoppingBagPrice.setBounds(715, purchase_y, 106, 25);
		lblShoppingBagPrice.setFont(outpostHeaderFont);
		VisitOutpost.add(lblShoppingBagPrice);
		cartPrice = lblShoppingBagPrice;

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

		JLabel lblTotalShipPiecesouSelected = new JLabel("You selected:");
		lblTotalShipPiecesouSelected.setForeground(Color.GRAY);
		lblTotalShipPiecesouSelected.setBackground(Color.GRAY);
		lblTotalShipPiecesouSelected.setBounds(selected_x, y, 106, 34);
		VisitOutpost.add(lblTotalShipPiecesouSelected);

		int spinnerMin = 0;
		int spinnerMax = 50;
		int spinnerStep = 1;
		int spinnerInitValue = 0;
		SpinnerModel model = new SpinnerNumberModel(spinnerInitValue, spinnerMin, spinnerMax, spinnerStep);
		JSpinner spinner = new JSpinner(model);
		spinner.setBackground(Color.DARK_GRAY);
		spinner.setBounds(selected_x, 413, 66, 45);
		VisitOutpost.add(spinner);

		JLabel lblSelecteditem = new JLabel("");
		lblSelecteditem.setForeground(Color.GRAY);
		lblSelecteditem.setBackground(Color.GRAY);
		lblSelecteditem.setBounds(selected_x, 282, 106, 106);
		VisitOutpost.add(lblSelecteditem);
		selectedItem = lblSelecteditem;

		JButton btnAddToCart = new JButton("Add to cart");
		btnAddToCart.setBorder(null);
		btnAddToCart.setForeground(Color.WHITE);
		btnAddToCart.setBackground(Color.DARK_GRAY);
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

		JLabel lblTotalShipPiecesourInventory = new JLabel("Your inventory:");
		lblTotalShipPiecesourInventory.setForeground(Color.GRAY);
		lblTotalShipPiecesourInventory.setBackground(Color.GRAY);
		lblTotalShipPiecesourInventory.setFont(outpostHeaderFont);
		lblTotalShipPiecesourInventory.setBounds(shopping_x, 310, 121, 34);
		VisitOutpost.add(lblTotalShipPiecesourInventory);

		JLabel lblCurrentInventory = new JLabel("");
		lblCurrentInventory.setForeground(Color.WHITE);
		lblCurrentInventory.setBackground(Color.GRAY);
		lblCurrentInventory.setFont(outpostItemDescFont);
		lblCurrentInventory.setBounds(shopping_x, 356, 383, 229);
		VisitOutpost.add(lblCurrentInventory);
		currInventory = lblCurrentInventory;

		// INVENTORY ITEM END
		// INVENTORY ITEM END
		// INVENTORY ITEM END

		JButton endDayButton = new JButton("End day");
		endDayButton.setFont(new Font("Quantico", Font.PLAIN, 20));
		endDayButton.setBorder(null);
		endDayButton.setBackground(Color.DARK_GRAY);
		endDayButton.setForeground(Color.WHITE);
		endDayButton.addActionListener(new ActionListener() {
			/**
			 * <<auto generated javadoc comment>>
			 * @param arg0 <<Param Desc>>
			 */
			public void actionPerformed(ActionEvent arg0) {
				engine.endDay();
				if (engine.hasGameEnded()) {
					finishedWindow();
					return;
				}
				if (engine.getCurrDay() <= engine.getGameLength()) {
					startDay();
					engine.updateCrewMemberStatus();
					refreshPage();
					refreshSpaceshipPage();
					refreshCrewStatusPage();
					refreshCrewButtons(commitActions);
				}
			}
		});

		Font footerGameStateFont = new Font("Quantico", Font.PLAIN, 15);
		int bottomPanel_y = 660;
		int bottomPanel_x = 20;
		int bottomPanelCol2_x = 200;
		int bottomPanelCol3_x = 350;

		endDayButton.setBounds(860, bottomPanel_y + 30, 114, 25);
		frmCommandCenter.getContentPane().add(endDayButton);

		JLabel lblShipPiecesFound = new JLabel("Ship Pieces Found:");
		lblShipPiecesFound.setForeground(Color.LIGHT_GRAY);
		lblShipPiecesFound.setFont(footerGameStateFont);
		lblShipPiecesFound.setBounds(bottomPanel_x, bottomPanel_y, 136, 25);
		frmCommandCenter.getContentPane().add(lblShipPiecesFound);

		JLabel lblX = new JLabel("x");
		lblX.setForeground(Color.LIGHT_GRAY);
		lblX.setFont(footerGameStateFont);
		lblX.setText("0");
		lblX.setBounds(bottomPanelCol2_x, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblX);
		currShipPieces = lblX;

		JLabel lblOutOf = new JLabel("out of");
		lblOutOf.setForeground(Color.LIGHT_GRAY);
		lblOutOf.setFont(footerGameStateFont);
		lblOutOf.setBounds(bottomPanelCol2_x + 25, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblOutOf);

		JLabel lblTotalShipPieces = new JLabel("y");
		lblTotalShipPieces.setForeground(Color.LIGHT_GRAY);
		lblTotalShipPieces.setFont(footerGameStateFont);
		lblTotalShipPieces.setBounds(bottomPanelCol2_x + 75, bottomPanel_y, 55, 25);
		frmCommandCenter.getContentPane().add(lblTotalShipPieces);
		lblTotalShipPieces.setText(String.valueOf(engine.getShipPieces()));

		JLabel lblCurrentDay = new JLabel("Current Day:");
		lblCurrentDay.setForeground(Color.LIGHT_GRAY);
		lblCurrentDay.setFont(footerGameStateFont);
		lblCurrentDay.setBounds(bottomPanel_x, bottomPanel_y + 30, 99, 25);
		frmCommandCenter.getContentPane().add(lblCurrentDay);

		JLabel lblCurrentDayCount = new JLabel("x");
		lblCurrentDayCount.setForeground(Color.LIGHT_GRAY);
		lblCurrentDayCount.setFont(footerGameStateFont);
		lblCurrentDayCount.setText("1");
		lblCurrentDayCount.setBounds(bottomPanelCol2_x, bottomPanel_y + 30, 55, 25);
		frmCommandCenter.getContentPane().add(lblCurrentDayCount);
		currDay = lblCurrentDayCount;

		JLabel label_9 = new JLabel("out of");
		label_9.setForeground(Color.LIGHT_GRAY);
		label_9.setBounds(bottomPanelCol2_x + 25, bottomPanel_y + 30, 55, 25);
		label_9.setFont(footerGameStateFont);
		frmCommandCenter.getContentPane().add(label_9);

		JLabel lblGameLengthTotal = new JLabel("y");
		lblGameLengthTotal.setForeground(Color.LIGHT_GRAY);
		lblGameLengthTotal.setBounds(bottomPanelCol2_x + 75, bottomPanel_y + 30, 55, 25);
		lblGameLengthTotal.setFont(footerGameStateFont);
		frmCommandCenter.getContentPane().add(lblGameLengthTotal);
		lblGameLengthTotal.setText(String.valueOf(engine.getGameLength()));

		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setForeground(Color.LIGHT_GRAY);
		lblMoney.setBounds(bottomPanelCol3_x, bottomPanel_y, 64, 25);
		lblMoney.setFont(footerGameStateFont);
		frmCommandCenter.getContentPane().add(lblMoney);

		JLabel lblCurrentMoney = new JLabel("$0");
		lblCurrentMoney.setForeground(Color.LIGHT_GRAY);
		lblCurrentMoney.setFont(footerGameStateFont);
		lblCurrentMoney.setBounds(bottomPanelCol3_x + 80, bottomPanel_y, 99, 25);
		frmCommandCenter.getContentPane().add(lblCurrentMoney);
		currMoney = lblCurrentMoney;

		JLabel lblRadar = new JLabel("Radar:");
		lblRadar.setForeground(Color.LIGHT_GRAY);
		lblRadar.setBounds(bottomPanelCol3_x, bottomPanel_y + 30, 64, 25);
		lblRadar.setFont(footerGameStateFont);
		frmCommandCenter.getContentPane().add(lblRadar);

		JLabel lblRadarPlanetStatus = new JLabel("");
		lblRadarPlanetStatus.setForeground(Color.LIGHT_GRAY);
		lblRadarPlanetStatus.setBounds(bottomPanelCol3_x + 80, bottomPanel_y + 30, 220, 25);
		lblRadarPlanetStatus.setFont(footerGameStateFont);
		frmCommandCenter.getContentPane().add(lblRadarPlanetStatus);
		
		radarPlanetStatus = lblRadarPlanetStatus;
		
		JMenuBar menuBar = new JMenuBar();
		frmCommandCenter.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				engine.saveGameState();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnNewMenu.add(mntmAbout);

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
					refreshCrewButtons(commitActions);
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

		refreshCrewStatusPage();
		refreshPage();

	}
}

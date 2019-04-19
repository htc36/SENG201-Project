package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEngine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

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
    
    /**
     * Create the application.
     */
    public CommandCenter(GameEngine engine, GameGUI game) {
        this.engine = engine;
        this.game = game;
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

    private void getItemDescription(String itemName) {
        String itemNames = "<html>";
        String itemTypes = "<html>";
        String itemHealings = "<html>";
        String itemPrices = "<html>";
        String itemCures = "<html>";
        String itemFills = "<html>";

        int numNewlines = 2;
        
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
           
        itemNames += "</html>";
        itemTypes += "</html>";
        itemHealings += "</html>";
        itemPrices += "</html>";
        itemCures += "</html>";
        itemFills += "</html>";

        this.itemNames.setText(itemNames);
        this.itemTypes.setText(itemTypes);
        this.itemHealings.setText(itemHealings);
        this.itemPrices.setText(itemPrices);
        this.itemCures.setText(itemCures);
        this.itemFills.setText(itemFills);
    }

    public void showItemDetails(String item) {
    	selectedItem.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/medic.png")));
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

        JPanel spaceshipStatus = new JPanel();

        spaceshipStatus.setBackground(Color.WHITE);
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
        
        JLabel lblBg = new JLabel("bg");
        lblBg.setIcon(new ImageIcon(CommandCenter.class.getResource("/img/black16.jpg")));
        lblBg.setBounds(0, 0, 995, 621);
        spaceshipStatus.add(lblBg);

        JPanel VisitOutpost = new JPanel();
        tabbedPane.addTab("Visit Outpost", null, VisitOutpost, null);
        VisitOutpost.setLayout(null);

        JLabel lblItemsOnSale = new JLabel("Items on sale:");
        lblItemsOnSale.setBounds(12, 12, 223, 34);
        VisitOutpost.add(lblItemsOnSale);

        JLabel lblName_1 = new JLabel("Name");
        lblName_1.setBounds(12, 62, 66, 15);
        VisitOutpost.add(lblName_1);

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

        JLabel label_11 = new JLabel("");
        itemNames = label_11;
        label_11.setBounds(12, 91, 106, 97);
        VisitOutpost.add(label_11);

        JLabel label_12 = new JLabel("");
        itemPrices = label_12;
        label_12.setBounds(215, 89, 66, 97);
        VisitOutpost.add(label_12);

        JLabel label_13 = new JLabel("");
        itemHealings = label_13;
        label_13.setBounds(293, 89, 66, 97);
        VisitOutpost.add(label_13);

        JLabel label_14 = new JLabel("");
        itemFills = label_14;
        label_14.setBounds(371, 89, 66, 97);
        VisitOutpost.add(label_14);

        JLabel label_15 = new JLabel("");
        itemCures = label_15;
        label_15.setBounds(449, 89, 80, 97);
        VisitOutpost.add(label_15);

        JLabel label_16 = new JLabel("Types");
        label_16.setBounds(132, 62, 106, 15);
        VisitOutpost.add(label_16);

        JLabel label_17 = new JLabel("");
        label_17.setBounds(132, 91, 66, 97);
        VisitOutpost.add(label_17);
        itemTypes= label_17;
        
        JLabel lblShoppingBag = new JLabel("Your shopping cart:");
        lblShoppingBag.setBounds(598, 12, 223, 34);
        VisitOutpost.add(lblShoppingBag);
        
        JLabel label_18 = new JLabel("</html>");
        label_18.setBounds(598, 91, 223, 338);
        VisitOutpost.add(label_18);
        
        JLabel lblYouSelected = new JLabel("You selected:");
        lblYouSelected.setBounds(12, 174, 106, 34);
        VisitOutpost.add(lblYouSelected);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(12, 200, 66, 45);
        VisitOutpost.add(spinner);
        
        JLabel lblSelecteditem = new JLabel("selectedItem");
        lblSelecteditem.setBounds(132, 154, 106, 106);
        VisitOutpost.add(lblSelecteditem);
        selectedItem = lblSelecteditem;
        
        JButton btnPurchase = new JButton("Purchase");
        btnPurchase.setBounds(852, 538, 131, 25);
        VisitOutpost.add(btnPurchase);
        
        JLabel lblTotalPrice = new JLabel("Total price:");
        lblTotalPrice.setBounds(600, 538, 106, 25);
        VisitOutpost.add(lblTotalPrice);
        
        JLabel lblPrice_1 = new JLabel("price");
        lblPrice_1.setBounds(715, 538, 106, 25);
        VisitOutpost.add(lblPrice_1);
        
        // ITEM ICONS START

        int consumablesIconSize = 80;
        
        JButton btnNewButton_1 = new JButton("f1");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("Brownie");
        	}
        });
        btnNewButton_1.setBounds(18, 274, 100, 100);
        VisitOutpost.add(btnNewButton_1);
        
        JButton btnF = new JButton("f2");
        btnF.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnF.setBounds(138, 272, 100, 100);
        VisitOutpost.add(btnF);
        
        JButton btnF_1 = new JButton("f3");
        btnF_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnF_1.setBounds(253, 274, 100, 100);
        VisitOutpost.add(btnF_1);
        
        JButton btnF_2 = new JButton("f4");
        btnF_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnF_2.setBounds(18, 386, 100, 100);
        VisitOutpost.add(btnF_2);
        
        JButton btnF_3 = new JButton("f5");
        btnF_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnF_3.setBounds(135, 386, 100, 100);
        VisitOutpost.add(btnF_3);
        
        JButton btnF_4 = new JButton("f6");
        btnF_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnF_4.setBounds(253, 386, 100, 100);
        VisitOutpost.add(btnF_4);
        
        JButton btnM_2 = new JButton("m3");
        btnM_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnM_2.setBounds(253, 509, 100, 100);
        VisitOutpost.add(btnM_2);
        
        JButton btnM = new JButton("m1");
        btnM.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnM.setBounds(12, 509, 100, 100);
        VisitOutpost.add(btnM);
        
        JButton btnM_1 = new JButton("m2");
        btnM_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		showItemDetails("f1");
        	}
        });
        btnM_1.setBounds(129, 509, 100, 100);
        VisitOutpost.add(btnM_1);

        // ITEM ICONS END
        
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
        btnNewButton.setBounds(898, 673, 114, 25);
        frmCommandCenter.getContentPane().add(btnNewButton);

        JLabel lblShipPiecesFound = new JLabel("Ship Pieces Found");
        lblShipPiecesFound.setBounds(12, 673, 136, 25);
        frmCommandCenter.getContentPane().add(lblShipPiecesFound);

        JLabel lblCurrentDay = new JLabel("Current Day");
        lblCurrentDay.setBounds(359, 673, 99, 25);
        frmCommandCenter.getContentPane().add(lblCurrentDay);

        JLabel lblX = new JLabel("x");
        lblX.setText("0");
        lblX.setBounds(160, 673, 55, 25);
        frmCommandCenter.getContentPane().add(lblX);
        currShipPieces = lblX;

        JLabel lblOutOf = new JLabel("out of");
        lblOutOf.setBounds(186, 673, 55, 25);
        frmCommandCenter.getContentPane().add(lblOutOf);

        JLabel lblY = new JLabel("y");
        lblY.setBounds(241, 673, 55, 25);
        frmCommandCenter.getContentPane().add(lblY);
        lblY.setText(String.valueOf(engine.getShipPieces()));

        JLabel label_8 = new JLabel("x");
        label_8.setText("1");
        label_8.setBounds(456, 673, 55, 25);
        frmCommandCenter.getContentPane().add(label_8);
        currDay = label_8;

        JLabel label_9 = new JLabel("out of");
        label_9.setBounds(482, 673, 55, 25);
        frmCommandCenter.getContentPane().add(label_9);

        JLabel label_10 = new JLabel("y");
        label_10.setBounds(537, 673, 55, 25);
        frmCommandCenter.getContentPane().add(label_10);
        label_10.setText(String.valueOf(engine.getGameLength()));

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
    }
}

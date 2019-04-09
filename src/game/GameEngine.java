package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consumable.*;
import unit.*;
import crew.Crew;
import outpost.Outpost;
import random_events.AlienPirates;
import random_events.SpacePlague;

public class GameEngine {

    private int gameLength;
    private int shipPieces;
    private ArrayList<String> crewMemberTypes;
    private ArrayList<CrewMember> crewMembers;
    private Spaceship ship;
    private Crew crew;
    private Outpost outpost;
    private int currDay;
    private int moneySpentInCurrSession;

    public GameEngine() {
        currDay = 1;

        crewMemberTypes = new ArrayList<>();
        crewMemberTypes.add("medic");
        crewMemberTypes.add("explorer");
        crewMemberTypes.add("hungus");
        crewMemberTypes.add("sleeper");
        crewMemberTypes.add("hardWorker");
        crewMemberTypes.add("builder");

        Food f1 = new Brownie();
        Food f2 = new FriedRice();
        Food f3 = new CockroachPowder();
        Food f4 = new SpaceCake();
        Food f5 = new TikkaMasala();
        Food f6 = new Hotbot();

        MedicalSupply m1 = new PolyJuice();
        MedicalSupply m2 = new AlienSpinalFluid();
        MedicalSupply m3 = new Vaccine();

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        outpost = new Outpost(c);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param item <<Param Description>>
     */
    public void addCrewConsumable(Consumable item) {
        crew.addConsumable(item);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Description>>
     * @return int <<Return Description>>
     */
    public int getInputNumDays(Scanner reader) {
        int numOfDays = 0;
        do {
            numOfDays = reader.nextInt();
            if (!isValidNumOfDays(numOfDays)) {
            	System.out.println("Please enter a value between 3 and 10");
            }
        } while (!isValidNumOfDays(numOfDays));

        return numOfDays;
    }

    public boolean isValidNumOfDays(int numOfDays) {
        return numOfDays <= 10 && numOfDays >= 3;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Description>>
     * @return String <<Return Description>>
     */
    public String getInputSpaceshipName(Scanner reader) {
        String name = reader.next();
        return name.replaceAll("\\s","");
    }

    public int getCrewAmount(Scanner reader) {
        int amount = 0;
        do {
            System.out.println("Crew amount must be between 2 and 4");
            System.out.print("> ");
            amount = reader.nextInt();
        }
        while (amount < 2 || amount > 4);

        return amount;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param length <<Param Description>>
     */
    public void setGameLength(int length) {
        gameLength = length;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param name <<Param Description>>
     */
    public void setupSpaceship(String name) {
        ship = new Spaceship(name);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void setupCrew() {
        crew = new Crew(crewMembers, ship);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void setShipPieces() {
        shipPieces = calculateShipPieces(gameLength);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void getInputName() {

    }

    public boolean isCrewInfoValid(String crewInfo){
        Pattern p = Pattern.compile("\\w+-\\w+");
        Matcher m = p.matcher(crewInfo);
        return m.find();
    }

    public void setCrewMembers(int crewNumbers, Scanner reader) {
        crewMembers = new ArrayList<>();


        for (int i = 0; i < crewNumbers; i++) {
            String crewType = "";
            String memberName = "";
            String crewInfo = "";
            do {
                System.out.print("Input crew name followed by their type");
                System.out.print(" (" + (crewNumbers - i) + " to go)\n");
                do {
                    System.out.print("> ");
                    crewInfo = reader.next();
                    if (!isCrewInfoValid(crewInfo)){
                        System.out.println("Invalid input");
                    }
                } while(!isCrewInfoValid(crewInfo));
                String[] splitter = crewInfo.split("-");
                memberName = splitter[0];
                crewType = splitter[1].toLowerCase();
                if (!crewMemberTypes.contains(crewType)){
                    System.out.println("Invalid Crew Type");
                }

            } while (!crewMemberTypes.contains(crewType));

            switch(crewType) {
                case "medic":
                    crewMembers.add(new Medic(memberName));
                    break;
                case "explorer":
                    crewMembers.add(new Explorer(memberName));
                    break;
                case "builder":
                    crewMembers.add(new Builder(memberName));
                    break;
                case "sleeper":
                    crewMembers.add(new Sleeper(memberName));
                    break;
                case "hardworker":
                    crewMembers.add(new HardWorker(memberName));
                    break;
                case "hungus":
                    crewMembers.add(new Hungus(memberName));
                    break;
            }
        }
    }

    /**
     * <<auto generated javadoc comment>>
     * @param days <<Param Description>>
     * @return int <<Return Description>>
     */
    public int calculateShipPieces(int days) {
        return days * 2 / 3;
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void startDay() {
        // We start the day with random events occuring, what fun!
        Random rand = new Random();
        int randomEvent = rand.nextInt(2);
        switch (randomEvent) {
            case 1:
                AlienPirates.causeDamage(crew); break;
            case 0:
                SpacePlague.causeDamage(crew);break;
        }
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void endDay() {
        // by the end of the day, everyone went to bed and get ready
        // for the next day. Refreshes all the crew members action to 2
        for (CrewMember c : crewMembers) {
            c.refreshActions(2);
        }
        currDay++;
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void run() {

    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void viewCrewMemberStatus() {
        typePrint();
        typePrint("*** Crew Members Status ***", 50);
        typePrint();
        typePrint("        Name       Type Health Luck Plagued Hunger Fatique Actions");
        typePrint("--------------------------------------------------------------------");
        String crewStatus = crew.getCrewMemberStatus();
        typePrint(crewStatus);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void viewSpaceshipStatus() {
        typePrint();
        typePrint("*** Spaceship Status ***", 50);
        typePrint();
        typePrint("        Name  Health");
        typePrint("--------------------");
        typePrint(ship.toString());
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void visitOutpost() {
    	moneySpentInCurrSession = 0;
        typePrint();
        typePrint("*** Welcome to the outpost ***", 50);
        typePrint();
        typePrint("Clerk: Don't forget to place your items in the bagging area", 30);
        typePrint("Clerk: Here are the things on sale today :}");
        typePrint();
        typePrint("    Type           Name Price Heal Fill Cures_Plague");
        typePrint("    ------------------------------------------------");
        for (String s : outpost.saleProductsToString().split("\n")) {
            typePrint("    " + s);
        }
        //typePrint(outpost.saleProductsToString());
        typePrint();
        typePrint("    Your inventory:");
        String currInventory = crew.consumablesToString();
        typePrint(currInventory);
        typePrint();
        int crewMoney = crew.getMoney();
        typePrint(String.format("    You have $%3d", crewMoney));
        typePrint();
        if (crewMoney < 50) {
            typePrint("Clerk: Tight on money, eh? What do you want today?");
        }
        typePrint("Clerk: You know the drill, say the amount and the item");
        typePrint("Clerk: Like 2xBanana");
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Description>>
     * @return String <<Return Description>>
     */
    public String getInputShoppingList(Scanner reader) {
        String errMsg = "Clerk: Sorry I didn't quite catch that, try again?";
        String allQueries = "";
        String query = "";
        
        while (true) {
            System.out.print("> ");
            query = reader.next();
            if (isValidQuery(query)) {
            	addItemToShoppingBag(query, reader);
            	
	
            	}
                
            else {
                if (query.equals("done")) {
                    break;
                }
                System.out.println(errMsg);
            }
        }

        return allQueries;
    }
    

    /**
     * <<auto generated javadoc comment>>
     * @param query <<Param Description>>
     * @return boolean <<Return Description>>
     */
    public boolean isValidQuery(String query) {
        Pattern p = Pattern.compile("\\d+x\\w+");
        Matcher m = p.matcher(query);
        if (m.find()) {
            String itemName = query.split("x")[1];
            return outpost.hasItemInStock(itemName);
        }

        return false;
    }
    public boolean enoughMoneyToPurchase(String query) {
    	int itemPrice = 0;
    	int amount = 0;
        String itemName = "";
        
        amount = Integer.valueOf(query.split("x")[0]);
        itemName = query.split("x")[1];
        for (int i = 0; i < amount; i ++ ) {
        	itemPrice += outpost.getPrice(itemName);
         }
        moneySpentInCurrSession += itemPrice;
        System.out.println(moneySpentInCurrSession);
        if (moneySpentInCurrSession <= crew.getMoney()) {
        	return true;
        }
        else {
        	moneySpentInCurrSession -= itemPrice;
        	return false;
        }

    }
    
    

    /**
     * <<auto generated javadoc comment>>
     * @param queries <<Param Description>>
     */
    public void addItemToShoppingBag(String query, Scanner reader) {
        int amount = 0;
        String itemName = "";
        amount = Integer.valueOf(query.split("x")[0]);
        itemName = query.split("x")[1];
        for (int i = 0; i < amount; i ++ ) {
        	outpost.addItemToShoppingBag(itemName);
        }
        while (outpost.getTotalPrice() > crew.getMoney()) {
        
        	System.out.println("You do not have enough money to purchase all the items");
        	System.out.println("Your shopping list");
        	viewShoppingBag();
        	System.out.println("Pick item to remove");
        	String removedItem = reader.next();
        	while (outpost.hasItemInShoppingBag(removedItem)) {
        		outpost.removeItemFromShoppingBag(removedItem);
        	}
        	
        }
        System.out.println("Good to go your shooping list is now:");
        viewShoppingBag();
        }
        	
        
    
    

    /**
     * <<auto generated javadoc comment>>
     */
    public void viewShoppingBag() {
        typePrint(outpost.shoppingBagToString());
        typePrint("Total price: $" + String.valueOf(outpost.getTotalPrice()));
        
    }

    /**
     * Prints out String message as if it was typed
     * the delay between each char can be set with param
     * delay (in miliseconds)
     * @param message the message it prints
     * @param delay delay between each chars
     */
    public void typePrint(String message, int delay) {
        int msgLength = message.length();
        if (msgLength == 0) {
            System.out.println();
            return;
        }

        int i;
        for (i = 0; i < msgLength; i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {};
        }

        if (message.charAt(--i) != '\n') {
            System.out.println();
        }
    }

    /**
     * Prints out String message as if it was typed
     * the delay is set to 10 by default
     * @param message the message it prints
     */
    public void typePrint(String message) {
        int msgLength = message.length();
        if (msgLength == 0) {
            System.out.println();
            return;
        }
        int i;

        for (i = 0; i < msgLength; i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {};
        }

        if (message.charAt(--i) != '\n') {
            System.out.println();
        }
    }

    /**
     * Wrapper around System.out.println() for consistencies
     */
    public void typePrint() {
        System.out.println();
    }

    public void enterToContinue(Scanner reader) {
    	
    	System.out.println("\nPress Enter to exit to homepage");
        try {
			System.in.read();
		} catch (IOException e) {
			
		}
    }
    public void commitActionPage(Scanner reader) {
    	System.out.println("Welcome to the action center");
    	System.out.println("Select crew member to complete action with");
    	System.out.println(crew.getCrewMemberStatus());
        typePrint("Index        Name       Type Health Luck Plagued Hunger Fatique Actions");
        typePrint("-----------------------------------------------------------------------");
        for (int i = 0; i < crewMembers.size(); i++) {
        	System.out.print(i +"    ");
        	System.out.println(crewMembers.get(i));
        }
        	
    	
    }
   

    public void homePage(Scanner reader) {
    	boolean quit = false;
        do {
        	typePrint("Welcome to the homepage");
        	typePrint("Press 1 to view crew status");
        	typePrint("Press 2 to view ship status");
        	typePrint("Press 3 commit action");
        	typePrint("Press 4 to visit Outpost");
        	typePrint("Press 5 to move to next day");
        	typePrint("Press 6 to end game");

			String name = reader.next();
			System.out.print("\033[H\033[2J");
			System.out.flush();
			switch (name) {
			case "1":
				viewCrewMemberStatus();
				enterToContinue(reader);
				break;
			
			case "2":
				viewSpaceshipStatus();
				enterToContinue(reader);
				break;
			case "3":
				commitActionPage(reader);
				break;
			case "4":
				visitOutpost();
				String queries = getInputShoppingList(reader);
				
				viewShoppingBag();

				outpost.purchaseItems(crew);
				enterToContinue(reader);
				break;
			case "5":
				endDay();
				if (gameLength - currDay == -1) {
					System.out.println("You reached your day limit thanks for playing");
					quit = true;
				}
				else {
				System.out.println("You are now on day " + currDay + " (" + (gameLength - currDay) + " day(s) till end of game)");
				}
				
				enterToContinue(reader);
				break;
			case "6":
				
				System.out.println("Thanks for playing");
				quit = true;
			}
		} while (quit == false);  

       

    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        GameEngine g = new GameEngine();
        Scanner reader = new Scanner(System.in);
        System.out.print("Spaceship name: ");
        g.setupSpaceship("ghfgh");
        System.out.print("Number of days: ");
        g.setGameLength(5);
        g.setShipPieces();
        System.out.println("Number of crew members (2-4)?");
        g.setCrewMembers(g.getCrewAmount(reader), reader);
        g.setupCrew();
        //g.addCrewConsumable(new Food("Spaghetti", 10, 10, 10));
        //g.addCrewConsumable(new Food("Banana", 10, 10, 10));
        //g.addCrewConsumable(new MedicalSupply("Vape", 10, 10, false));
        //g.addCrewConsumable(new MedicalSupply("Eyeballs", 10, 10, true));
        g.homePage(reader);
        //g.visitOutpost();
        //String queries = g.getInputShoppingList(reader);
        //g.addItemToShoppingBag(queries);
        //g.viewShoppingBag();


        reader.close();
    }


}

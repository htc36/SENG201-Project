package game;

import static game.Utils.typePrint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crew.InsufficientItemInStock;
import game.InsufficientFundException;

public class Game {
    private Scanner reader;
    private GameEngine g;

    /**
     * Prints the list of selectable crew members
     */
    public static void printCrewMemberList() {
        System.out.println("Here are the possible crew member choices");
        System.out.println("--> Actioneer - has 3 actions instead of the usual 2");
        System.out.println("--> Builder - repairs spaceship faster");
        System.out.println("--> Explorer - has higher chance of finding ship pieces");
        System.out.println("--> Hungus - more immune to hunger");
        System.out.println("--> Medic - receives less damage");
        System.out.println("--> Sleeper - more immune to fatique");
    }

    /**
     * prints the spaceship status using ASCII characters
     */
    public void viewSpaceshipStatus() {
        Utils.printSpaceshipASCII();
        Utils.printSpaceshipHeader();
        ArrayList<String> s = g.getShipStatus();
        String name = s.get(0);
        String health = s.get(1);
        String template = String.format("%12.12s", name);
        template += String.format("%8s", health);
        typePrint(template);
    }

    /**
     * Prints the crew members in a pretty table
     * @param crewStatus ArrayList of crewStats
     * @param withIndex set to true if index is needed in front
     */
    public void printCrewMembers(ArrayList<ArrayList<String>> crewStatus, boolean withIndex) {
        Utils.printCrewASCII();
        if (withIndex) 
            Utils.printActionCenterHeader();
        else 
            Utils.printCrewStatusHeader();

        int counter = 0;
        for (ArrayList<String> c : crewStatus) {
            String name = c.get(0);
            String health = c.get(1);
            String luck = c.get(2);
            String status = c.get(3);
            String hunger = c.get(4);
            String fatique = c.get(5);
            String actions = c.get(6);
            String type = c.get(7);

            String template = "";
            if (withIndex) {
                template += String.format("%5d", counter);
                counter += 1;
            }
            template += String.format("%12.12s", name);
            template += String.format("%11.11s", type);
            template += String.format("%9s", health); // health stat
            template += String.format("%7s", luck); // luck stat
            template += String.format("%10.1s", status); // has plague
            template += String.format("%9s", hunger); // hunger level
            template += String.format("%10s", fatique); // fatique level
            template += String.format("%10s", actions); // actions

            typePrint(template);
        }
    }

    /**
     * Get the player input for spaceship's name
     * @return String the name of the spaceship
     */
    public String getInputSpaceshipName() {
        System.out.print("> ");
        String name = reader.nextLine();
        return name.replaceAll("\\s","");
    }

    /**
     * Get the player input for the number of days (length of the game)
     * @return int the number of days
     */
    public int getInputNumDays() {
        int numOfDays;
        do {
            numOfDays = getIntegerInput();
            if (!g.isValidNumOfDays(numOfDays)) {
                System.out.println("Please enter a value between 3 and 10");
            }
        } while (!g.isValidNumOfDays(numOfDays));

        return numOfDays;
    }

    /**
     * Prints the main menu for all player's possible actions
     * @return boolean false if the game should end, true if the game should continue
     */
    public boolean printHomePage() {
        Utils.printHomepageHeader();
        typePrint("Found ship pieces: " + g.getFoundShipPieces());
        typePrint();

        System.out.print("> ");
        String name = reader.next();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch (name) {
            case "1":
                printCrewMembers(g.getCrewMemberStatus(), false);
                enterToContinue();
                break;
            case "2":
                viewSpaceshipStatus();
                enterToContinue();
                break;
            case "3":
                commitActionPage();
                enterToContinue();
                break;
            case "4":
                visitOutpost();
                g.clearShoppingBag();
                getInputShoppingList();
                TreeMap<String, Integer> shoppingBag = g.getShoppingBag();
                try {
                    g.purchaseItems(shoppingBag);
                } catch (InsufficientFundException err) {
                    typePrint("You do not have enough funds");
                }
                enterToContinue();
                break;
            case "5":
                return false;
            default:
                typePrint("Spaceship Operator: I can't understand what you're saying cap'n");
                break;
        }
        return true;
    }

    /**
     * Get the crew member details input from the player
     * @param crewNumbers the number of crew members to be inputted
     * @return ArrayList<String> list of produced crew members
     */
    public ArrayList<String> getInputCrewMembers(int crewNumbers) {
        ArrayList<String> crewString = new ArrayList<>();
        ArrayList<String> crewMemberTypes = g.getCrewMemberTypes();
        for (int i = 0; i < crewNumbers; i++) {
            String crewType = "";
            String crewInfo = "";
            do {
                System.out.print("Input crew name followed by their type without spaces");
                System.out.println(" (" + (crewNumbers - i) + " to go)");
                System.out.println("For example:");
                System.out.println("> dora-explorer");
                System.out.println("> ralph-builder");
                System.out.println();
                System.out.print("> ");
                while(true) {
                    crewInfo = reader.nextLine();
                    if (isCrewInfoValid(crewInfo))
                        break;
                }
                String[] splitter = crewInfo.split("-");
                crewType = splitter[1].toLowerCase();
                if (!crewMemberTypes.contains(crewType)){
                    System.out.println("Invalid Crew Type");
                }
                crewString.add(crewInfo);
            } while (!crewMemberTypes.contains(crewType));
        }

        return crewString;
    }

    /**
     * Prints the outpost menu
     */
    public void visitOutpost() {
        Utils.printOutpostHeader();
        ArrayList<ArrayList<String>> outpostProducts = g.getOutpostSaleProducts();
        for (ArrayList<String> item : outpostProducts) {
            String name = item.get(0);
            String price = item.get(1);
            String healingAmount = item.get(2);
            String type = item.get(3);
            String effect = item.get(4);

            String template = "";
            template += type;
            template += String.format("%12.12s", name);
            template += String.format("%6s", price);
            template += String.format("%5s", healingAmount);
            if (effect.equals("T") || effect.equals("F"))
                template += String.format("%18s", effect);
            else
                template += String.format("%5s", effect);
            typePrint("    " + template);
        }

        typePrint();
        typePrint("    Your inventory:");
        ArrayList<ArrayList<String>> currInventory = g.getCrewConsumables();
        printCrewConsumables(currInventory, false);
        typePrint();
        int crewMoney = g.getCrewMoney();
        typePrint(String.format("    You have $%3d", crewMoney));
        typePrint();
        if (crewMoney < 50) {
            typePrint("Clerk: Tight on money, eh? What do you want today?");
        }
        typePrint("Clerk: You know the drill, say the amount and the item");
        typePrint("Clerk: Don't forget to say done when you're finished");
        typePrint("Clerk: Let me refresh your memory just in case");
        typePrint();
        typePrint("Clerk: > 2xBrownie");
        typePrint("Clerk: > 1xSpaceCake");
        typePrint("Clerk: > done");
        typePrint();
    }

    /**
     * Prints the list of crew's consumables in a pretty table format
     * @param consumables list of consumables
     * @param withIndex set to True if index at the front is desired, false otherwise
     */
    public void printCrewConsumables(ArrayList<ArrayList<String>> consumables,
            boolean withIndex) {
        int counter = 0;
        for (ArrayList<String> itemStats : consumables) {
            String template = "";
            if (withIndex) {
                template += String.format("%5s", String.valueOf(counter));
            }
            template += String.format("%3s", itemStats.get(5));
            template += "x";
            template += String.format("%6s", itemStats.get(3)); //type
            template += String.format("%12.12s", itemStats.get(0)); //name
            template += String.format("%6s", itemStats.get(1)); //price
            template += String.format("%5s", itemStats.get(2)); //heal
            if (itemStats.get(4).equals("T") || itemStats.get(4).equals("F"))
                template += String.format("%18s", itemStats.get(4)); //cures
            else
                template += String.format("%5s", itemStats.get(4)); //cures
            typePrint(template);
            counter++;
        }
    }

    /**
     * Prints the list of items in a pretty table
     * @param consumables list of items
     */
    /**
     * Get the player input for the items they are going to buy
     */
    public void getInputShoppingList() {
        String errMsg = "Clerk: Sorry I didn't quite catch that, try again?";
        String allQueries = "";
        String query = "";

        while (true) {
            System.out.print("> ");
            query = reader.next();
            if (isValidQuery(query)) {
                g.addItemToShoppingBag(query);
            }
            else {
                if (query.equals("done")) {
                    break;
                }
                System.out.println(errMsg);
            }
        }
    }

    /**
     * Get the player input for the number of crew members
     * @return int number of crew members
     */
    public int getCrewAmount() {
        int amount = 0;
        do {
            amount = getIntegerInput();
            if (!g.isCrewNumberValid(amount))
                System.out.println("Crew amount must be between 2 and 4");
        } while (!g.isCrewNumberValid(amount));

        return amount;
    }

    /**
     * Start the day inside the game
     */
    public void startDay() {
        enterToContinue();
        int randomEvent = g.getRandomEvent();
        switch (randomEvent) {
            case 1:
                typePrint("Oh no those pesky alien pirates invaded the ship and raided our inventory");
                break;
            case 2:
                typePrint("Houston we have a problem");
                typePrint("Some crew member(s) have been infected with Space Plague");
                break;
        }

        g.updateCrewMemberStatus();
        String template = "WARNING\n";
        template += "Crew members below are hungry or tired\n";
        template += "They will receive more damage when injured\n";
        typePrint(template);
        for (String c : g.getUnhealthyCrewMembers()) {
            typePrint("-> " + c);
        }
        enterToContinue();
        Utils.clearScreen();
        ArrayList<String> deadCrew = g.getDeadCrewMembers();
        if (deadCrew.size() != 0){
            typePrint("Sady there has been a death(s), the following have died");
            for (String name : deadCrew) {
                typePrint(Utils.getDeathMessage() + ": " + name + ", Day ");
            }
            typePrint("Press F to pay respects");
            String respects;
            do {
                respects = reader.next();
                if (!respects.equals("F")){
                    typePrint("Please only F");
                }
            }while (!respects.equals("F"));
            Utils.clearScreen();
        }
    }

    /**
     * Gets an integer input from the player
     * This function will keep on trying until it gets an integer
     * @return int integer from the player
     */
    public int getIntegerInput() {
        while (true) {
            System.out.print("> ");
            String input = reader.next();
            try {
                int inputInt = Integer.valueOf(input);
                return inputInt;
            } catch (NumberFormatException e) {
                typePrint("N: Your input is not an integer");
                typePrint("N: Naughty naughty");
                typePrint();
            }
        }
    }

    /**
     * Prints the list of actions a crew member could do
     */
    public void commitActionPage() {
        System.out.println("Welcome to the action center");
        System.out.println("Select crew member to complete action with \n");

        ArrayList<ArrayList<String>> crewMembers = g.getCrewMemberStatus();

        int membersWithActions = 0;
        for (ArrayList<String> member: crewMembers) {
            int actions = Integer.valueOf(member.get(6));
            if (actions > 0)
                membersWithActions++;
        }

        if (membersWithActions == 0) {
            typePrint("N: All your crews are out of actions");
            typePrint("N: Give them some rest and they'll be back in action tomorrow");
            typePrint("N: Back in action, get it? Heh heh heh");
            typePrint();
            return;
        }

        System.out.println("\nPlease enter index of crew member you want to apply action to");
        printCrewMembers(crewMembers, true);

        int index; 
        do {
            while (true) {
                index = getIntegerInput();
                try {
                    g.selectCrewMember(index);
                    break;
                } catch (IndexOutOfBoundsException err) {
                    typePrint("Oh my that crew member doesn't exist try again");
                    continue;
                }
            } 
            if (!g.selectedCrewHasAction())
                typePrint("Sorry " + g.selectedCrewName() + " does not have any actions");
        } while(!g.selectedCrewHasAction());

        Utils.clearScreen();

        String selectedCrewName = g.selectedCrewName();
        typePrint("Select action to apply to " + selectedCrewName);
        Utils.printActionCenterChoices();

        int selection = getIntegerInput();
        switch(selection) {
            // selected crew consumes consumable
            case 1:
                if (g.getCrewConsumablesCount() == 0) {
                    typePrint();
                    typePrint("N: The captain looks at his empty inventory and thinks");
                    typePrint("N: \"My crew could wait for another day\"");
                    typePrint();
                    break;
                }

                typePrint("Select food to consume:");
                Utils.printActionCommitFoodSelectionHeader();
                ArrayList<ArrayList<String>> crewInventory = g.getCrewConsumables();
                printCrewConsumables(crewInventory, true);

                int chosenItem;
                do {
                    chosenItem = getIntegerInput();
                    if (chosenItem < 0 || chosenItem >= crewInventory.size())
                        typePrint("You can only choose items that are in the list");
                } while (chosenItem < 0 || chosenItem >= crewInventory.size());

                try {
                    g.selectedCrewUseItem(chosenItem);
                } catch (InsufficientItemInStock err) {
                    typePrint(err.getMessage());
                }
                break;
            case 2:
                if (g.selectedCrewGetFatique() == 0){
                    typePrint("What a nice captain, " + selectedCrewName + " has no fatigue, yet you've granted rest");
                } else {
                    typePrint("As we speak ol mate " + selectedCrewName + " is having a well deserved kip");
                }
                g.selectedCrewSleep();
                break;
            case 3:
                if (g.getSpaceshipHealth() == 100)
                    typePrint("We don't have any damage cap, but Jerry let one rip in the dunny so we'll deal to that");
                else
                    typePrint("Nice job, you know what they say healthy ship happy crew");
                g.selectedCrewRepairShield();
                break;
            case 4:
                boolean planetHasPieces = g.planetHasShipPieces();
                boolean foundPieces = g.selectedCrewSearchPlanet();

                typePrint("N: " + selectedCrewName + " searches long and hard", 50);

                if (planetHasPieces) {
                    if (foundPieces) {
                        typePrint(selectedCrewName + " has found a ship piece!");
                        g.incrementFoundShipPieces();
                        typePrint("The crew now has " + g.getFoundShipPieces() + " ship pieces");
                        typePrint();
                        g.planetExtractShipPieces();
                        break;
                    } 
                } 

                if (g.unlucky(20)) { // 20% of finding nothing
                    typePrint("N: and found nothing");
                } else if (g.unlucky(50)) { // or if found something, 50% chance it's item
                    String randomItem = g.crewGetRandomItem();
                    typePrint("N: and found " + randomItem);
                    typePrint("N: Not a ship piece, but still something");
                } else { // 50% chance it's money
                    g.crewAddMoney();
                    typePrint("N: and found money");
                    typePrint("N: Luckily the currency is used all around the universe");
                }
                typePrint();
                break;
            case 5:
                if (!g.isSpaceshipAbleToFly()) {
                    typePrint("Spaceship Operator: Our ship is too damaged to take off sir");
                    typePrint();
                    break;
                }

                int copilotCanditatesCount = crewMembers.size() - 1;

                if (copilotCanditatesCount == 0) {
                    typePrint("N: Everyone looks tired");
                    typePrint("N: Looks like you don't have someone who can copilot");
                    typePrint();
                    break;
                }

                printCrewMembers(crewMembers, true);

                System.out.println();
                int copilotIndex = 0;
                do {
                    try {
                        System.out.println("Choose the copilot index:");
                        copilotIndex = getIntegerInput();
                        g.setCopilot(copilotIndex);
                    } catch (IndexOutOfBoundsException err) {
                        typePrint("Oh my that crew member doesn't exist try again");
                        continue;
                    }

                    if (!g.isValidCopilot(index, copilotIndex)) {
                        typePrint("N: Nuh, uh. Can't choose that one");
                    }

                    if (copilotIndex == index) {
                        typePrint("N: I also wish I could clone myself sometimes");
                    }
                } while (!g.isValidCopilot(index, copilotIndex));

                g.selectedCrewPilotSpaceship();
                if (g.isHitAsteroid()) {
                    Utils.printSpaceshipTravelling(true);
                    g.asteroidCausingDamage();
                } else {
                    Utils.printSpaceshipTravelling(false);
                }

                typePrint("N: You have arrived at planet " + g.getPlanetName());
                String shipPiecePresence = "Spaceship Operator: Our radar has detected ";
                if (!g.planetHasShipPieces()) {
                    shipPiecePresence += "NO ";
                }
                shipPiecePresence += "presence of ship piece here\n";
                typePrint(shipPiecePresence);
                typePrint();
                break;
            default:
                typePrint();
                typePrint("Your selection was not in one of the choices");
                typePrint();
                break;
        }
    }

    /**
     * Get input from the player to press enter to continue
     */
    public void enterToContinue() {
        System.out.println("\n<Enter> to go back to command center");
        try {
            System.in.read();
        } catch (IOException e) {};
        Utils.clearScreen();
    }

    /**
     * Checks if the item query from the player for the outpost is valid
     * @param query query from the player
     * @return boolean true if valid, false otherwise
     */
    public boolean isValidQuery(String query) {
        Pattern p = Pattern.compile("^\\d+x\\w+$");
        Matcher m = p.matcher(query);
        if (m.find()) {
            String itemName = query.split("x")[1];
            int itemCount = Integer.valueOf(query.split("x")[0]);
            if (itemCount < 0) {
                typePrint("Clerk: NO Refunds >:(");
                return false;
            }
            return g.hasOutpostStock(itemName);
        }

        return false;
    }

    /**
     * Runs the game
     */
    public void run() {
        int gameLength = g.getGameLength();
        while (true) {
            int currDay = g.getCurrDay();
            if (g.hasGameEnded()) {
                typePrint("N: The game has ended");
                return;
            }
            if(currDay != 1)
                startDay();
            if (g.hasGameEnded()) {
                typePrint("N: Cap, theres no easy way to put this, everyone is DEAD");
                typePrint("Better luck next time");
                return;
            }
            typePrint("Day " + currDay + " (" + (gameLength - currDay) + " day(s) till end of game)");
            String template = "INFO :: Detected ";
            if (g.planetHasShipPieces())
                template += "NO ";
            template += "presence of a ship piece in this planet";
            typePrint(template);
            boolean shouldRepeat = false;
            do {
                shouldRepeat = printHomePage();
            } while (shouldRepeat);
            g.endDay();
        }
    }

    /**
     * Checks if the crew info from the player input is valid
     * @param crewInfo crew info from the player
     * @return boolean true if valid, false otherwise
     */
    public boolean isCrewInfoValid(String crewInfo){
        Pattern p = Pattern.compile("\\w+-\\w+");
        Matcher m = p.matcher(crewInfo);
        return m.find();
    }

    /**
     * Sets the spaceship with the name specified
     * @param input name of the spaceship
     */
    public void setupSpaceship(String input) {
        g.setupSpaceship(input);
    }

    /**
     * Sets the game length with the days specified
     * @param numOfDays number of days
     */
    public void setGameLength(int numOfDays) {
        g.setGameLength(numOfDays);
        g.setShipPieces();
    }

    /**
     * Close the Scanner object
     */
    public void closeReader() {
        reader.close();
    }

    /**
     * Set the crew members
     * @param crewString string describing the crew members
     */
    public void setCrewMembers(ArrayList<String> crewString) {
        g.setCrewMembers(crewString);
    }

    /**
     * Setup the planets
     */
    public void setupPlanets() {
        g.setupPlanets();
    }

    /**
     * Setup the crew
     */
    public void setupCrew() {
        g.setupCrew();
    }

    /**
     * Constructor for command line application
     */
    public Game() {
        reader = new Scanner(System.in);
        g = new GameEngine();
    }

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        Utils.clearScreen();
        Game game = new Game();
        System.out.println("Spaceship name: ");
        game.setupSpaceship(game.getInputSpaceshipName());
        Utils.clearScreen();
        System.out.println("Number of days: ");
        game.setGameLength(game.getInputNumDays());
        Utils.clearScreen();
        Game.printCrewMemberList();
        System.out.println("\nNumber of crew members (2-4): ");
        game.setCrewMembers(game.getInputCrewMembers(game.getCrewAmount()));
        Utils.clearScreen();
        game.setupPlanets();
        game.setupCrew();
        game.run();
        game.closeReader();
    }


}

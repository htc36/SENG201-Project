package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consumable.*;
import unit.*;
import crew.Crew;
import outpost.Outpost;
import planet.Planet;
import random_events.AlienPirates;
import random_events.SpacePlague;
import static game.Utils.typePrint;

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
    private boolean hasEnded;
    private ArrayList<Planet> planets;
    private int currentPlanetIndex;
    private int foundShipPieces;

    /**
     * <<auto generated javadoc comment>>
     */
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

        hasEnded = false;

        currentPlanetIndex = 0;
        foundShipPieces = 0;

    }

    public void setupPlanets() {
        planets = new ArrayList<>();
        // Make 15 planets, only 6 of them have ship pieces
        String[] planetsWithShipPieces = {"CX1337", 
            "Aiur", "Earth0x02", "RuhRoh", "BobbyBobBob", "Amber"};

        for (int i = 0; i < shipPieces; i++) {
            planets.add(new Planet(planetsWithShipPieces[i], true));
        }

        String[] firstName = {"Auspicious", "Green", "Dumpster"};
        String[] lastName = {"Pie", "Crane", "Bonsai", "Ilama"};
        for (int i = 0; i < firstName.length; i++) {
            for(int j = 0; j < lastName.length; j++) {
                planets.add(new Planet(firstName[i] + lastName[j], false));
                if (planets.size() >= shipPieces * 3) {
                    return;
                }
            }
        }
    }

    /**
     * <<auto generated javadoc comment>>
     * @param item <<Param Desc>>
     */
    public void addCrewConsumable(Consumable item) {
        crew.addConsumable(item);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     * @return int <<Return Desc>>
     */
    public int getInputNumDays(Scanner reader) {
        int numOfDays;
        do {
            numOfDays = getIntegerInput(reader);
            if (!isValidNumOfDays(numOfDays)) {
                System.out.println("[Error] Please enter a value between 3 and 10");
            }
        } while (!isValidNumOfDays(numOfDays));

        return numOfDays;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param numOfDays <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean isValidNumOfDays(int numOfDays) {
        return numOfDays <= 10 && numOfDays >= 3;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     * @return String <<Return Desc>>
     */
    public String getInputSpaceshipName(Scanner reader) {
        String name = reader.next();
        return name.replaceAll("\\s","");
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     * @return int <<Return Desc>>
     */
    public int getCrewAmount(Scanner reader) {
        int amount = 0;
        do {
            System.out.println("Crew amount must be between 2 and 4");
            amount = getIntegerInput(reader);
        } while (amount < 2 || amount > 4);

        return amount;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param length <<Param Desc>>
     */
    public void setGameLength(int length) {
        gameLength = length;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param name <<Param Desc>>
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
     * @param crewInfo <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean isCrewInfoValid(String crewInfo){
        Pattern p = Pattern.compile("\\w+-\\w+");
        Matcher m = p.matcher(crewInfo);
        return m.find();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param crewNumbers <<Param Desc>>
     * @param reader <<Param Desc>>
     */
    public void setCrewMembers(int crewNumbers, Scanner reader) {
        crewMembers = new ArrayList<>();


        for (int i = 0; i < crewNumbers; i++) {
            String crewType = "";
            String memberName = "";
            String crewInfo = "";
            do {
                System.out.print("Input crew name followed by their type");
                System.out.println(" (" + (crewNumbers - i) + " to go)");
                System.out.println("For example: dora-explorer");
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
     * @param days <<Param Desc>>
     * @return int <<Return Desc>>
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
        Utils.printCrewASCII();
        Utils.printCrewStatusHeader();
        String crewStatus = crew.getCrewMemberStatus();
        typePrint(crewStatus);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void viewSpaceshipStatus() {
        Utils.printSpaceshipASCII();
        Utils.printSpaceshipHeader();
        typePrint(ship.toString());
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void visitOutpost() {
        Utils.printOutpostHeader();
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
     * @param reader <<Param Desc>>
     * @return String <<Return Desc>>
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
     * @param query <<Param Desc>>
     * @return boolean <<Return Desc>>
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
            return outpost.hasItemInStock(itemName);
        }

        return false;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param queries <<Param Desc>>
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
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     */
    public void enterToContinue(Scanner reader) {

        System.out.println("\nPress Enter to exit to homepage");
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }

    public boolean isValidCopilot(int currIndex, int copilotIndex, int actions) {
        if (currIndex != copilotIndex) {
            return actions > 0;
        }

        return false;
    }

    public int getIntegerInput(Scanner reader) {
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
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     */
    public void commitActionPage(Scanner reader) {
        System.out.println("Welcome to the action center");
        System.out.println("Select crew member to complete action with \n");
        Utils.printActionCenterHeader();
        int numOfCrewsWithActions = 0;
        for (int i = 0; i < crewMembers.size(); i++) {
            if (crewMembers.get(i).stillHasActions()) {
                System.out.print(i + "    ");
                System.out.println(crewMembers.get(i));
                numOfCrewsWithActions++;
            }
        }
        if (numOfCrewsWithActions == 0) {
            typePrint("N: All your crews are out of actions");
            typePrint("N: Give them some rest and they'll be back in action tomorrow");
            typePrint("N: Back in action, get it? Heh heh heh");
            typePrint();
            return;
        }

        System.out.println("\nPlease enter index of crew member you want to apply action to");

        int index; 
        CrewMember selectedCrew;
        do {
            index = getIntegerInput(reader);
            selectedCrew = crewMembers.get(index); 
            if (selectedCrew.getActions() == 0){
                typePrint("Sorry " + selectedCrew.getName() + " does not have any actions");
            }
        } while(selectedCrew.getActions() == 0);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        typePrint("Selected crew member: " + selectedCrew.getName() + " at index " + index);
        typePrint("Select action to apply to " + selectedCrew.getName());

        Utils.printActionCenterChoices();

        int selection = getIntegerInput(reader);
        switch(selection) {
            case 1:
                TreeMap<Consumable, Integer> consumableWithCounts = crew.getConsumables();
                if (consumableWithCounts.size() == 0) {
                    typePrint();
                    typePrint("N: The player looks at his empty inventory and thinks");
                    typePrint("N: \"My crew could wait for another day\"");
                    typePrint();
                    return;
                }

                typePrint("Select food to consume:");
                int counter = 0;
                String template = "";
                Utils.printActionCommitFoodSelectionHeader();
                ArrayList<String> ConsumablesList = new ArrayList<>();
                for (Consumable c: consumableWithCounts.keySet()){
                    template += String.format("%5d   ", counter);
                    template += String.format("%8d   ", consumableWithCounts.get(c));
                    template += c;
                    System.out.println(template);
                    counter += 1;
                    template = "";
                    ConsumablesList.add(c.getName());
                }
                int chosenItem = getIntegerInput(reader);
                selectedCrew.useItem(crew.popConsumable(ConsumablesList.get(chosenItem)));
                break;
            case 4:
                boolean planetHasPieces = planets.get(currentPlanetIndex).stillHasShipPieces();
                boolean foundPieces = selectedCrew.searchPlanet();
                if (planetHasPieces) {
                    if (foundPieces) {
                        typePrint(selectedCrew.getName() + " has found a ship piece!");
                        foundShipPieces++;
                        typePrint("The crew now has " + foundShipPieces + " ship pieces");
                        planets.get(currentPlanetIndex).extractShipPieces();
                        break;
                    } 
                } 

                crew.addConsumable(outpost.getRandomItem());
                break;
            case 5:
                Utils.printActionCenterHeader();
                for (int i = 0; i < crewMembers.size(); i++) {
                    if (crewMembers.get(i).stillHasActions() && i != index) {
                        System.out.print(i + "    ");
                        System.out.println(crewMembers.get(i));
                    }
                }
                System.out.println();
                int actions = 0;
                int copilotIndex = 0;
                do {
                    System.out.println("Choose the copilot:");
                    copilotIndex = getIntegerInput(reader);
                    actions  = crewMembers.get(copilotIndex).getActions(); 
                    if (!isValidCopilot(index, copilotIndex, actions)) {
                        typePrint("N: Nuh, uh. Can't choose that one");
                    }
                    if (copilotIndex == index) {
                        typePrint("N: I also wish I could clone myself sometimes");
                    }
                } while (!isValidCopilot(index, copilotIndex, actions));
                CrewMember copilot = crewMembers.get(copilotIndex);
                selectedCrew.pilotShip(copilot);
                Random rand = new Random();
                int nextPlanetIndex;
                do {
                    nextPlanetIndex = rand.nextInt(planets.size());
                } while (nextPlanetIndex == currentPlanetIndex);
                currentPlanetIndex = nextPlanetIndex;
                Planet currentPlanet = planets.get(currentPlanetIndex);
                typePrint("N: You have arrived at " + currentPlanet.getName());
                String shipPiecePresence = "Spaceship Operator: Our radar has detected ";
                if (!currentPlanet.stillHasShipPieces()) {
                    shipPiecePresence += "no ";
                }
                shipPiecePresence += "presence of ship piece here\n";
                typePrint(shipPiecePresence);
                typePrint();
                break;
            default:
                typePrint();
                typePrint("[Error] Your selection was not in one of the choices");
                typePrint();
                break;
        }
    }

    public boolean hasFoundEnoughPieces() {
        return foundShipPieces == shipPieces;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Desc>>
     */
    public void homePage(Scanner reader) {
        do {
            if (hasFoundEnoughPieces()) {
                typePrint("N: You have won this computer game");
                typePrint("N: Congratulations");
                return;
            }

            Utils.printHomepageHeader();
            typePrint("[Ship pieces] : " + foundShipPieces);
            typePrint();

            System.out.print("> ");
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
                    getInputShoppingList(reader);
                    viewShoppingBag();

                    outpost.purchaseItems(crew);
                    enterToContinue(reader);
                    break;
                case "5":
                    endDay();
                    if (gameLength - currDay == -1) {
                        System.out.println("You reached your day limit thanks for playing");
                        hasEnded = true;
                    }
                    else {
                        System.out.println("You are now on day " + currDay + " (" + (gameLength - currDay) + " day(s) till end of game)");
                    }

                    enterToContinue(reader);
                    break;
                case "6":

                    System.out.println("Thanks for playing");
                    hasEnded = true;
            }
        } while (!hasEnded);  

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
        g.setupPlanets();
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

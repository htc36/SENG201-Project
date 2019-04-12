package game;

import static game.Utils.typePrint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consumable.*;
import crew.Crew;
import outpost.Outpost;
import planet.Planet;
import random_events.*;
import unit.*;

public class GameEngine {

    private int gameLength;
    private int shipPieces;
    private ArrayList<String> crewMemberTypes;
    private ArrayList<CrewMember> crewMembers;
    private Spaceship ship;
    private Crew crew;
    private Outpost outpost;
    private int currDay;
    private ArrayList<Planet> planets;
    private int currentPlanetIndex;
    private int foundShipPieces;
    private Scanner reader;

    /**
     * <<auto generated javadoc comment>>
     */
    public GameEngine() {
        reader = new Scanner(System.in);

        currDay = 1;

        crewMemberTypes = new ArrayList<>();
        crewMemberTypes.add("medic");
        crewMemberTypes.add("explorer");
        crewMemberTypes.add("hungus");
        crewMemberTypes.add("sleeper");
        crewMemberTypes.add("actioneer");
        crewMemberTypes.add("builder");

        Food f1 = new Brownie();
        Food f2 = new FriedRice();
        Food f3 = new Dumplings();
        Food f4 = new SpaceCake();
        Food f5 = new TikkaMasala();
        Food f6 = new Hotbot();

        MedicalSupply m1 = new PolyJuice();
        MedicalSupply m2 = new PickledPlum();
        MedicalSupply m3 = new Vaccine();

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        outpost = new Outpost(c);

        currentPlanetIndex = 0;
        foundShipPieces = 0;

    }

    /**
     * <<auto generated javadoc comment>>
     */
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
                planets.add(new Planet(firstName[i] + " " + lastName[j], false));
                if (planets.size() >= shipPieces * 2) {
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
     * @return int <<Return Desc>>
     */
    public int getInputNumDays() {
        int numOfDays;
        do {
            numOfDays = getIntegerInput();
            if (!isValidNumOfDays(numOfDays)) {
                System.out.println("Please enter a value between 3 and 10");
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
     * @return String <<Return Desc>>
     */
    public String getInputSpaceshipName() {
        System.out.print("> ");
        String name = reader.next();
        return name.replaceAll("\\s","");
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getCrewAmount() {
        int amount = 0;
        do {
            amount = getIntegerInput();
            if (amount < 2 || amount > 4)
                System.out.println("Crew amount must be between 2 and 4");
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
        ship = new Spaceship(name.toUpperCase());
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
     */
    public void setCrewMembers(int crewNumbers) {
        crewMembers = new ArrayList<>();


        for (int i = 0; i < crewNumbers; i++) {
            String crewType = "";
            String memberName = "";
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
                memberName = splitter[0].toUpperCase();
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
                case "actioneer":
                    crewMembers.add(new Actioneer(memberName));
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
        typePrint("Clerk: Don't forget to say done when you're finished");
        typePrint("Clerk: Let me refresh your memory just in case");
        typePrint();
        typePrint("Clerk: > 2xBrownie");
        typePrint("Clerk: > 1xSpaceCake");
        typePrint("Clerk: > done");
        typePrint();
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getInputShoppingList() {
        String errMsg = "Clerk: Sorry I didn't quite catch that, try again?";
        String allQueries = "";
        String query = "";

        while (true) {
            System.out.print("> ");
            query = reader.next();
            if (isValidQuery(query)) {
                addItemToShoppingBag(query);
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
     * @param query <<Param Desc>>
     */
    public void addItemToShoppingBag(String query) {
        int amount = 0;
        String itemName = "";
        amount = Integer.valueOf(query.split("x")[0]);
        itemName = query.split("x")[1];
        for (int i = 0; i < amount; i ++ ) {
            outpost.addItemToShoppingBag(itemName);
        }
        while (outpost.getTotalPrice() > crew.getMoney()) {

            typePrint("Clerk: You do not have enough money to purchase all the items");
            typePrint("Clerk: Your shopping list");
            viewShoppingBag();
            typePrint("Pick item to remove");
            String removedItem = reader.next();
            while (outpost.hasItemInShoppingBag(removedItem)) {
                outpost.removeItemFromShoppingBag(removedItem);
            }

        }
        System.out.println("Good to go, your shooping list is now:");
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
     */
    public void enterToContinue() {
        System.out.println("\n<Enter> to go back to command center");
        try {
            System.in.read();
        } catch (IOException e) {};
        Utils.clearScreen();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param currIndex <<Param Desc>>
     * @param copilotIndex <<Param Desc>>
     * @param actions <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean isValidCopilot(int currIndex, int copilotIndex, int actions) {
        if (currIndex != copilotIndex) {
            return actions > 0;
        }

        return false;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
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
     * <<auto generated javadoc comment>>
     */
    public void commitActionPage() {
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
            do{
                index = getIntegerInput();
                if (index >= crewMembers.size() || index < 0)
                    typePrint("Oh my that crew member dosent exist try again");
            } while (index >= crewMembers.size() || index < 0);    
            selectedCrew = crewMembers.get(index); 
            if (selectedCrew.getActions() == 0)
                typePrint("Sorry " + selectedCrew.getName() + " does not have any actions");
        } while(selectedCrew.getActions() == 0);

        Utils.clearScreen();

        typePrint("Select action to apply to " + selectedCrew.getName());

        Utils.printActionCenterChoices();

        int selection = getIntegerInput();
        switch(selection) {
            case 1:
                TreeMap<Consumable, Integer> consumableWithCounts = crew.getConsumables();
                if (consumableWithCounts.size() == 0) {
                    typePrint();
                    typePrint("N: The captain looks at his empty inventory and thinks");
                    typePrint("N: \"My crew could wait for another day\"");
                    typePrint();
                    break;
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

                int chosenItem;
                do {
                    chosenItem = getIntegerInput();
                    if (chosenItem < 0 || chosenItem >= ConsumablesList.size())
                        typePrint("You can only choose items that are in the list");
                } while (chosenItem < 0 || chosenItem >= ConsumablesList.size());

                selectedCrew.useItem(crew.popConsumable(ConsumablesList.get(chosenItem)));
                break;
            case 2:
                if (selectedCrew.getFatique() == 0){
                    typePrint("What a nice captain, " + selectedCrew.getName() + " has no fatigue, yet you've granted rest");
                }else{
                    typePrint("As we speak ol mate " + selectedCrew.getName() + " is having a well deserved kip");
                }
                selectedCrew.sleep(30);
                break;
            case 3:
                if (ship.getHealth() == 100){
                    typePrint("We don't have any damage cap, but Jerry let one rip in the dunny so we'll deal to that");
                }else{
                    typePrint("Nice job, you know what they say healthy ship happy crew");
                } 

                selectedCrew.repairShield(ship, 15);
                break;
            case 4:
                boolean planetHasPieces = planets.get(currentPlanetIndex).stillHasShipPieces();
                boolean foundPieces = selectedCrew.searchPlanet();
                if (planetHasPieces) {
                    if (foundPieces) {
                        typePrint(selectedCrew.getName() + " has found a ship piece!");
                        foundShipPieces++;
                        typePrint("The crew now has " + foundShipPieces + " ship pieces");
                        typePrint();
                        planets.get(currentPlanetIndex).extractShipPieces();
                        break;
                    } 
                } 

                typePrint("N: " + selectedCrew.getName() + " searches long and hard", 50);
                if (unlucky(20)) {
                    typePrint("N: and found nothing");
                } else if (unlucky(50)) {
                    Consumable randomItem = outpost.getRandomItem();
                    typePrint("N: and found " + randomItem.getName());
                    crew.addConsumable(randomItem);
                    typePrint("N: Not a ship piece, but still something");
                } else {
                    crew.addMoney(45);
                    typePrint("N: and found money");
                    typePrint("N: Luckily the currency is used all around the universe");
                }
                typePrint();
                break;
            case 5:
                if (ship.getHealth() <= 50) {
                    typePrint("Spaceship Operator: Our ship is too damaged to take off sir");
                    typePrint();
                    break;
                }
                Utils.printActionCenterHeader();
                int copilotCanditates = 0;
                for (int i = 0; i < crewMembers.size(); i++) {
                    if (crewMembers.get(i).stillHasActions() && i != index) {
                        System.out.print(i + "    ");
                        System.out.println(crewMembers.get(i));
                        copilotCanditates++;
                    }
                }

                if (copilotCanditates == 0) {
                    typePrint("N: Everyone looks tired");
                    typePrint("N: Looks like you don't have someone who can copilot");
                    typePrint();
                    break;
                }

                System.out.println();
                int actions = 0;
                int copilotIndex = 0;
                do {
                    System.out.println("Choose the copilot index:");
                    copilotIndex = getIntegerInput();
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

                // percentage chance of 40% happening
                boolean unlucky = unlucky(40);
                if (unlucky) {
                    Utils.printSpaceshipTravelling(unlucky);
                    AsteroidBelt.causeDamage(crew);
                } else {
                    Utils.printSpaceshipTravelling(false);
                }

                Planet currentPlanet = planets.get(currentPlanetIndex);
                typePrint("N: You have arrived at planet " + currentPlanet.getName());
                String shipPiecePresence = "Spaceship Operator: Our radar has detected ";
                if (!currentPlanet.stillHasShipPieces()) {
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

    private boolean unlucky(int happeningChance) {
        Random rand = new Random();
        int chance = rand.nextInt(101);

        return chance < happeningChance;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean hasFoundEnoughPieces() {
        return foundShipPieces == shipPieces;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean printHomePage() {
        Utils.printHomepageHeader();
        typePrint("Found ship pieces: " + foundShipPieces);
        typePrint();

        System.out.print("> ");
        String name = reader.next();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch (name) {
            case "1":
                viewCrewMemberStatus();
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
                getInputShoppingList();
                viewShoppingBag();
                outpost.purchaseItems(crew);
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
     * <<auto generated javadoc comment>>
     */
    public void startDay() {
        // We start the day with random events occuring, what fun!
        Random rand = new Random();
        int randomEvent = rand.nextInt(3);
        switch (randomEvent) {
            case 1:
                AlienPirates.causeDamage(crew); 
                typePrint("Oh no those pesky alien pirates invaded the ship and raided our invenory");
                if (crew.getLostItem() == "")
                    typePrint("Little did they know we didn't have any muhahah, that'll teach em");
                else 
                    typePrint("Sadly a " + crew.getLostItem() + " was taken, it will be dearly missed");
                enterToContinue();
                break;
            case 0:
                typePrint("Houston we have a problem");
                typePrint("Some crew member(s) have been infected with Space Plague");
                typePrint("Some health points have been deducted from each.. Ouch");
                SpacePlague.causeDamage(crew);
                typePrint("Health will continue to be deducted from all crew members who are infected");
                typePrint("Use the vaccine from the outpost to heal yourself");
                enterToContinue();
                break;
        }

        ArrayList<CrewMember> deadCrew = crew.updateCrewStatus();
        String template = "WARNING\n";
        template += "Crew members below are hungry or tired\n";
        template += "They will receive more damage when injured\n";
        for (CrewMember c : crewMembers) {
            template += "-> " + c.getName() + "\n";
        }
        typePrint(template);
        enterToContinue();
        Utils.clearScreen();

        if (deadCrew.size() != 0){
            typePrint("Sady there has been a death(s), the following have died");
            for (CrewMember f : deadCrew) {
                typePrint(Utils.getDeathMessage() + ": " +  f.getName() + ", Day " + currDay + " of 10, 2019");
                crew.removeCrewMember(f);
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
        while (true) {
            if (hasGameEnded()) {
                typePrint("N: The game has ended");
                return;
            }
            if(currDay != 1)
                startDay();
            if (hasGameEnded()) {
                typePrint("N: Cap, theres no easy way to put this, everyone is DEAD");
                typePrint("Better luck next time");
                return;
            }
            typePrint("Day " + currDay + " (" + (gameLength - currDay) + " day(s) till end of game)");
            String template = "INFO :: Detected ";
            if (!planets.get(currentPlanetIndex).stillHasShipPieces())
                template += "NO ";
            template += "presence of a ship piece in this planet";
            typePrint(template);
            boolean shouldRepeat = false;
            do {
                shouldRepeat = printHomePage();
            } while (shouldRepeat);

            endDay();
        }
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean hasGameEnded() {
        return hasFoundEnoughPieces() || gameLength - currDay == -1 || crewMembers.size() == 0;
    }

    public void closeReader() {
        reader.close();
    }

    public void printFinalScore() {
        int shipPiecesScore = foundShipPieces * 1000;
        int daysScore = currDay * - 100;
        int finalScore = shipPiecesScore - daysScore;
        typePrint(String.format("Found ship pieces %1d * 1000 = %4d", foundShipPieces, shipPiecesScore));
        typePrint(String.format("Days spent        %1d * -100 = %4d", currDay, daysScore));
        typePrint("                  ------------------ + ");
        typePrint(String.format("Final score                  %4d", finalScore));
    }

}

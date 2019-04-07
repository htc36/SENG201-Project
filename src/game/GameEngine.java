package game;

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

    public GameEngine() {
        currDay = 1;

        crewMemberTypes = new ArrayList<>();
        crewMemberTypes.add("medic");
        crewMemberTypes.add("explorer");

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
        int numOfDays = reader.nextInt();
        return numOfDays;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param reader <<Param Description>>
     * @return String <<Return Description>>
     */
    public String getInputCrewMembers(Scanner reader) {
        String choices = "";
        do {
            choices = reader.next();
        } while (isInvalidCrewMemberInput(choices));

        return choices.replaceAll("\\s","").toLowerCase();
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

    /**
     * <<auto generated javadoc comment>>
     * @param choices <<Param Description>>
     * @return boolean <<Return Description>>
     */
    public boolean isInvalidCrewMemberInput(String choices) {
        String[] membersList = choices.split(",");
        for (String m : membersList) {
            if (!crewMemberTypes.contains(m)) {
                return true;
            }
        }
        return false;
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

    /**
     * <<auto generated javadoc comment>>
     * @param members <<Param Description>>
     */
    public void setCrewMembers(String members) {
        crewMembers = new ArrayList<>();
        String[] membersList = members.split(",");
        String memberName = "Gengis Khannnnn";
        for (String m : membersList) {
            switch(m) {
                case "Medic":
                    crewMembers.add(new Medic(memberName));
                    break;
                case "Explorer":
                    crewMembers.add(new Explorer(memberName));
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
        typePrint("        Name  Health Luck Plagued Hunger Fatique Actions");
        typePrint("--------------------------------------------------------");
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
                allQueries += query + ",";
            } else {
                if (query.equals("done")) {
                    break;
                }
                typePrint(errMsg);
            }
        }

        System.out.println(allQueries);
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

    /**
     * <<auto generated javadoc comment>>
     * @param queries <<Param Description>>
     */
    public void addItemToShoppingBag(String queries) {
        int amount = 0;
        String itemName = "";
        System.out.println(queries);
        String[] querySplit = queries.split(",");
        for (String query : querySplit) {
            amount = Integer.valueOf(query.split("x")[0]);
            itemName = query.split("x")[1];
            for (int i = 0; i < amount; i ++ ) {
                outpost.addItemToShoppingBag(itemName);
            }
        }
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void viewShoppingBag() {
        typePrint(outpost.shoppingBagToString());
        typePrint("Total price: $" + String.valueOf(outpost.getTotalPrice()));
    }

    /*
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

    /*
     * Prints out String message as if it was typed
     * the delay is set to 10 by default
     * @param message the message it prints
     */
    public void typePrint(String message) {
        int msgLength = message.length();
        if (msgLength == 0) {
            System.out.println();
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

    /*
     * Wrapper around System.out.println() for consistencies
     */
    public void typePrint() {
        System.out.println();
    }

    public static void main(String[] args) {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        GameEngine g = new GameEngine();
        Scanner reader = new Scanner(System.in);
        //System.out.println("Spaceship name: ");
        g.setupSpaceship("Andromeda");
        //g.setupSpaceship(g.getInputSpaceshipName(reader));
        //System.out.println("Number of days: ");
        g.setGameLength(10);
        //g.setGameLength(g.getInputNumDays(reader));
        g.setShipPieces();
        g.setCrewMembers("Medic,Medic,Medic");
        //g.setCrewMembers(g.getInputCrewMembers(reader));
        g.setupCrew();
        g.addCrewConsumable(new Food("Spaghetti", 10, 10, 10));
        g.addCrewConsumable(new Food("Banana", 10, 10, 10));
        g.addCrewConsumable(new MedicalSupply("Vape", 10, 10, false));
        g.addCrewConsumable(new MedicalSupply("Eyeballs", 10, 10, true));
        //g.viewCrewMemberStatus();
        //g.viewSpaceshipStatus();
        g.visitOutpost();
        String queries = g.getInputShoppingList(reader);
        g.addItemToShoppingBag(queries);
        g.viewShoppingBag();

        reader.close();
    }


}

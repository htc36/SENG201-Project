package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import consumable.*;
import crew.Crew;
import outpost.Outpost;
import unit.CrewMember;
import unit.Explorer;
import unit.Medic;
import unit.Spaceship;

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
        crewMemberTypes.add("Medic");
        crewMemberTypes.add("Explorer");

        Food f1 = new Food("Banana", 100, 180, 1);
        Food f2 = new Food("Melon", 100, 180, 1);
        Food f3 = new Food("Apple", 100, 180, 1);
        Food f4 = new Food("Beef", 100, 180, 1);
        Food f5 = new Food("Pork", 100, 180, 1);
        Food f6 = new Food("Chicken", 100, 180, 1);

        MedicalSupply m1 = new MedicalSupply("M1", 10, 10, false);
        MedicalSupply m2 = new MedicalSupply("Vaxxin", 10, 10, false);
        MedicalSupply m3 = new MedicalSupply("Cockroach Powder", 10, 10, false);

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        outpost = new Outpost(c);
    }

    public int getInputNumDays(Scanner reader) {
        int numOfDays = reader.nextInt();
        return numOfDays;
    }

    public String getInputCrewMembers(Scanner reader) {
        String choices = "";
        do {
            choices = reader.next();
        } while (isInvalidCrewMemberInput(choices));

        return choices.replaceAll("\\s","");
    }

    public String getInputSpaceshipName(Scanner reader) {
        String name = reader.next();
        return name.replaceAll("\\s","");
    }

    public boolean isInvalidCrewMemberInput(String choices) {
        String[] membersList = choices.split(",");
        for (String m : membersList) {
            if (!crewMemberTypes.contains(m)) {
                return true;
            }
        }
        return false;
    }

    public void setGameLength(int length) {
        gameLength = length;
    }

    public void setupSpaceship(String name) {
        ship = new Spaceship(name);
    }

    public void setupCrew() {
        crew = new Crew(crewMembers, ship);
    }

    public void setShipPieces() {
        shipPieces = calculateShipPieces(gameLength);
    }

    public void setCrewMembers(String members) {
        crewMembers = new ArrayList<>();
        String[] membersList = members.split(",");
        String memberName = "Gengis Khannnnn";
        for (String m : membersList) {
            switch(m) {
                case "Medic":
                    crewMembers.add(new Medic(memberName, 100));
                    break;
                case "Explorer":
                    crewMembers.add(new Explorer(memberName, 100));
                    break;
            }
        }
    }

    public int calculateShipPieces(int days) {
        return days * 2 / 3;
    }

    public void startDay() {
        // We start the day with random events occuring, what fun!
        Random rand = new Random();
        int randomEvent = rand.nextInt(2);
        switch (randomEvent) {
            case 1:
                break;
            case 0:
                break;
        }
    }

    public void endDay() {
        // by the end of the day, everyone went to bed and get ready
        // for the next day. Refreshes all the crew members action to 2
        for (CrewMember c : crewMembers) {
            c.refreshActions();
        }
        currDay++;
    }

    public void run() {

    }

    public void viewCrewMemberStatus() {
        typePrint();
        typePrint("*** Crew Members Status ***");
        typePrint();
        typePrint("        Name  Health Luck Plagued Hunger Fatique Actions");
        typePrint("--------------------------------------------------------");
        String crewStatus = crew.getCrewMemberStatus();
        typePrint(crewStatus);
    }

    public void viewSpaceshipStatus() {
        typePrint();
        typePrint("*** Spaceship Status ***");
        typePrint();
        typePrint("        Name  Health");
        typePrint("--------------------");
        typePrint(ship.toString());
    }

    public void visitOutpost() {
        typePrint();
        typePrint("*** Welcome to the outpost ***");
        typePrint();
        typePrint("Please place your items in the bagging area");
        typePrint("Here are the things on sale today:");
        typePrint("----------------------------------");
        typePrint("Type           Name Price Heal Fill Cures_Plague");
        typePrint("------------------------------------------------");
        typePrint(outpost.saleProductsToString());
        typePrint();
    }

    public void typePrint() {
        System.out.println();
    }

    public void typePrint(String message) {
        int msgLength = message.length();
        int i;
        for (i = 0; i < msgLength; i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {};
        }

        if (message.charAt(--i) != '\n') {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        GameEngine g = new GameEngine();
        Scanner reader = new Scanner(System.in);
        System.out.println("Spaceship name: ");
        g.setupSpaceship("Andromeda");
        //g.setupSpaceship(g.getInputSpaceshipName(reader));
        System.out.println("Number of days: ");
        g.setGameLength(10);
        //g.setGameLength(g.getInputNumDays(reader));
        g.setShipPieces();
        g.setCrewMembers("Medic,Medic,Medic");
        //g.setCrewMembers(g.getInputCrewMembers(reader));
        g.setupCrew();
        reader.close();
        g.viewCrewMemberStatus();
        g.viewSpaceshipStatus();
        g.visitOutpost();
    }

}

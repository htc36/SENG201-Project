package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import crew.Crew;
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
    private int currDay;

    public GameEngine() {
        currDay = 1;

        crewMemberTypes = new ArrayList<>();
        crewMemberTypes.add("Medic");
        crewMemberTypes.add("Explorer");
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

        return choices;
    }

    public String getInputSpaceshipName(Scanner reader) {
        String name = reader.next();
        return name;
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
        String memberName = "27";
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

    public static void main(String[] args) {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        GameEngine g = new GameEngine();
        Scanner reader = new Scanner(System.in);
        System.out.print("Spaceship name: ");
        g.setupSpaceship(g.getInputSpaceshipName(reader));
        System.out.print("Number of days: ");
        g.setGameLength(g.getInputNumDays(reader));
        g.setShipPieces();
        g.setCrewMembers(g.getInputCrewMembers(reader));
        g.setupCrew();
        reader.close();
    }

}

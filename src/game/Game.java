package game;

import game.Utils.*;

public class Game {
    public static void printCrewMemberList() {
        System.out.println("Here are the possible crew member choices");
        System.out.println("--> Actioneer - has 3 actions instead of the usual 2");
        System.out.println("--> Builder - repairs spaceship faster");
        System.out.println("--> Explorer - has higher chance of finding ship pieces");
        System.out.println("--> Hungus - more immune to hunger");
        System.out.println("--> Medic - receives less damage");
        System.out.println("--> Sleeper - more immune to fatique");
    }

    public static void main(String[] args) {
        Utils.clearScreen();
        GameEngine g = new GameEngine();
        System.out.println("Spaceship name: ");
        g.setupSpaceship(g.getInputSpaceshipName());
        Utils.clearScreen();
        System.out.println("Number of days: ");
        g.setGameLength(g.getInputNumDays());
        Utils.clearScreen();
        Game.printCrewMemberList();
        System.out.println("\nNumber of crew members (2-4): ");
        g.setCrewMembers(g.getCrewAmount());
        Utils.clearScreen();
        g.setShipPieces();
        g.setupPlanets();
        g.setupCrew();
        g.run();
        g.closeReader();
        g.printFinalScore();
    }

}

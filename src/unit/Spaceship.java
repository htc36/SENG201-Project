package unit;

import java.util.ArrayList;

/**
 * Defines a spaceship, the crew member 
 * uses a spaceship to get from planet to planet.
 * The spaceship needs to be a certain amount of health
 * before it can take off
 */
public class Spaceship extends Unit {

    /**
     * Constructor for Spaceship
     * @param shipName name of the new ship
     */
    public Spaceship(String shipName) {
        super(shipName);
    }

    /**
     * Adds shield health to Spaceship
     * @param amount amount of health added
     */
    public void repairShield(int amount) {
        super.addHealth(amount);
    }

    /**
     * Reduces shield health to Spaceship
     * @param amount amount of health reduced
     */
    public void receiveDamage(int amount) {
        super.reduceHealth(amount);
    }

    /**
     * Returns ArrayList of String representation of an Spaceship
     * @return ArrayList<String> ArrayList String representation of an Spaceship
     */
    public ArrayList<String> getShipStatus() {
        ArrayList<String> result = new ArrayList<>();
        result.add(super.getName());
        result.add(String.format("%d", super.getHealth()));

        return result;
    }

}

package unit;

import java.util.ArrayList;

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

    public ArrayList<String> getShipStatus() {
        ArrayList<String> result = new ArrayList<>();
        result.add(super.getName());
        result.add(String.format("%d", super.getHealth()));

        return result;
    }

}

package unit;

public class Spaceship extends Unit {

    /* 
     * Constructor for Spaceship
     * @param shipName name of the new ship
     */
    public Spaceship(String shipName) {
        super(shipName);
    }

    /* 
     * Adds shield health to Spaceship
     * @param amount amount of health added
     */
    public void repairShield(int amount) {
        super.addHealth(amount);
    }

    /* 
     * Reduces shield health to Spaceship
     * @param amount amount of health reduced
     */
    public void receiveDamage(int amount) {
        super.reduceHealth(amount);
    }

}

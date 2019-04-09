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

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String toString() {
        String template = "%12.12s"; // name
        template += "%8d"; // shield level

        return String.format(template, super.getName(), super.getHealth());
    }

}

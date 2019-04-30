package unit;

public class Unit {
    private int health;
    private String name;

    /**
     * Constructor for Unit
     * @param newName name of the new unit
     */
    public Unit(String newName) {
        name = newName;
        health = 100;
    }

    /**
     * Returns if a Unit has more than 0 health
     * @return bool value if unit is alive
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Sets the health of a unit to amount
     * @param health amount of health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Reduces health to unit by some amount
     * @param amount the amount of health reduced
     */
    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    /**
     * Adds health to unit by some amount
     * @param amount the amount of health added
     */
    public void addHealth(int amount) {
        health += amount;
        if (health > 100) {
            health = 100;
        }
    }

    /**
     * Returns the Unit's health points
     * @return health of the unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the Unit's name
     * @return name of the unit
     */
    public String getName() {
        return name;
    }

}

package events;

import crew.Crew;
import unit.Spaceship;

/**
 * Defines one of the random events, 
 * Asteroid belt can cause
 * damage to the spaceship whenever the crew goes to another
 * planet
 */
public class AsteroidBelt implements RandomEvents{

    /**
     * Asteroid Belt causes damage to the crew!
     * Crew's spaceship receives some damage
     * @param c the crew
     */
    public static void causeDamage(Crew c) {
        Spaceship spaceShip = c.getSpaceship();
        int spaceShipHealth = spaceShip.getHealth();
        if (spaceShipHealth > 70) {
            spaceShip.receiveDamage(15);
        } else {
            spaceShip.receiveDamage(20);
        }
    }

}

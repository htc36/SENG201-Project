package random_events;

import crew.Crew;
import unit.Spaceship;

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

        System.out.println("WARNING");
        System.out.println("The spaceship has BONKED into an asteroid belt");
        System.out.println("N: The pilot won't have cold beers tonight");
        System.out.println();
    }

}

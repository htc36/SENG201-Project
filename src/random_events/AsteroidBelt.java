package random_events;

import crew.Crew;
import unit.Spaceship;

public class AsteroidBelt implements RandomEvents{

    /**
     * Asteroid Belt causes damage to the crew!
     * Crew's spaceship receives some damage
     * @param c the crew
     */
    public void causeDamage(Crew c) {
        Spaceship spaceShip = c.getSpaceship();
        spaceShip.receiveDamage(20);

    }

}

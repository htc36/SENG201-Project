package random_events;

import crew.Crew;
import unit.Spaceship;

public class AsteroidBelt implements RandomEvents{

    /**
     * <<auto generated javadoc comment>>
     * @param c <<Param Desc>>
     */
    public void causeDamage(Crew c) {
        Spaceship spaceShip = c.getSpaceship();
        spaceShip.receiveDamage(20);

    }







}

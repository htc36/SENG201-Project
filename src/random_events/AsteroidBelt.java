package random_events;

import crew.Crew;
import unit.Spaceship;

public class AsteroidBelt implements RandomEvents{

    public void causeDamage(Crew c) {
        Spaceship spaceShip = c.getSpaceship();
        spaceShip.receiveDamage(20);

    }







}

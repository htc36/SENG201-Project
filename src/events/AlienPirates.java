package events;

import crew.Crew;

public class AlienPirates implements RandomEvents{

    /**
     * Alien Pirates causes damage to the crew! Oh no!
     * A random item is popped from crew's inventory
     * @param c the crew
     */
    public static void causeDamage(Crew c) {
        c.popRandomItem();
    }

}

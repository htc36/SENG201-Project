package events;

import crew.Crew;

/**
 * Defines one of the random events, 
 * Alien pirates can steal one of the 
 * player's consumables
 */
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

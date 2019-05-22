package events;

import crew.Crew;

/**
 * Defines an interface that every random events have to implement.
 * Makes sure that every random event can cause damage to the crew in
 * one way or another
 * @author kta79
 *
 */
public interface RandomEvents {

    /**
     * Interface that every random events has to implement
     * */
    /**
     * Causes damage in one way or another to the player's crew
     * @param c the crew
     */
    public static void causeDamage(Crew c) {};

}

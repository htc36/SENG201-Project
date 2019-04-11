package random_events;

import crew.Crew;

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

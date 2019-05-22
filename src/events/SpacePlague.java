package events;

import java.util.ArrayList;
import java.util.Random;

import crew.Crew;
import unit.CrewMember;

/**
 * Defines one of the random events, 
 * Space Plague causes health of the crew members
 * to reduce every day until they consumed an item that cures space plague
 */
public class SpacePlague implements RandomEvents {

    /**
     * Space plague sets random crew members between 1 to every crew member
     * to be plagued, while they are plagued they will receive damage over time
     * @param cr crew
     */
    public static void causeDamage(Crew cr) {
        ArrayList<CrewMember> c = cr.getCrewMembers();
        Random rand = new Random();
        int index = rand.nextInt(c.size());
        ArrayList<Integer> indexs = new ArrayList<>();
        do {
            indexs.add(index);
            c.get(index).makeSick();
            c.get(index).reduceHealth(10);
            index = rand.nextInt(c.size());
        } while (!indexs.contains(index));
    }

}

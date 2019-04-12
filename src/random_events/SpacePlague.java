package random_events;

import java.util.ArrayList;
import java.util.Random;

import crew.Crew;
import unit.CrewMember;
import static game.Utils.typePrint;

public class SpacePlague implements RandomEvents {
//private String effectedMembers;
    /**
     * Space Plague! 
     * space plague sets random crew members between 1 to all 
     * to be plagued, while they are plagued they will receive 
     * damage over time
     * @param cr the crew
     */
    public static void causeDamage(Crew cr) {
        String effectedMembers = "";
        ArrayList<CrewMember> c = cr.getCrewMembers();
        Random rand = new Random();
        int index = rand.nextInt(c.size());
        ArrayList<Integer> indexs = new ArrayList<>();
        while (!indexs.contains(index)) {
            effectedMembers += c.get(index).getName() + " ";
            indexs.add(index);
            c.get(index).makeSick();
            c.get(index).reduceHealth(10);
            index = rand.nextInt(c.size());
        }
        typePrint(effectedMembers); 

    }

}

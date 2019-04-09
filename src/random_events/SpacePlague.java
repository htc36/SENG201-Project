package random_events;

import java.util.ArrayList;
import java.util.Random;

import crew.Crew;
import unit.CrewMember;

public class SpacePlague implements RandomEvents {

    public static void causeDamage(Crew cr) {
        ArrayList<CrewMember> c = cr.getCrewMembers();
        Random rand = new Random();
        int index = rand.nextInt(c.size());
        ArrayList<Integer> indexs = new ArrayList<>();

        while (!indexs.contains(index)) {
            indexs.add(index);
            c.get(index).makeSick();
            c.get(index).reduceHealth(10);
            index = rand.nextInt(c.size());
        }

    }

}

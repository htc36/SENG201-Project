package unit;

import java.util.ArrayList;

public class Medic extends CrewMember {

    /**
     * Constructor for medic type crew member
     * medic type units receive less damage compared to other units
     * @param memberName name of the medic unit
     */
    public Medic(String memberName) {
        super(memberName, 20);
    }

    /**
     * Reduces the health of medic type unit
     * medic type units receive less damage compared to other units
     * @param amount amount of damage received
     */
    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount / 2);
    }

    /**
     * Returns ArrayList of String representation of an Medic
     * @return ArrayList<String> ArrayList String representation of an Medic
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Medic");
        return result;
    }


}

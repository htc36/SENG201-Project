package unit;

import java.util.ArrayList;

/**
 * Defines the builder crew member,
 * Builder can repair the spaceship faster
 */
public class Builder extends CrewMember{

    /**
     * Constructor for builder type crew member
     * builder unit can repair shield better than other units
     * @param memberName name of the builder unit
     */
    public Builder(String memberName) {
        super(memberName, 40);
    }

    /**
     * Repairs the shield of the spaceship by some amount
     * builder unit gets extra bonus when repairing spaceship
     * @param s the spaceship being repaired
     * @param amount amount of shield repaired
     */
    @Override
    public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
    }

    /**
     * Returns ArrayList of String representation of an Builder
     * @return ArrayList<String> ArrayList String representation of an Builder
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Builder");
        return result;
    }

}

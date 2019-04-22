package unit;

import java.util.ArrayList;

public class Builder extends CrewMember{

    /**
     * constructor for builder type crew member
     * builder unit can repair shield better than other units
     * @param memberName name of the builder unit
     */
    public Builder(String memberName) {
        super(memberName, 40);
    }

    /**
     * repairs the shield of the spaceship by some amount
     * builder unit gets extra bonus when repairing spaceship
     * @param s the spaceship being repaired
     * @param amount amount of shield repaired
     */
    @Override
    public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return ArrayList<String> <<Return Desc>>
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Builder");
        return result;
    }

}

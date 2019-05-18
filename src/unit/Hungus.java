package unit;

import java.util.ArrayList;

public class Hungus extends CrewMember{

    /**
     * Constructor for hungus type crew member
     * hungus type units gets hungry less easily
     * and benefit more from food
     * @param memberName name of the hungus unit
     */
    public Hungus(String memberName) {
        super(memberName, 30);

    }

    /**
     * Increases the hunger of hungus unit
     * hungus units gets hungry less easily
     * @param amount amount of hunger level increased
     */
    @Override
    public void increaseHunger(int amount) {
        super.increaseHunger(amount / 2);
    }


    /**
     * Decreases the hunger of hungus unit
     * @param amount amount of hunger level decreased
     */
    @Override
    public void decreaseHunger(int amount) {
        super.decreaseHunger(amount * 2);
    }

    /**
     * Returns ArrayList of String representation of an Hungus
     * @return ArrayList<String> ArrayList String representation of an Hungus
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Hungus");
        return result;
    }


}

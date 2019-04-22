package unit;

import java.util.ArrayList;

public class Hungus extends CrewMember{

    /**
     * constructor for hungus type crew member
     * hungus type units gets hungry less easily
     * and benefit more from food
     * @param memberName name of the hungus unit
     */
    public Hungus(String memberName) {
        super(memberName, 30);

    }

    /**
     * increases the hunger of hungus unit
     * hungus units gets hungry less easily
     * @param amount amount of hunger level increased
     */
    @Override
    public void increaseHunger(int amount) {
        super.increaseHunger(amount / 2);
    }


    /**
     * decreases the hunger of hungus unit
     * @param amount amount of hunger level decreased
     */
    @Override
    public void decreaseHunger(int amount) {
        super.decreaseHunger(amount * 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return ArrayList<String> <<Return Desc>>
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Hungus");
        return result;
    }


}

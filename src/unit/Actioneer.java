package unit;

import java.util.ArrayList;

public class Actioneer extends CrewMember{

    /**
     * Constructor for builder type hardworker
     * hardworker type units have more actions than other units
     * @param memberName name of the hardworker unit
     */
    public Actioneer(String memberName) {
        super(memberName, 30, 3);
    }

    /**
     * Refreshes the number of actions of hardworker to 3
     * @param amount the number of actions
     */
    @Override
    public void refreshActions(int amount) {
        super.refreshActions(3);

    }

    /**
     * Returns ArrayList of String representation of an Actoineer
     * @return ArrayList<String> ArrayList String representation of an Actioneer
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Actioneer");
        return result;
    }

}

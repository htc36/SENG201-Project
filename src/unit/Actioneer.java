package unit;

import java.util.ArrayList;

/**
 * Defines the actioneer crew member,
 * Actioneer can do 3 actions instead of the usual 2
 */
public class Actioneer extends CrewMember {

    /**
     * Constructor for builder type actioneer
     * actioneer type units have more actions than other units
     * @param memberName name of the actioneer unit
     */
    public Actioneer(String memberName) {
        super(memberName, 30, 3);
    }

    /**
     * Refreshes the number of actions of actioneer to 3
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

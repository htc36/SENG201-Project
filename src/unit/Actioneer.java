package unit;

import java.util.ArrayList;

public class Actioneer extends CrewMember{

    /**
     * constructor for builder type hardworker
     * hardworker type units have more actions than other units
     * @param memberName name of the hardworker unit
     */
    public Actioneer(String memberName) {
        super(memberName, 30, 3);
    }

    /**
     * refreshes the number of actions of hardworker to 3
     * @param amount the number of actions
     */
    @Override
    public void refreshActions(int amount) {
        super.refreshActions(3);

    }

    /**
     * <<auto generated javadoc comment>>
     * @return ArrayList<String> <<Return Desc>>
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Actioneer");
        return result;
    }

}

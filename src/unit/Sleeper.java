package unit;

import java.util.ArrayList;

public class Sleeper extends CrewMember{

    /**
     * constructor for sleeper type crew member
     * sleeper type units benefit more from sleeping
     * @param memberName name of the sleeper unit
     */
    public Sleeper(String memberName) {
        super(memberName, 40);
    }

    /**
     * sleeper sleeps zZzzZzzZZ
     * sleeper type units benefit more from sleeping
     * @param amount amount of fatique level reduced
     */
    @Override
    public void sleep(int amount) {
        super.sleep(amount * 2);
    }

    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Sleeper");
        return result;
    }

}

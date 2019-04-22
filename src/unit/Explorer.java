package unit;

import java.util.ArrayList;

public class Explorer extends CrewMember {

    /**
     * constructor for explorer type crew member
     * explorer unit has higher luck than other type members
     * @param memberName name of the explorer unit
     */
    public Explorer(String memberName) {
        super(memberName, 75);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return ArrayList<String> <<Return Desc>>
     */
    @Override
    public ArrayList<String> getCrewString() {
        ArrayList<String> result = super.getCrewString();
        result.add("Explorer");
        return result;
    }


}

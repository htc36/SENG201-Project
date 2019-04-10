package unit;

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
     * toString() method of explorer
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a explorer
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Explorer");
    }

}

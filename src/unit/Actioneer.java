package unit;

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
     * toString() method of hardworker
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a hardworker
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Actioneer");
    }

}

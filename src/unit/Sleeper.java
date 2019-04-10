package unit;

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

    /**
     * toString() method of sleeper
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a sleeper
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Sleeper");
    }

}

package unit;

public class Medic extends CrewMember {

    /**
     * constructor for medic type crew member
     * medic type units receive less damage compared to other units
     * @param memberName name of the medic unit
     */
    public Medic(String memberName) {
        super(memberName, 10);
    }

    /**
     * reduces the health of medic type unit
     * medic type units receive less damage compared to other units
     * @param amount amount of damage received
     */
    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount / 2);
    }

    /**
     * toString() method of medic type unit
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a medic unit
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Medic");
    }

}

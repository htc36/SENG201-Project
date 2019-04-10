package unit;

public class Builder extends CrewMember{

    /**
     * constructor for builder type crew member
     * builder unit can repair shield better than other units
     * @param memberName name of the builder unit
     */
    public Builder(String memberName) {
        super(memberName, 10);
    }

    /**
     * repairs the shield of the spaceship by some amount
     * builder unit gets extra bonus when repairing spaceship
     * @param s the spaceship being repaired
     * @param amount amount of shield repaired
     */
    @Override
    public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
    }

    /**
     * toString() method of builder
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a builder
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Builder");
    }

}

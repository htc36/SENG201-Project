package unit;

public class Builder extends CrewMember{

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public Builder(String memberName) {
        super(memberName, 10);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param s <<Param Desc>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Builder");
    }

}

package unit;

public class Medic extends CrewMember {

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public Medic(String memberName) {
        super(memberName, 10);
    }
    /**
     * <<auto generated javadoc comment>>
     */
    @Override
    public void makeSick() {
        return ;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount / 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Medic");
    }

}

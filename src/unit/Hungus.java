package unit;

public class Hungus extends CrewMember{

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public Hungus(String memberName) {
        super(memberName, 10);

    }

    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void increaseHunger(int amount) {
        super.increaseHunger(amount / 2);
    }


    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void decreaseHunger(int amount) {

        super.decreaseHunger(amount * 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Hungus");
    }

}

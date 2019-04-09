package unit;

public class Sleeper extends CrewMember{

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public Sleeper(String memberName) {
        super(memberName, 10);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void sleep(int amount) {
        super.sleep(amount * 2);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Sleeper");
    }

}
